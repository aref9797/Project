package com.bonabu.Project.Entity;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class enduser extends user{

    private String name_family;

    public String getName_family() {
        return name_family;
    }

    public void setName_family(String name_family) {
        this.name_family = name_family;
    }
}
