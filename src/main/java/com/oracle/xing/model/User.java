package com.oracle.xing.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Administrator on 2018/10/15.
 */
public class User implements Serializable {

    private String id;

    private String userName;

    private char gender;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String id, String userName, char gender) {
        this.id = id;
        this.userName = userName;
        this.gender = gender;
    }

    public User(String userName, char gender) {
        this.userName = userName;
        this.gender = gender;
        this.id = UUID.randomUUID().toString().replaceAll("-","");
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                '}';
    }
}
