package org.reid.mybatis.executor.iface;

import java.util.List;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.mapping.MappedStatement;

/**
 * <p>
 *	执行器
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public interface Executor {

	/**
	 * 查询
	 * @param configuration 数据库连接信息
	 * @param mappedStatement sql语句信息
	 * @param params 可变参数
	 * @param <E> E
	 * @return List<E>
	 * @throws Exception 执行异常
	 */
	<E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
