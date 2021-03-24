package com.reid.dao;

import com.reid.pojo.User;

import java.util.List;

/**
 * <p>
 * 类描述
 * </p>
 *
 * @ClassName IHjzlpjgz
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @Data 2021/1/26
 * @Version V1.0
 **/
public interface IUser {

    List<User> findAll();

    User findByCondition(User user);

    User getUser(String id, String name);
}
