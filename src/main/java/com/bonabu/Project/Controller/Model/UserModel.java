package com.bonabu.Project.Controller.Model;

public class UserModel {

    private String email,name,family,type;
    private Integer allmassege,allnoreadmassege;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
