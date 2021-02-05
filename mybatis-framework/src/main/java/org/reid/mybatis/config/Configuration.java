package org.reid.mybatis.config;

import org.reid.mybatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * <p>
 * 封装了全局配置文件和映射文件中的信息
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class Configuration {

	private DataSource dataSource;

	/**
	 * key：statementId
	 * value：封装好的mappedStatement对象
	 */
	private final Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
		this.mappedStatements.put(statementId, mappedStatement);
	}

	public MappedStatement getMappedStatementMap(String statementId) {
		return this.mappedStatements.get(statementId);
	}

}
