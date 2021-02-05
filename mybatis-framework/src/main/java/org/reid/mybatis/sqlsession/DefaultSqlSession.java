package org.reid.mybatis.sqlsession;

import java.lang.reflect.*;
import java.util.List;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.mapping.MappedStatement;
import org.reid.mybatis.executor.SimpleExecutor;

/**
 * <p>
 *	默认Sql会话
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class DefaultSqlSession implements SqlSession {

	private final Configuration configuration;

	public DefaultSqlSession(Configuration configuration) {
		this.configuration = configuration;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> List<E> selectList(String statementId, Object... params) throws Exception {
		SimpleExecutor simpleExecutor = new SimpleExecutor();
		MappedStatement mappedStatement = configuration.getMappedStatementMap(statementId);
		List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
		return (List<E>) list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T selectOne(String statementId, Object... params) throws Exception {
		List<Object> objects = selectList(statementId, params);
		if (objects.size() == 1){
			return (T) objects.get(0);
		}else {
			throw new RuntimeException("查询结果为空或不唯一");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getMapper(Class<?> mapperClass) throws Exception {
		//使用JDK动态代理来为Dao接口生成代理对象
		Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//底层还是去执行了JDBC代码
				String className = method.getDeclaringClass().getName();
				String methodName = method.getName();
				String statementId = className + "." + methodName;
				//获取被调用方法的返回值类型
				Type genericReturnType = method.getGenericReturnType();
				//判断是否是泛型类型参数
				if (genericReturnType instanceof ParameterizedType) {
					return selectList(statementId, args);
				}
				return selectOne(statementId, args);
			}
		});
		return (T) proxyInstance;
	}

}
