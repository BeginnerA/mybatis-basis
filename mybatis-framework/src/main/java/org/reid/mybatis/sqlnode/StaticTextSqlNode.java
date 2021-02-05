package org.reid.mybatis.sqlnode;

import org.reid.mybatis.sqlnode.iface.SqlNode;

/**
 * <p>
 *	保存了非${}的sql文本信息
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class StaticTextSqlNode implements SqlNode {

	private final String sqlText;

	public StaticTextSqlNode(String sqlText) {
		this.sqlText = sqlText;
	}

	@Override
	public void apply(DynamicContext context) {
		context.appendSql(sqlText);
	}

}
