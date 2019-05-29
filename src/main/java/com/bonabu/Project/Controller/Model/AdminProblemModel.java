package com.bonabu.Project.Controller.Model;

import java.util.Date;

public class AdminProblemModel {
    private Long id;
    private  String title,desc,name,email;
    private Integer statuse;
    private String date;
    private Res_AnswerModel answer;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
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

    public Integer getStatuse() {
        return statuse;
    }

    public void setStatuse(Integer statuse) {
        this.statuse = statuse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Res_AnswerModel getAnswer() {
        return answer;
    }

    public void setAnswer(Res_AnswerModel answer) {
        this.answer = answer;
    }
}
