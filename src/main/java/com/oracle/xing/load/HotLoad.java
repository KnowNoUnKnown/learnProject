package com.oracle.xing.load;

import com.oracle.xing.load.listen.FileListener;

public class HotLoad {

    /**
     * 示例
     * @param args
     * @throws Exception
     */
    public static void main(String... args)throws Exception{
        FileListener fileListener = new FileListener();
        fileListener.listen("E:\\LearnProject\\Java\\learnProject\\src\\main\\java\\com\\oracle\\xing\\load\\basedo");
        System.out.println("Server start successfull!");
    }

    /**
     * 启动
     * @param dir 动态加载的文件夹
     * @throws Exception
     */
    public static Boolean start(String dir)throws Exception{
        FileListener fileListener = new FileListener();
        fileListener.listen(dir);
        return Boolean.TRUE;
    }
}