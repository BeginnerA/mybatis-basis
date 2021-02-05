package org.reid.mybatis.executor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.mapping.MappedStatement;
import org.reid.mybatis.sqlsource.BoundSql;
import org.reid.mybatis.sqlsource.ParameterMapping;
import org.reid.mybatis.utils.NameMaster;

/**
 * <p>
 *	简单执行JDBC代码
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class SimpleExecutor extends BaseExecutor {

	@Override
	public List<Object> queryFromDataBase(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object... params) {
		List<Object> results = new ArrayList<Object>();
		try {
			// 获取连接
			Connection connection = getConnection(configuration);
			// 获取SQL语句
			String sql = boundSql.getSql();
			// 判断创建哪种Statement
			ResultSet resultSet = null;
			if ("prepared".equals(mappedStatement.getStatementType())) {
				// 创建Statement
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				// 设置参数
				parameterize(prepareStatement, mappedStatement, boundSql, params);
				// 执行Statement
				resultSet = prepareStatement.executeQuery();
			} else if ("callable".equals(mappedStatement.getStatementType())) {
				// 创建Statement
				// 设置参数
				// 执行Statement
			}

			// 处理结果集
			if (resultSet != null) {
				handleResultSet(mappedStatement, resultSet, results);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	/**
	 * 处理结果集
	 * @param mappedStatement 映射语句
	 * @param resultSet 结果集
	 * @param results 结果
	 * @throws Exception 执行异常
	 */
	private void handleResultSet(MappedStatement mappedStatement, ResultSet resultSet, List<Object> results)
			throws Exception {
		Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
		// 没遍历一次是一行数据，对应一个映射对象
		while (resultSet.next()) {
			Object result = resultTypeClass.newInstance();
			// 每一列，对应映射对象的一个属性
			// 列的名称要和对象的属性名称一致
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				String columnName = metaData.getColumnName(i + 1);
				//字段名特殊处理
				columnName = NameMaster.resetName(columnName);
				Field field = resultTypeClass.getDeclaredField(columnName);
				field.setAccessible(true);
				field.set(result, resultSet.getObject(i + 1));
			}

			results.add(result);
		}

	}

	/**
	 * 设置参数
	 * @param prepareStatement 预编译的SQL语句的对象
	 * @param mappedStatement 映射语句
	 * @param boundSql 可执行的SQL语句
	 * @param params 参数
	 * @throws Exception 执行异常
	 */
	private void parameterize(PreparedStatement prepareStatement, MappedStatement mappedStatement, BoundSql boundSql, Object... params) throws Exception {
		// 先判断入参类型
		Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();
		if (parameterTypeClass == Integer.class) {
			prepareStatement.setObject(1, Integer.parseInt(params[0].toString()));
		} else if (parameterTypeClass == String.class) {
			prepareStatement.setObject(1, params[0].toString());
		} else if (parameterTypeClass == Map.class) {

		} else if (parameterTypeClass == null) {
			for (int i = 1; i <= params.length; i++) {
				prepareStatement.setObject(i, params[i].toString());
			}
		} else {// 自定义对象类型
			List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

			for (int i = 0; i < parameterMappings.size(); i++) {
				// 获取#{}中的属性名称
				ParameterMapping parameterMapping = parameterMappings.get(i);

				String name = parameterMapping.getName();

				// 根据属性名称，获取入参对象中对应的属性的值
				// 要求#{}中的属性名称和入参对象中的属性名称一致
				Field declaredField = parameterTypeClass.getDeclaredField(name);
				declaredField.setAccessible(true);
				Object value = declaredField.get(params[0]);

				prepareStatement.setObject(i + 1, value);
			}
		}

	}

	/**
	 * 获得连接
	 * @param configuration 数据库连接信息
	 * @return 数据库连接
	 */
	private Connection getConnection(Configuration configuration) {
		Connection connection = null;
		try {
			DataSource dataSource = configuration.getDataSource();
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
