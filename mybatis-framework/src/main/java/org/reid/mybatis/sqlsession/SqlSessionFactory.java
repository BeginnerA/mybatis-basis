package org.reid.mybatis.sqlsession;

/**
 * <p>
 *	SQL会话工厂，主要用来生产SqlSession
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public interface SqlSessionFactory {

	/**
	 * 获取SqlSession
	 * @return SqlSession
	 */
	SqlSession openSession();

}
