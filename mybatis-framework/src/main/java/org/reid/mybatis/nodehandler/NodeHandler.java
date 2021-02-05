package org.reid.mybatis.nodehandler;

import java.util.List;

import org.dom4j.Element;

import org.reid.mybatis.sqlnode.iface.SqlNode;

/**
 * <p>
 *	处理select标签的子标签
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public interface NodeHandler {

	/**
	 * 处理节点
	 * @param nodeToHandle 要处理的节点
	 * @param contents 内容
	 */
	void handleNode(Element nodeToHandle, List<SqlNode> contents);
}
