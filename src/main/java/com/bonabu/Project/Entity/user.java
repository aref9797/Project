package com.bonabu.Project.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class user {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "user_id_seq")
    @SequenceGenerator(name="user_id_seq", sequenceName = "user_id_seq", allocationSize=1)
    private Long id;

    @Column(unique=true, nullable=false,length = 10)
    private String usename;

    @Column(unique=true, nullable=false,length = 20)
    private String email;

    @Column(unique=true, nullable=false)
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
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
}
