package com.oracle.xing.load;

import com.oracle.xing.load.listen.FileListener;

public class HotLoad {

    public static void main(String... args)throws Exception{
        FileListener fileListener = new FileListener();
        fileListener.listen("E:\\LearnProject\\Java\\learnProject\\src\\main\\java\\com\\oracle\\xing\\load\\basedo");
        System.out.println("Server start successfull!");
    }
}