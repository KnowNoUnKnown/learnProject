package com.oracle.xing.streamapi;


/**
 * Created by liuyong
 * 2018-12-24  16-29
 */

public class Person {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static int compare(Person a, Person b){
        return b.getAge()-a.getAge();
    }
}
