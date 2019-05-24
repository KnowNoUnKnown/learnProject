package com.oracle.xing.serialize;


import com.google.common.collect.Lists;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SerializeTest {

    public static void main(String...args)throws Exception{
        serializeTest();
        arrayTest();
    }

    public static void serializeTest()throws Exception{
        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("date",new Date());
        concurrentHashMap.put("localTime", LocalTime.now());
        concurrentHashMap.put("localDate", LocalDate.now());
        Phone phone = new Phone();
        phone.setColor("蓝色");
        phone.setName("华为");
        phone.setPrice(BigDecimal.valueOf(1399.99));
        phone.setLocalDateTime(LocalDateTime.now());
        phone.setConcurrentHashMap(concurrentHashMap);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(FstUtil.serializer(phone));
        Phone copy = FstUtil.unserializer(outputStream.toByteArray());
        System.out.println(phone.equals(copy));
    }

    public static void objectCopyTest(){
        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("date",new Date());
        concurrentHashMap.put("localTime", LocalTime.now());
        concurrentHashMap.put("localDate", LocalDate.now());
        Phone phone = new Phone();
        phone.setColor("蓝色");
        phone.setName("华为");
        phone.setPrice(BigDecimal.valueOf(1399.99));
        phone.setLocalDateTime(LocalDateTime.now());
        phone.setConcurrentHashMap(concurrentHashMap);
        Phone copy = FstUtil.copyObject(phone);
        System.out.println(phone.equals(copy));
    }

    public static void listCopyTest(){
        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("date",new Date());
        concurrentHashMap.put("localTime", LocalTime.now());
        concurrentHashMap.put("localDate", LocalDate.now());
        Phone phone = new Phone();
        phone.setColor("蓝色");
        phone.setName("华为");
        phone.setPrice(BigDecimal.valueOf(1399.99));
        phone.setLocalDateTime(LocalDateTime.now());
        phone.setConcurrentHashMap(concurrentHashMap);
        List<Phone> phones = Lists.newArrayList(phone,FstUtil.copyObject(phone),FstUtil.copyObject(phone),FstUtil.copyObject(phone));
        List<Phone> copys = FstUtil.copyObject(phones);
        System.out.println(phones.equals(copys));
    }



    public static void arrayTest(){
        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("date",new Date());
        concurrentHashMap.put("localTime", LocalTime.now());
        concurrentHashMap.put("localDate", LocalDate.now());
        Phone phone = new Phone();
        phone.setColor("蓝色");
        phone.setName("华为");
        phone.setPrice(BigDecimal.valueOf(1399.99));
        phone.setLocalDateTime(LocalDateTime.now());
        phone.setConcurrentHashMap(concurrentHashMap);
        Phone[] phones = {phone,FstUtil.copyObject(phone),FstUtil.copyObject(phone),FstUtil.copyObject(phone),FstUtil.copyObject(phone)};
        Phone[] copys = FstUtil.copyObject(phones);
        System.out.println(phones.equals(copys));
    }
}