package org.reid.mybatis.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import org.reid.mybatis.nodehandler.NodeHandler;
import org.reid.mybatis.sqlnode.IfSqlNode;
import org.reid.mybatis.sqlnode.MixedSqlNode;
import org.reid.mybatis.sqlnode.StaticTextSqlNode;
import org.reid.mybatis.sqlnode.TextSqlNode;
import org.reid.mybatis.sqlnode.iface.SqlNode;
import org.reid.mybatis.sqlsource.DynamicSqlSource;
import org.reid.mybatis.sqlsource.RawSqlSource;
import org.reid.mybatis.sqlsource.iface.SqlSource;

/**
 * <p>
 * XML脚本解析器
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class XMLScriptParser {
	private boolean isDynamic = false;

	private final Map<String, NodeHandler> nodeHandlerMap = new HashMap<String, NodeHandler>();

	public XMLScriptParser() {
		initNodeHandlers();
	}

	private void initNodeHandlers() {
		nodeHandlerMap.put("if", new IfNodeHandler());
		// nodeHandlerMap.put("where", new WhereNodeHandler());
	}

	public SqlSource parseScriptNode(Element selectElement) {
		// 解析动态标签
		MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);
		SqlSource sqlSource = null;
		// 先判断是否包含${}
		if (isDynamic) {
			sqlSource = new DynamicSqlSource(rootSqlNode);
		} else {
			sqlSource = new RawSqlSource(rootSqlNode);
		}

		return sqlSource;
	}

	private MixedSqlNode parseDynamicTags(Element selectElement) {
		List<SqlNode> contents = new ArrayList<>();
		// 使用nodeCount会统计文本节点，而使用elements获取到的都是元素子节点
		int nodeCount = selectElement.nodeCount();
		for (int i = 0; i < nodeCount; i++) {
			// 按照顺序获取每个节点对象
			Node node = selectElement.node(i);
			if (node instanceof Text) {
				String sqlText = node.getText().trim();
				if ("".equals(sqlText)) {
					continue;
				}
				TextSqlNode textSqlNode = new TextSqlNode(sqlText);
				if (textSqlNode.isDynamic()) {
					isDynamic = true;
					contents.add(textSqlNode);
				} else {
					contents.add(new StaticTextSqlNode(sqlText));
				}

			} else if (node instanceof Element) {
				// 此处通过NodeHandler去处理不同的标签
				Element nodeToHandle = (Element) node;
				String name = nodeToHandle.getName();
				// 怎么去查找对应的标签处理器呢，需要通过标签名称
				NodeHandler nodeHandler = nodeHandlerMap.get(name);
				nodeHandler.handleNode(nodeToHandle, contents);

				isDynamic = true;
			}
		}
		return new MixedSqlNode(contents);
	}

	/**
	 * 专门来解析if标签的标签处理器
	 * 
	 * @author 灭霸詹
	 *
	 */
	class IfNodeHandler implements NodeHandler {

		/**
		 * nodeToHandler：if标签
		 */
		@Override
		public void handleNode(Element nodeToHandler, List<SqlNode> contents) {
			String test = nodeToHandler.attributeValue("test");

			MixedSqlNode parseDynamicTags = parseDynamicTags(nodeToHandler);

			contents.add(new IfSqlNode(test, parseDynamicTags));
		}

	}
}
