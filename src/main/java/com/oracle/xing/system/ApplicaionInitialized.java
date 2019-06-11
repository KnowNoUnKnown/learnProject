package com.oracle.xing.system;

import com.alibaba.fastjson.JSON;
import com.oracle.xing.spi.MessageHandle;
import com.oracle.xing.spi.PayMessageHandle;
import com.oracle.xing.spi.RollBackMessageHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Created by liuyong
 * 2018-12-06  15-08
 */
@Component
public class ApplicaionInitialized implements ApplicationRunner {

    @Autowired
    private RollBackMessageHandle rollBackMessageHandle;

    @Autowired
    private PayMessageHandle payMessageHandle;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        /**
         * 通过spi 加载类,
         * 然后将spi 类交给spring管理
         */
        ServiceLoader<MessageHandle> serviceLoader = ServiceLoader.load(MessageHandle.class);
        Iterator<MessageHandle> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            MessageHandle m = iterator.next();
            if(m instanceof PayMessageHandle){
                payMessageHandle = (PayMessageHandle)m;
            }
            if(m instanceof RollBackMessageHandle){
                rollBackMessageHandle = (RollBackMessageHandle)m;
            }
            m.execute();
            System.out.println(JSON.toJSONString(m));
            System.out.println(m.getClass().getClassLoader()+"\n\n\n");
        }

        System.out.println(JSON.toJSONString(rollBackMessageHandle));
        System.out.println("autowired  -----rollBackMessageHandle    "+rollBackMessageHandle.getClass().getClassLoader());


        System.out.println(JSON.toJSONString(payMessageHandle));
        System.out.println("autowired  -----payMessageHandle    "+payMessageHandle.getClass().getClassLoader());

        /**
         * 获取接口下所有实现类
         */
        Map<String , MessageHandle> map = webApplicationContext.getBeansOfType(MessageHandle.class);
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}
