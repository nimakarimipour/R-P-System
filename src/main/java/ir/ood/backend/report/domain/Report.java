package ir.ood.backend.report.domain;

import ir.ood.backend.commons.exceptions.BusinessLogincException;
import ir.ood.backend.commons.exceptions.NotFoundException;
import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.evaluation.domain.Evaluation;
import ir.ood.backend.evaluation.domain.EvaluationCriterion;
import ir.ood.backend.userManagement.domain.Worker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class Report implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Worker reviewer;
    @ManyToOne
    @JoinColumn(name = "reviewee_id")
    private Worker reviewee;
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "report_id")
    private List<Score> scores;

    private Report() {
        scores = new ArrayList<>();
    }

    public Report(Worker reviewer, Worker reviewee, Evaluation evaluation) {
        this();
        this.reviewer = reviewer;
        this.reviewee = reviewee;
        this.evaluation = evaluation;
    }

    public boolean isReviewer(Worker worker) {
        return reviewer.getId() == worker.getId();
    }

    @Override
    public Map serialize() {
        return mapOf(
                "id", id,
                "scores", scores.stream().map(Serializable::serialize).collect(Collectors.toList()),
                "evaluation", evaluation.serialize(),
                "reviewer", reviewer.serialize(),
                "reviewee", reviewee.serialize(),
                "punished", shouldBePunished(),
                "rewarded", shouldBeRewarded()
        );
    }

    private boolean shouldBePunished() {
        return scores.size() > 0 && evaluation.shouldBePunished(totalScore());
    }

    private boolean shouldBeRewarded() {
        return scores.size() > 0 && evaluation.shouldBeRewarded(totalScore());
    }

    private int totalScore() {
        return scores.stream().mapToInt(Score::getValue).sum();
    }

    public boolean hasScores() {
        return scores.size() > 0;
    }

    public void enterGrade(int criterionId, String gradeText) {
        if (! evaluation.isValidGradeText(criterionId, gradeText))
            throw new BusinessLogincException("invalid_grade", "This grade is not valid for the given criterion");
        EvaluationCriterion criterion = evaluation.
                findCriterion(criterionId).
                orElseThrow(() -> new NotFoundException("invalid_criterion_id", "No such criterion for the evaluation"));
        Score score = new Score(
                criterion,
                evaluation.getValueForGradeText(criterionId, gradeText),
                gradeText
        );
        scores.add(score);
    }

}
