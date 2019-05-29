package com.bonabu.Project.Controller.Model;

import java.util.Date;
public class Res_ProblemModel {

    private Long id;
    private String title,desc,adminTitle;
    private String date;
    private Integer statuse;
    private Res_AnswerModel answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatuse() {
        return statuse;
    }

    public void setStatuse(Integer statuse) {
        this.statuse = statuse;
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

    public String getAdminTitle() {
        return adminTitle;
    }

    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
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
