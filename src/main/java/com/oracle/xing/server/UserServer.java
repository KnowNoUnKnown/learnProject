package com.oracle.xing.server;

import com.oracle.xing.model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */
public interface UserServer {

    User getUser(String id);

    List<User> getUsers(User user);

}
