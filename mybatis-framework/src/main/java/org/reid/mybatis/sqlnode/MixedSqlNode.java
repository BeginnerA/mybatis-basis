package org.reid.mybatis.sqlnode;

import java.util.List;

import org.reid.mybatis.sqlnode.iface.SqlNode;

/**
 * <p>
 *	封装了所有解析出来的sqlnode节点信息，方便统一处理
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class MixedSqlNode implements SqlNode {
	private final List<SqlNode> sqlNodes;

	public MixedSqlNode(List<SqlNode> sqlNodes) {
		this.sqlNodes = sqlNodes;
	}

	@Override
	public void apply(DynamicContext context) {
		for (SqlNode sqlNode : sqlNodes) {
			sqlNode.apply(context);
		}
	}

}
