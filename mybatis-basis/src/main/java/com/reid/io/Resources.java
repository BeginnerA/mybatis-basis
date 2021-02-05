package com.reid.io;

import java.io.InputStream;
import java.net.URL;

/**
 * <p>
 * 资源
 * </p>
 *
 * @ClassName Resources
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public class Resources {

    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，储存在内存中
     * @return InputStream
     */
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
