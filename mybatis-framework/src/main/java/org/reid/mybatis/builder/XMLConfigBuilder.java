package org.reid.mybatis.builder;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.io.Resources;
import org.reid.mybatis.utils.DocumentUtils;

/**
 * <p>
 * 专门用来解析全局配置文件的类
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class XMLConfigBuilder {

	private final Configuration configuration;

	public XMLConfigBuilder() {
		this.configuration = new Configuration();
	}

	/**
	 * 从根标签开始解析全局配置文件
	 * 
	 * @param inputStream xml文件流
	 * @return 映射文件
	 */
	public Configuration parse(InputStream inputStream) {
		Document document = DocumentUtils.readDocument(inputStream);
		// <configuration>
		assert document != null;
		Element rootElement = document.getRootElement();
		parserConfiguration(rootElement);
		return configuration;
	}

	/**
	 * 解析器配置
	 * @param rootElement <configuration>元素
	 */
	private void parserConfiguration(Element rootElement) {
		Element environmentsElement = rootElement.element("environments");
		parseEnvironmentsElement(environmentsElement);
		Element mappersElement = rootElement.element("mappers");
		parseMappersElement(mappersElement);
	}

	/**
	 * 解析Mappers元素
	 * @param mappersElement 元素
	 */
	@SuppressWarnings("unchecked")
	private void parseMappersElement(Element mappersElement) {
		List<Element> mapperElements = mappersElement.elements("mapper");
		for (Element mapperElement : mapperElements) {
			parseMapper(mapperElement);
		}
	}

	/**
	 * 解析<mapper>标签
	 * @param mapperElement 元素
	 */
	private void parseMapper(Element mapperElement) {
		String resource = mapperElement.attributeValue("resource");
		
		InputStream inputStream = Resources.getResourceAsStream(resource);
		Document document = DocumentUtils.readDocument(inputStream);
		assert document != null;
		//创建专门来解析映射文件的解析类
		XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(configuration);
		mapperBuilder.parse(document.getRootElement());
	}

	/**
	 * 解析环境元素
	 * @param environmentsElement <environments>元素
	 */
	@SuppressWarnings("unchecked")
	private void parseEnvironmentsElement(Element environmentsElement) {
		String defaultEnvId = environmentsElement.attributeValue("default");
		if (defaultEnvId == null || "".equals(defaultEnvId)) {
			return;
		}
		List<Element> elements = environmentsElement.elements("environment");
		for (Element envElement : elements) {
			String id = envElement.attributeValue("id");
			if (defaultEnvId.equals(id)) {
				parseDataSource(envElement.element("dataSource"));
			}
		}
	}

	/**
	 * 解析数据源
	 * @param dbElement <dataSource>元素
	 */
	@SuppressWarnings("unchecked")
	private void parseDataSource(Element dbElement) {
		Properties properties = new Properties();

		List<Element> propertyElements = dbElement.elements();
		for (Element prop : propertyElements) {
			String name = prop.attributeValue("name");
			String value = prop.attributeValue("value");
			properties.put(name, value);
		}
		String dbType = dbElement.attributeValue("type");
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		if ("DBCP".equals(dbType)) {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			
			configuration.setDataSource(dataSource);
		}else {
			try {
				ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
				comboPooledDataSource.setDriverClass(driver);
				comboPooledDataSource.setJdbcUrl(url);
				comboPooledDataSource.setUser(username);
				comboPooledDataSource.setPassword(password);

				configuration.setDataSource(comboPooledDataSource);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
