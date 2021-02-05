package org.reid.mybatis.sqlsession;

import java.io.InputStream;
import java.io.Reader;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.builder.XMLConfigBuilder;

/**
 * <p>
 *	使用构建者模式去创建SqlSessionFactory
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class SqlSessionFactoryBuilder {
	private Configuration configuration;

	public SqlSessionFactory build(InputStream inputStream) {
		// 执行解析流程
		XMLConfigBuilder configBuilder = new XMLConfigBuilder();
		configuration = configBuilder.parse(inputStream);
		return build();
	}

	public SqlSessionFactory build(Reader reader) {
		return null;
	}
	
	private SqlSessionFactory build() {
		//创建SqlSessionFactory对象
		return new DefaultSqlSessionFactory(configuration);
	}

}
