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
        ServiceLoader<MessageHandle> serviceLoader = ServiceLoader.load(MessageHandle.class);
        Iterator<MessageHandle> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            MessageHandle m = iterator.next();
            m.execute();
            System.out.println(JSON.toJSONString(m));
            System.out.println(m.getClass().getClassLoader()+"\n\n\n");
        }

        System.out.println(JSON.toJSONString(rollBackMessageHandle));
        System.out.println("autowired  -----rollBackMessageHandle    "+rollBackMessageHandle.getClass().getClassLoader());


        System.out.println(JSON.toJSONString(payMessageHandle));
        System.out.println("autowired  -----payMessageHandle    "+payMessageHandle.getClass().getClassLoader());

        Map<String , MessageHandle> map = webApplicationContext.getBeansOfType(MessageHandle.class);
        System.out.println(JSON.toJSONString(map));
    }
}
