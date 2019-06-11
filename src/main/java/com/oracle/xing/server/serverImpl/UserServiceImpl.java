package com.oracle.xing.server.serverImpl;

import com.oracle.xing.mapper.UserMapper;
import com.oracle.xing.model.User;
import com.oracle.xing.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */
@Service
public class UserServiceImpl implements UserServer{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getUsers(User user) {
        return userMapper.query(user);
    }
}
