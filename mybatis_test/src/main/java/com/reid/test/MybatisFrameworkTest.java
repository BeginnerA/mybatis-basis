package com.reid.test;

import com.reid.dao.IHjzlpjgz;

import com.reid.pojo.THjzlpjgz;
import org.junit.Test;
import org.reid.mybatis.io.Resources;
import org.reid.mybatis.sqlsession.SqlSession;
import org.reid.mybatis.sqlsession.SqlSessionFactory;
import org.reid.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 类描述
 * </p>
 *
 * @ClassName MybatisFrameworkTest
 * @Author REID
 * @Data 2021/1/28
 * @Version V1.0
 **/
public class MybatisFrameworkTest {
    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsStream("SqlMapConfig.xml");
        //构建者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        THjzlpjgz hjzlpjgz = new THjzlpjgz();
        hjzlpjgz.setId("234f06e2dd551699e9044e73d482ccc0");
        hjzlpjgz.setUserid("1");
        /*THjzlpjgz th = sqlSession.selectOne("user.findByCondition", hjzlpjgz);
        System.out.println(th);

        List<THjzlpjgz> th2 = sqlSession.selectList("user.findAll");
        System.out.println(th2.size());*/

        IHjzlpjgz hjzlpjgz1 = sqlSession.getMapper(IHjzlpjgz.class);
        /*List<THjzlpjgz> all = hjzlpjgz1.findAll();
        System.out.println(all.size());*/
        THjzlpjgz byCondition = hjzlpjgz1.findByCondition(hjzlpjgz);
        System.out.println(byCondition);
        THjzlpjgz byCondition1 = hjzlpjgz1.gethjzlpj("234f06e2dd551699e9044e73d482ccc0", "1");
        System.out.println(byCondition1);
    }


}
