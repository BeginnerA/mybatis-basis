package org.reid.mybatis.utils;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Map;

import ognl.*;

/**
 * <p>
 *	Ognl工具
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class OgnlUtils {
	/**
	 * 根据Ognl表达式，获取指定对象的参数值
	 * @param expression Ognl表达式
	 * @param paramObject 参数对象
	 * @return 参数值
	 */
	public static Object getValue(String expression, Object paramObject) {
		try {

			OgnlContext context = new OgnlContext(null, null, new AbstractMemberAccess() {
				@Override
				public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
					int modifiers = member.getModifiers();
					return Modifier.isPublic(modifiers);
				}
			});
			context.setRoot(paramObject);

			//mybatis中的动态标签使用的是ognl表达式
			//mybatis中的${}使用的是ognl表达式
			// 构建Ognl表达式
			Object ognlExpression = Ognl.parseExpression(expression);
			// 解析表达式
			return Ognl.getValue(ognlExpression, context, context.getRoot());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过Ognl表达式，去计算boolean类型的结果
	 * @param expression Ognl表达式
	 * @param parameterObject 参数对象
	 * @return boolean
	 */
	public static boolean evaluateBoolean(String expression, Object parameterObject) {
		Object value = OgnlUtils.getValue(expression, parameterObject);
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		if (value instanceof Number) {
			return new BigDecimal(String.valueOf(value)).compareTo(BigDecimal.ZERO) != 0;
		}
		return value != null;
	}
}
