package org.reid.mybatis.builder;

import org.dom4j.Element;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.mapping.MappedStatement;
import org.reid.mybatis.sqlsource.iface.SqlSource;

/**
 * <p>
 * 用来解析映射文件中的select/insert等CRUD标签
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class XMLStatementBuilder {
	private final Configuration configuration;

	public XMLStatementBuilder(Configuration configuration) {
		this.configuration = configuration;

	}

	/**
	 * 解析语句
	 * @param selectElement 选择元素
	 * @param namespace 命名空间
	 */
	public void parseStatement(Element selectElement, String namespace) {
		String statementId = selectElement.attributeValue("id");

		if (statementId == null) {
			return;
		}
		// 一个CURD标签对应一个MappedStatement对象
		// 一个MappedStatement对象由一个statementId来标识，所以保证唯一性
		// statementId = namespace + "." + CRUD标签的id属性
		statementId = namespace + "." + statementId;

		String parameterType = selectElement.attributeValue("parameterType");
		Class<?> parameterClass = resolveType(parameterType);

		String resultType = selectElement.attributeValue("resultType");
		Class<?> resultClass = resolveType(resultType);

		String statementType = selectElement.attributeValue("statementType");
		statementType = statementType == null || "".equals(statementType) ? "prepared" : statementType;

		// 解析SQL信息
		SqlSource sqlSource = createSqlSource(selectElement);

		// TODO 建议使用构建者模式去优化
		MappedStatement mappedStatement = new MappedStatement(statementId, parameterClass, resultClass, statementType, sqlSource);
		configuration.addMappedStatement(statementId, mappedStatement);
	}

	private SqlSource createSqlSource(Element selectElement) {
		XMLScriptParser scriptParser = new XMLScriptParser();
		return scriptParser.parseScriptNode(selectElement);
	}

	/**
	 * 装载一个类并且对其进行实例化的操作
	 * @param parameterType 类路径
	 * @return 具体类
	 */
	private Class<?> resolveType(String parameterType) {
		if (parameterType != null) {
			try {
				return Class.forName(parameterType);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
