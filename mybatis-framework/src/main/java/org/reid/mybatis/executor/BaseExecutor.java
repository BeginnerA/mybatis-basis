package org.reid.mybatis.executor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.mapping.MappedStatement;
import org.reid.mybatis.executor.iface.Executor;
import org.reid.mybatis.sqlsource.BoundSql;

/**
 * <p>
 *	基本执行器
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public abstract class BaseExecutor implements Executor {

	private final Map<String, List<Object>> oneLevelCache = new HashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object... params) {
		// 调用SqlSource获取BoundSql
		BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(params);
		// 处理一级缓存
		List<Object> results = oneLevelCache.get(boundSql.getSql());
		if (results != null) {
			return (List<T>) results;
		}

		// 查询数据库
		results = queryFromDataBase(configuration, mappedStatement, boundSql, params);

		oneLevelCache.put(boundSql.getSql(), results);
		return (List<T>) results;
	}

	/**
	 * 从数据库查询
	 * @param configuration 数据库连接信息
	 * @param mappedStatement sql语句信息
	 * @param boundSql sql
	 * @param params  可变参数
	 * @return 数据集
	 */
	public abstract List<Object> queryFromDataBase(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object... params);

}
