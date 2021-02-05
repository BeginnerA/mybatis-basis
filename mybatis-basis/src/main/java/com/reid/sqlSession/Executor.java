package com.reid.sqlSession;

import com.reid.pojo.Configuration;
import com.reid.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * sql执行者
 * </p>
 *
 * @ClassName Executor
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public interface Executor {

    /**
     * 查询
     * @param configuration 数据库连接信息
     * @param mappedStatement sql语句信息
     * @param params 可变参数
     * @param <E> E
     * @return List<E>
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
