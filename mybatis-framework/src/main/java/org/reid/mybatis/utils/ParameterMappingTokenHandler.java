package org.reid.mybatis.utils;

import java.util.ArrayList;
import java.util.List;

import org.reid.mybatis.sqlsource.ParameterMapping;

/**
 * <p>
 *	参数映射
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class ParameterMappingTokenHandler implements TokenHandler {
	private List<ParameterMapping> parameterMappings = new ArrayList<>();

	@Override
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	/**
	 * 建立参数映射
	 * @param content {}里面的内容：如#{id}
	 * @return ParameterMapping
	 */
	private ParameterMapping buildParameterMapping(String content) {
		return new ParameterMapping(content);
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
