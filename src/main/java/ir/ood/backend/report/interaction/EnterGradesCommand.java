package ir.ood.backend.report.interaction;

import ir.ood.backend.commons.requestParams.Validatable;

import java.util.Map;
import java.util.Objects;

public class EnterGradesCommand extends Validatable {

    public Map<Integer, String> criterionScoreTextMap;
    public int reportId;

    @Override
    public void validate() {
        validateField(reportId, reportId -> reportId > 0, "reportId");
        validateField(criterionScoreTextMap, map -> map.size() > 0, "criterionScoreMap");
        validateField(criterionScoreTextMap, map -> map.keySet().stream().anyMatch(Objects::nonNull), "criterionScoreMap");
        validateField(criterionScoreTextMap, map -> map.values().stream().anyMatch(Objects::nonNull), "criterionScoreMap");
    }
}
