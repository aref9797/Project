package com.bonabu.Project.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class admin extends user implements Serializable {


    @Column(name = "AdminTitle")
    private String adminTitle;


    @OneToMany(mappedBy = "admin")
    private List<Problem> problems;

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public String getAdminTitle() {
        return adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }
}
