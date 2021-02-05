package org.reid.mybatis.sqlsource.iface;

import org.reid.mybatis.sqlsource.BoundSql;

/**
 * <p>
 *	SQL源
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public interface SqlSource {

	/**
	 * 根据入参对象，获取JDBC可以执行的SQL语句
	 * 执行阶段才会调用该方法
	 * @param params 参数
	 * @return 解析后的sql语句和参数
	 */
	BoundSql getBoundSql(Object... params);

}
