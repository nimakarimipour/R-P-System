package ir.ood.backend.userManagement.domain;

import ir.ood.backend.commons.web.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Section implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String title;

    private Section() {
    }

    public Section(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    @Override
    public Map serialize() {
        return mapOf("id", id, "title", title);
    }
}
