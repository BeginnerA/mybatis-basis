package com.reid.test;

import com.reid.dao.IUser;

import com.reid.pojo.User;
import org.junit.Test;
import org.reid.mybatis.io.Resources;
import org.reid.mybatis.sqlsession.SqlSession;
import org.reid.mybatis.sqlsession.SqlSessionFactory;
import org.reid.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;

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
        User user = new User();
        user.setId("1331884149004910593");
        user.setName("张三");
        /*User th = sqlSession.selectOne("user.findByCondition", user);
        System.out.println(th);

        List<User> th2 = sqlSession.selectList("user.findAll");
        System.out.println(th2.size());*/

        IUser iUser = sqlSession.getMapper(IUser.class);
        /*List<User> all = iuser.findAll();
        System.out.println(all.size());*/
        User byCondition = iUser.findByCondition(user);
        System.out.println(byCondition);
        User byCondition1 = iUser.getUser("1331884149004910593", "张三");
        System.out.println(byCondition1);
    }


}
