package org.reid.mybatis.executor;

import java.util.List;

import org.reid.mybatis.config.Configuration;
import org.reid.mybatis.mapping.MappedStatement;
import org.reid.mybatis.executor.iface.Executor;

/**
 * <p>
 *	缓存执行器
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class CachingExecutor implements Executor {

	private final Executor delegate;
	
	public CachingExecutor(Executor delegate) {
		this.delegate = delegate;
	}

	@Override
	public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
		// 处理二级缓存
		return delegate.query(configuration, mappedStatement, params);
	}
}
