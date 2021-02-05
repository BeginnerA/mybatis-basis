package org.reid.mybatis.sqlnode.iface;

import org.reid.mybatis.sqlnode.DynamicContext;

/**
 * <p>
 *	封装不同的sql脚本，提供sql脚本处理功能
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public interface SqlNode {

	/**
	 * 处理${}，将处理之后的SQL语句，追加到context中
	 * @param context sql信息
	 */
	void apply(DynamicContext context);
}
