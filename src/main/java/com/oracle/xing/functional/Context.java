package com.oracle.xing.functional;

import com.oracle.xing.model.BaseModel;



/**
 * 函数式接口编程
 *
 *  1、接口定义必须为interface
 *  2、只能有一个抽象方法
 *  3、可以有多个公共的默认方法
 *  4、可以有多个静态方法
 *  5、只能有一个Object继承的equals
 *
 *
 *  小计
 *      1、函数式接口使用与对某一对象进行顺序处理。
 *      2、延迟执行（先定义方法行为，具体执行延后），类似于接口回调。
 *      3、随时定义可变函数，减少代码量，同时降低可读性。
 *      4、其他特性待测试，总结
 *
 */



@FunctionalInterface
public interface Context {

    void invoke(BaseModel baseModel)throws Exception;

    public default void run(BaseModel baseModel)throws Exception{

    }

    public default void start(BaseModel baseModel)throws Exception{

    }

    @Override
    public boolean equals(Object obj);

    public static Boolean compare(){
        return true;
    }


    public static Boolean compare(Object o){
        return true;
    }


}
