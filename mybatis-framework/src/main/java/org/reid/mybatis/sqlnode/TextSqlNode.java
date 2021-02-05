package org.reid.mybatis.sqlnode;

import org.reid.mybatis.sqlnode.iface.SqlNode;
import org.reid.mybatis.utils.GenericTokenParser;
import org.reid.mybatis.utils.OgnlUtils;
import org.reid.mybatis.utils.SimpleTypeRegistry;
import org.reid.mybatis.utils.TokenHandler;

/**
 * <p>
 *	封装sql文本
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class TextSqlNode implements SqlNode {

	private final String sqlText;

	public TextSqlNode(String sqlText) {
		super();
		this.sqlText = sqlText;
	}

	@Override
	public void apply(DynamicContext context) {
		//先处理${}，将处理之后的SQL语句，追加到context中
		GenericTokenParser tokenParser = new GenericTokenParser("${", "}", new BindingTokenParser(context));
		String sql = tokenParser.parse(sqlText);
		context.appendSql(sql);
	}

	public boolean isDynamic() {
		return sqlText.contains("${");
	}
	
	private static class BindingTokenParser implements TokenHandler {
		private final DynamicContext context;

		public BindingTokenParser(DynamicContext context) {
			this.context = context;
		}

		@Override
		public String handleToken(String expression) {
			Object paramObject = context.getBindings().get("_parameter");
			if (paramObject == null) {
				return "";
			} else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
				return String.valueOf(paramObject);
			}

			// 使用Ognl api去获取相应的值
			Object value = OgnlUtils.getValue(expression, context.getBindings());
			return value == null ? "" : String.valueOf(value);
		}

	}

}
