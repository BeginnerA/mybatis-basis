package org.reid.mybatis.sqlsource;

import java.util.List;

import org.reid.mybatis.sqlsource.iface.SqlSource;

/**
 * <p>
 *	封装静态SQL源
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class StaticSqlSource implements SqlSource {

	private final String sql;

	private final List<ParameterMapping> parameterMappings;

	public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
		super();
		this.sql = sql;
		this.parameterMappings = parameterMappings;
	}

	@Override
	public BoundSql getBoundSql(Object... params) {
		return new BoundSql(sql, parameterMappings);
	}

}
