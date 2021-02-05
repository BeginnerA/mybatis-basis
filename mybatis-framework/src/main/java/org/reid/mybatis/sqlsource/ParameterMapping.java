package org.reid.mybatis.sqlsource;

/**
 * <p>
 *	解析#{}获取到的参数信息，主要包含参数名称（也就是#{}中的名称和参数类型）
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class ParameterMapping {

	private String name;
	
	private Class<?> type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public ParameterMapping(String name) {
		super();
		this.name = name;
	}
	
	
}
