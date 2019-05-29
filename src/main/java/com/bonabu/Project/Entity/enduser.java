package com.bonabu.Project.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class enduser extends user  implements Serializable {

    private String name;
    private String family;
    private Integer type;


    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "enduser")
    private List<Problem> problems;

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
