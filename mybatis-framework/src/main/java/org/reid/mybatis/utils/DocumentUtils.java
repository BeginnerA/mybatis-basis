package org.reid.mybatis.utils;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * <p>
 *  Document工具
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class DocumentUtils {

	public static Document readDocument(InputStream inputStream) {
		try {
			SAXReader saxReader = new SAXReader();
			return saxReader.read(inputStream);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

}
