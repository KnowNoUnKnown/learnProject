package com.oracle.xing.ttype;

import com.google.common.collect.Lists;
import com.oracle.xing.ttype.model.Girl;
import com.oracle.xing.ttype.model.People;
import com.oracle.xing.ttype.model.Student;
import com.oracle.xing.ttype.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class CollectTest {

    public static void main(String...args){
        listTest();
        modelTest();
        System.out.println("finished!");
    }



    public static void listTest(){
        // 初始化可以放东西,但是add 方法被废弃
        List<? extends People> peoples = Lists.newArrayList(new Student(),new Girl(),new Worker());
/**
        peoples.add(new People());
        peoples.add(new Student());
        peoples.add(new Worker());
        peoples.add(new Girl());
*/
        People people = peoples.get(0);  // 仅仅允许取,不允许添加

        List<? super Student> girls = new ArrayList<>();
        girls.add(new Student());
        girls.add(new Girl());
        // 只能存放 Student 本身及其子类
//        Student student = girls.get(0); 无法get
    }


    public static void modelTest(){
        Tmodel<? extends People> tmodel = new Tmodel<>();
    /**
        tmodel.setData(new People());
        tmodel.setData(new Student());
        tmodel.setData(new Worker());
        tmodel.setData(new Girl());
       */
        People people = tmodel.getData();


        Tmodel<? super Student> smodel = new Tmodel<Student>();
        smodel.setData(new Student());
        smodel.setData(new Girl());

        Object o = smodel.getData();

    }
}