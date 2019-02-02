package ir.ood.backend.userManagement.domain;

import ir.ood.backend.commons.web.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Map;

@Embeddable
public class SectionAssignment implements Serializable {

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    private boolean isManager;

    void setSection(Section section) {
        this.section = section;
    }

    @Override
    public Map serialize() {
        return mapOf("section", section.serialize(), "isManager", isManager);
    }

    boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    int getSectionId() {
        return section.getId();
    }
}
