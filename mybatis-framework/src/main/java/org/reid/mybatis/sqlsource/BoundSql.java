package org.reid.mybatis.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *	解析后的sql语句和参数
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class BoundSql {

	/**
	 * JDBC可以执行的SQL语句
	 */
	private String sql;

	/**
	 * 参数集合
	 */
	private List<ParameterMapping> parameterMappings = new ArrayList<>();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

	public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
		super();
		this.sql = sql;
		this.parameterMappings = parameterMappings;
	}
	
}
