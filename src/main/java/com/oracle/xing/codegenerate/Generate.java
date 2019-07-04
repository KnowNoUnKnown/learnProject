package com.oracle.xing.codegenerate;

import com.cn.stardust.star.codegen.CodeGenerate;
import com.cn.stardust.star.codegen.typeconvert.DataTypeConvert;
import com.google.common.collect.Lists;
import com.oracle.xing.Application;

/**
 * https://github.com/oraclexing
 * <p>
 *
 * @author stardust
 * @version 1.0.0
 */
public class Generate {

    public static void main(String...args){
        CodeGenerate codeGenerate = CodeGenerate.getInstance("192.168.1.101",
                "000000","000000","000000",new DataTypeConvert());
        codeGenerate.generate(Lists.newArrayList("user1","user2"),
                "E:", Application.class);
    }
}