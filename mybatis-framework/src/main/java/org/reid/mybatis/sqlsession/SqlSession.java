package org.reid.mybatis.sqlsession;

import java.util.List;

/**
 * <p>
 *	表示一次Sql会话
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public interface SqlSession {

	/**
	 * 集合查询
	 * @param statementId xml文件sql唯一编号
	 * @param params 可变参数
	 * @param <E> E
	 * @return List<E>
	 * @throws Exception SQL异常
	 */
	<E> List<E> selectList(String statementId, Object... params) throws Exception;

	/**
	 * 唯一值查询
	 * @param statementId xml文件sql唯一编号
	 * @param params 可变参数
	 * @param <T> T
	 * @return T
	 * @throws Exception SQL异常
	 */
	<T> T selectOne(String statementId, Object... params) throws Exception;

	/**
	 * 为Dao接口生成代理实现类
	 * @param mapperClass 映射器类
	 * @param <T> T
	 * @return T
	 * @throws Exception 执行异常
	 */
	<T> T getMapper(Class<?> mapperClass) throws Exception;

}
