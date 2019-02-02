package ir.ood.backend.userManagement.domain;

import ir.ood.backend.commons.web.Serializable;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Worker implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private boolean isSuperuser;
    @Column(unique = true)
    private String email;
    private String password;
    @Embedded
    private SectionAssignment sectionAssignment;

    public Worker() {
        this.sectionAssignment = new SectionAssignment();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSuperuser() {
        return isSuperuser;
    }

    public void setSuperuser(boolean superuser) {
        isSuperuser = superuser;
    }

    public void setSection(Section section, boolean isManager) {
        this.sectionAssignment.setSection(section);
        this.sectionAssignment.setManager(isManager);
    }

    public boolean isManager() {
        return sectionAssignment.isManager();
    }

    public int getSectionId() {
        return sectionAssignment.getSectionId();
    }

    @Override
    public Map serialize() {
        return mapOf(
                "name", name,
                "id", id,
                "isSuperuser", isSuperuser,
                "email", email,
                "sectionAssignment", sectionAssignment.serialize()
        );
    }
}
