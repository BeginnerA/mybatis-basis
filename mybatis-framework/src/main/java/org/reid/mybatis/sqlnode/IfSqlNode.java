package org.reid.mybatis.sqlnode;

import org.reid.mybatis.sqlnode.iface.SqlNode;
import org.reid.mybatis.utils.OgnlUtils;

/**
 * <p>
 *	IF\SQL节点信息
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class IfSqlNode implements SqlNode {

	private final String test;

	private final SqlNode rootSqlNode;

	public IfSqlNode(String test, MixedSqlNode rootSqlNode) {
		this.test = test;
		this.rootSqlNode = rootSqlNode;
	}

	@Override
	public void apply(DynamicContext context) {

		boolean testValue = OgnlUtils.evaluateBoolean(test, context.getBindings().get("_parameter"));
		if (testValue) {
			rootSqlNode.apply(context);
		}
	}

}
