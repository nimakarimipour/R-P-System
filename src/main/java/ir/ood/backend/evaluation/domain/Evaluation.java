package ir.ood.backend.evaluation.domain;

import ir.ood.backend.commons.exceptions.NotFoundException;
import ir.ood.backend.commons.web.Serializable;
import ir.ood.backend.userManagement.domain.Section;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
public class Evaluation implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "evaluation_id")
    private List<EvaluationCriterion> criteria;
    @Embedded
    private EvaluationThreshold thresholds;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    public Evaluation(String title, Section section) {
        this();
        this.title = title;
        this.section = section;
    }

    public Evaluation() {
        this.criteria = new ArrayList<>();
        thresholds = new EvaluationThreshold();
    }

    public void addCriterion(EvaluationCriterion criterion) {
        this.criteria.add(criterion);
    }

    public void updateThresholds(int punishmentThreshold, int rewardThreshold) {
        this.thresholds = new EvaluationThreshold(punishmentThreshold, rewardThreshold);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectionId() {
        return section.getId();
    }

    @Override
    public Map serialize() {
        return mapOf(
                "id", id,
                "title", title,
                "thresholds", thresholds.serialize(),
                "criteria", criteria.stream().map(Serializable::serialize).collect(Collectors.toList()),
                "sectionId", getSectionId()
        );
    }

    public void setActions(String rewardAction, String punishmentAction) {
        thresholds.setActions(rewardAction, punishmentAction);
    }

    public Optional<EvaluationCriterion> findCriterion(int id) {
        for (EvaluationCriterion criterion : criteria)
            if (criterion.getId() == id)
                return Optional.ofNullable(criterion);
        return Optional.empty();
    }

    public boolean isValidGradeText(int criterionId, String text) {
        EvaluationCriterion criterion = findCriterion(criterionId).orElseThrow(
                () -> new NotFoundException("invalid_criterion_id", "No such criterion for this evaluation"));
        return criterion.isValidGradeText(text);
    }

    public int getValueForGradeText(int criterionId, String text) {
        EvaluationCriterion criterion = findCriterion(criterionId).orElseThrow(
                () -> new NotFoundException("invalid_criterion_id", "No such criterion for this evaluation"));
        return criterion.getValueForGradeText(text);
    }

    public boolean shouldBeRewarded(int score) {
        return thresholds.shouldBeRewarded(score);
    }

    public boolean shouldBePunished(int score) {
        return thresholds.shouldBePunished(score);
    }
}
