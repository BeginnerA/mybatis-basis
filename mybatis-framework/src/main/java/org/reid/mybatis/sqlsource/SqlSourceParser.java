package org.reid.mybatis.sqlsource;

import org.reid.mybatis.sqlsource.iface.SqlSource;
import org.reid.mybatis.utils.GenericTokenParser;
import org.reid.mybatis.utils.ParameterMappingTokenHandler;

/**
 * <p>
 *	SQL源解析器
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class SqlSourceParser {

	public SqlSource parse(String sql) {
		// 先处理${}，将处理之后的SQL语句，追加到context中
		ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
		GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
		// 此处获取到的sql语句，是完全符合JDBC执行要求的语句
		sql = tokenParser.parse(sql);
		// 将解析之后的sql语句和参数集合都封装到StaticSqlSource中
		return new StaticSqlSource(sql, tokenHandler.getParameterMappings());
	}

}
