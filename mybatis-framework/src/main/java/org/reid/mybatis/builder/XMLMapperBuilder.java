package org.reid.mybatis.builder;

import java.util.List;

import org.dom4j.Element;
import org.reid.mybatis.config.Configuration;

/**
 * <p>
 * 专门来解析映射文件的类
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class XMLMapperBuilder {
	private final Configuration configuration;

	public XMLMapperBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	@SuppressWarnings("unchecked")
	public void parse(Element rootElement) {
		//为了方便管理statement，需要使用statement唯一标识
		String namespace = rootElement.attributeValue("namespace");
		
		List<Element> selectElements = rootElement.elements("select");
		for (Element selectElement : selectElements) {
			XMLStatementBuilder	statementBuilder = new XMLStatementBuilder(configuration);
			statementBuilder.parseStatement(selectElement,namespace);
		}
		
	}


}
