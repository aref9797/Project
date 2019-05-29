package com.bonabu.Project.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "problem")
public class Problem implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;


    private String description;

    private Integer statuse;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enduser_id")
    private enduser enduser;


    @OneToOne(mappedBy = "problem")
    private Answer amswer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private admin admin;

    public com.bonabu.Project.Entity.admin getAdmin() {
        return admin;
    }

    public void setAdmin(com.bonabu.Project.Entity.admin admin) {
        this.admin = admin;
    }

    public Integer getStatuse() {
        return statuse;
    }

    public void setStatuse(Integer statuse) {
        this.statuse = statuse;
    }

    public Answer getAmswer() {
        return amswer;
    }

    public void setAmswer(Answer amswer) {
        this.amswer = amswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public com.bonabu.Project.Entity.enduser getEnduser() {
        return enduser;
    }

    public void setEnduser(com.bonabu.Project.Entity.enduser enduser) {
        this.enduser = enduser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
