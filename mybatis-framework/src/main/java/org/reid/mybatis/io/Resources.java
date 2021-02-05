package org.reid.mybatis.io;

import java.io.InputStream;
import java.io.Reader;

/**
 * <p>
 *	资源资源
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public class Resources {

	/**
	 * 根据配置文件的路径，将配置文件加载成字节输入流，储存在内存中
	 * @return InputStream
	 */
	public static InputStream getResourceAsStream(String location) {
		return Resources.class.getClassLoader().getResourceAsStream(location);
	}

	public static Reader getResourceAsReader(String location) {
		return null;
	}
}
