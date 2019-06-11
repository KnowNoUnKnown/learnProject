package com.oracle.xing.load.basedo;

public class People implements Live {

    @Override
    public void run() {
        System.out.println("People run......");
    }

    @Override
    public String getName() {
        return "People saying something true!";
    }
}
