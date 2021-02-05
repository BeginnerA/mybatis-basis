package com.reid.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.reid.io.Resources;
import com.reid.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 * XML配置生成器
 * 解析封装SqlMapConfig.xml数据库连接配置信息
 * </p>
 *
 * @ClassName XMLConfigBuilder
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public class XMLConfigBuilder {

    private final Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 将配置文件进行解析，封装Configuration
     * @param inputStream SqlMapConfig.xml字节流
     * @return Configuration
     */
    @SuppressWarnings("unchecked")
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
        Document document = new SAXReader().read(inputStream);
        //获取根对象<configuration>
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        list.forEach(element -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            Object o = properties.setProperty(name, value);
        });

        //创建连接池
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));

        configuration.setDataSource(comboPooledDataSource);

        //mapper.xml解析
        List<Element> mapperLis = rootElement.selectNodes("//mappers");
        for (Element element : mapperLis){
            String mapperPath = element.attributeValue("resource");
            InputStream resourceAsSteam = Resources.getResourceAsStream(mapperPath);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsSteam);
        }

        return configuration;
    }

}
