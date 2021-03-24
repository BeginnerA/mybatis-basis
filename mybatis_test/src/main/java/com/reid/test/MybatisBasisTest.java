/*
package com.reid.test;

import com.reid.dao.IUser;
import com.reid.io.Resources;
import com.reid.pojo.User;
import com.reid.sqlSession.DefaultSqlSession;
import com.reid.sqlSession.SqlSession;
import com.reid.sqlSession.SqlSessionFactory;
import com.reid.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

*/
/**
 * <p>
 * 类描述
 * </p>
 *
 * @ClassName IPersistenceTest
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @Data 2021/1/15
 * @Version V1.0
 **//*

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("SqlMapConfig.xml");
        //构建者模式
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().Build(resourceAsSteam);
        //工厂模式
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User user = new User();
        user.setId("1f24d6ed42597df85ad7c70f4e05daed");
        user.setName("1");
        */
        /*User th = sqlSession.selectOne("user.findByCondition", user);
        System.out.println(th);

        List<User> th2 = sqlSession.selectList("user.findAll");
        System.out.println(th2.size());*//*


        IUser iuser = sqlSession.getMapper(IUser.class);
        List<User> all = iuser.findAll();
        System.out.println(all.size());
        */
/*User byCondition = iuser.findByCondition(user);
        System.out.println(byCondition);*//*

    }


}
*/
