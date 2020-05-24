package com.oracle.xing.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/15.
 */
public class User extends BaseModel implements Serializable {

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

    public User(String userName, char gender) {
        this.userName = userName;
        this.gender = gender;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", gender=" + gender +
                "} " + super.toString();
    }
}
