package com.oracle.xing.functional;

import com.oracle.xing.model.BaseModel;
import com.oracle.xing.model.School;
import com.oracle.xing.model.User;
import org.springframework.beans.BeanUtils;

public class ContextInitalize {


    public Context getUserContext(){
        return this::userContext;
    }

    private void userContext(BaseModel baseModel)throws Exception{
        User user = new User();
        BeanUtils.copyProperties(baseModel,user);
        user.setGender('男');
        user.setUserName("stardust");
        System.out.println("This is a user context...");
        user.print();

    }

    public Context getSchoolContext(){
        return this::schoolContext;
    }

    private void schoolContext(BaseModel baseModel)throws Exception{
        School school = new School();
        BeanUtils.copyProperties(baseModel,school);
        school.setAddress("华源街道");
        school.setName("希望小学");
        System.out.println("This is a school context...");
        school.print();
    }
}
