package com.oracle.xing.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by liuyong
 * 2019-01-16  10-30
 */

public class UnfaseT {

    public static void main(String...args)throws Exception{
        Class clazz = Unsafe.class;
        Field field = clazz.getDeclaredField("theUnsafe");
        field.setAccessible(true);

        Unsafe unsafe = (Unsafe)field.get(null);
        User source = (User) unsafe.allocateInstance(User.class);
        source.setAge(15);
        source.setName("测试");
        System.out.println(source.toString());

    }


    class User{

        private String name;

        private Integer age;

        public User() {
            System.out.println("none——————————>"+null);
        }

        public User(String name) {
            System.out.println("name——————————>"+name);
            this.name = name;
        }

        public User(Integer age) {
            System.out.println("age——————————>"+age);
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return this.getName()+":"+this.getAge();
        }
    }

}
