package com.oracle.xing.functional;

import com.oracle.xing.model.BaseModel;

import java.time.LocalDateTime;

/**
 * 函数式编程风格
 *
 * 1、要求addContext 的方法入参必须有函数式注解
 *  contextPool.addContext((contex)->{
 *             System.out.println("附加Context");
 *             contex.setId("000000");
 *             contex.setUpdateAt(null);
 *             contex.setCreateAt(null);
 *             contex.print();
 *         });
 *
 */
public class ContextRun {

    public static void main(String[] args) {
        BaseModel model = new BaseModel();
        model.setArchive(0);
        model.setCreateAt(LocalDateTime.now());
        model.setUpdateAt(LocalDateTime.now());
        model.setId("123456789");
        ContextInitalize init = new ContextInitalize();
        ContextPool contextPool = new ContextPool(init.getUserContext(),init.getSchoolContext());
        contextPool.addContext((contex)->{
            System.out.println("附加Context");
            contex.setId("000000");
            contex.setUpdateAt(null);
            contex.setCreateAt(null);
            contex.print();
        });

        contextPool.getContexts().forEach(e ->{
            try {
                e.invoke(model);
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }
        });
    }

}
