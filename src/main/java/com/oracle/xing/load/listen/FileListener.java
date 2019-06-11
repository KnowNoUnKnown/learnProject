package com.oracle.xing.load.listen;

import com.oracle.xing.load.classload.FileClassLoader;
import com.oracle.xing.load.compile.JCompiler;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.util.Map;

public class FileListener {

    private FileClassLoader fileClassLoader = new FileClassLoader();

    public void listen(String filePath)throws Exception{
        FileFilter filter= FileFilterUtils.and(FileFilterUtils.trueFileFilter());
        FileAlterationObserver filealtertionObserver=new FileAlterationObserver(filePath, filter);
        filealtertionObserver.addListener(new FileAlterationListenerAdaptor(){
            @Override
            public void onDirectoryCreate(File directory)
            {
                // TODO Auto-generated method stub
                System.out.println("onDirectoryCreate");
                super.onDirectoryCreate(directory);
            }

            @Override
            public void onDirectoryDelete(File directory)
            {
                // TODO Auto-generated method stub
                System.out.println("onDirectoryDelete");
                super.onDirectoryDelete(directory);
            }

            @Override
            public void onFileChange(File file)
            {
                // TODO Auto-generated method stub
                System.out.println("onFileChange-------------->"+file.getName());
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try {
                    Files.copy(file.toPath(),outputStream);
                    /**
                     * 编译.java文件
                     */

                    Map<String, byte[]> map = JCompiler.compiler(file.getName().split("\\.")[0], outputStream.toString());
                    for(String key : map.keySet()){
                        /**
                         * 加载.class文件
                         */
                        fileClassLoader.loadClass(key,map.get(key));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                super.onFileChange(file);
            }

            @Override
            public void onFileCreate(File file)
            {
                System.out.println("onFileCreate"+file.getAbsoluteFile());
                super.onFileCreate(file);
            }

            @Override
            public void onFileDelete(File file)
            {
                // TODO Auto-generated method stub
                System.out.println("onFileDelete");
                super.onFileDelete(file);
            }

            @Override
            public void onStart(FileAlterationObserver observer)
            {
                // TODO Auto-generated method stub
//                System.out.println("onStart");
                super.onStart(observer);
            }
        });
        FileAlterationMonitor monitor = new FileAlterationMonitor(1000);
        monitor.addObserver(filealtertionObserver);
        monitor.start();
    }
}