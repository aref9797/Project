package com.bonabu.Project.Entity;

import javax.persistence.*;

@Entity
public class Student extends Person {


    private String stdnumber;

    public String getStdnumber() {
        return stdnumber;
    }

    public void setStdnumber(String stdnumber) {
        this.stdnumber = stdnumber;
    }
}
