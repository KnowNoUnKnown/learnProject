package com.oracle.xing.ttype.model;

public class Worker extends People {

    private Integer selary;

    private Integer job;


    public Integer getSelary() {
        return selary;
    }

    public void setSelary(Integer selary) {
        this.selary = selary;
    }

    public Integer getJob() {
        return job;
    }

    public void setJob(Integer job) {
        this.job = job;
    }
}
