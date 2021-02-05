package org.reid.mybatis.sqlnode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *	封装了sql信息、可以封装入参信息
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class DynamicContext {

	private final StringBuffer sb = new StringBuffer();

	private final Map<String, Object> bindings = new HashMap<String, Object>();

	/**
	 * 将传入的入参对象，封装到Map集合中
	 * 
	 * @param parameter 参数
	 */
	public DynamicContext(Object parameter) {
		this.bindings.put("_parameter", parameter);
	}

	public String getSql() {
		return sb.toString();
	}

	public void appendSql(String sql) {
		sb.append(sql);
		sb.append(" ");
	}

	public Map<String, Object> getBindings() {
		return bindings;
	}

}
