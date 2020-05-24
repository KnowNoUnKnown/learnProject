package com.oracle.xing.streamapi;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by liuyong
 * 2018-12-24  11-32
 */

public class StreamApi {

    public static void main(String [] args){
        System.out.println("run......");
        mapStream();

    }

    public void ArrayStream(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("张三",25));
        personList.add(new Person("李四",31));
        personList.add(new Person("王五",21));
        personList.add(new Person("无极",18));
        personList.add(new Person("赵柳",24));
        personList.add(new Person("孙琪",29));

        // map
        List<String> destList = personList.stream().map(p ->p.getName().toUpperCase()).collect(Collectors.toList());
        System.out.println(destList);
        System.out.println("source...."+JSONObject.toJSONString(personList));

        // filler
        List<Person> personList1;
        personList1 = personList.stream().filter(t -> t.getAge()>25).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(personList1));
        System.out.println("source...."+JSONObject.toJSONString(personList));
        Person person = personList.stream().max(Person::compare).get();
        System.out.println(JSONObject.toJSONString(person));

        personList1 = personList.stream().sorted(Person::compare).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(personList1));

        int sumAge = personList.stream().map(m -> m.getAge()).collect(Collectors.toList()).stream().reduce(0,(a,b)-> a+b);

        System.out.println("sumAge"+sumAge);

        Integer max = personList.stream().map(m ->m.getAge()).collect(Collectors.toList()).stream().max(Integer::compareTo).get();

        System.out.println("max——————>"+max);

        Integer min = personList.stream().map(m ->m.getAge()).collect(Collectors.toList()).stream().min(Integer::compareTo).get();

        System.out.println("min——————>"+min);
    }

    public static void mapStream(){

//        ThreadPoolExecutor executor = new ThreadPoolExecutor();
//        executor.execute();
        HashMap<String ,Integer> map = new HashMap<>();
        map.put("hello",3);
        map.put("world",85);
        map.put("star",16);
        map.put("dust",363);
        map.put("galaxy",17);
        map.put("halo",75);

        Integer max = map.keySet().stream().map(e -> {
            System.out.println(e+"————————>"+map.get(e));
            return map.get(e);
        }).collect(Collectors.toList()).stream().max(Integer::compareTo).get();
        System.out.println("max————————>"+max);

        Integer min = map.keySet().stream().map(e -> map.get(e)).collect(Collectors.toList()).stream().min(Integer::compareTo).get();
        System.out.println("min————————>"+min);

        Integer sum = map.values().stream().reduce(0,( x , y)-> x + y );
        System.out.println(sum);

    }
}