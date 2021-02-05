package com.reid.sqlSession;

/**
 * <p>
 * SQL会话工厂
 * </p>
 *
 * @ClassName SqlSessionFactory
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public interface SqlSessionFactory {

    /**
     * 打开SqlSession会话
     * @return SqlSession
     */
    SqlSession openSession();

}
