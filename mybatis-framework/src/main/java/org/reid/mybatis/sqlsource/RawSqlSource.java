package org.reid.mybatis.sqlsource;

import org.reid.mybatis.sqlnode.DynamicContext;
import org.reid.mybatis.sqlnode.iface.SqlNode;
import org.reid.mybatis.sqlsource.iface.SqlSource;

/**
 * <p>
 *	封装不包含${}和动态标签的SQL信息
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class RawSqlSource implements SqlSource {

	private final SqlSource sqlSource;
	
	public RawSqlSource(SqlNode rootSqlNode) {
		DynamicContext context = new DynamicContext(null);
		// 将SqlNode处理成一条SQL语句
		rootSqlNode.apply(context);
		// 该SQL语句，此时还包含#{}，不包含${}
		String sql = context.getSql();
		// 通过SqlSourceParser去解析SqlSource中的#{}
		SqlSourceParser sqlSourceParser = new SqlSourceParser();
		// 将解析的结果，最终封装成StaticSqlSource
		sqlSource = sqlSourceParser.parse(sql);
	}

	@Override
	public BoundSql getBoundSql(Object... params) {
		return sqlSource.getBoundSql(params);
	}

}
