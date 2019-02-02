package ir.ood.backend.evaluation.domain.grading;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private int id;
    private int value;
    private String text;

    private Grade() {
    }

    public Grade(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
