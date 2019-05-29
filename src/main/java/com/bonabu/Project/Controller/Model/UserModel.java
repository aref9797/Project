package com.bonabu.Project.Controller.Model;

public class UserModel {

    private String email,name;
    private Integer allmassege,allnoreadmassege;

    public Integer getAllmassege() {
        return allmassege;
    }

    public void setAllmassege(Integer allmassege) {
        this.allmassege = allmassege;
    }

    public Integer getAllnoreadmassege() {
        return allnoreadmassege;
    }

    public void setAllnoreadmassege(Integer allnoreadmassege) {
        this.allnoreadmassege = allnoreadmassege;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
