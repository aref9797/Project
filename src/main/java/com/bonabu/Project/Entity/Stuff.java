package com.bonabu.Project.Entity;


import javax.persistence.*;

@Entity

public class Stuff extends Person {

    private String personalcode;

    public String getPersonalcode() {
        return personalcode;
    }

    public void setPersonalcode(String personalcode) {
        this.personalcode = personalcode;
    }
}
