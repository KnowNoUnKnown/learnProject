package com.oracle.xing.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class School extends BaseModel implements Serializable {

    private String name;

    private String address;

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
