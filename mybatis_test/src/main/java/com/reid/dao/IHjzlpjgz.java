package com.reid.dao;

import com.reid.pojo.THjzlpjgz;

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
public interface IHjzlpjgz {

    List<THjzlpjgz> findAll();

    THjzlpjgz findByCondition(THjzlpjgz hjzlpjgz);

    THjzlpjgz gethjzlpj(String id, String userid);
}
