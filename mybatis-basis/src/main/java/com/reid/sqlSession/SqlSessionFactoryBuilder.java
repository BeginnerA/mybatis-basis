package com.reid.sqlSession;

import com.reid.config.XMLConfigBuilder;
import com.reid.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * <p>
 * 解析配置文件
 * </p>
 *
 * @ClassName SqlSessionFactoryBuilder
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        //第一：使用dom4j解析配置文件，将解析出来的内容封装到容器对象中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        //创建SqlSessionFactory对象
        return new DefaultSqlSessionFactory(configuration);
    }

}
