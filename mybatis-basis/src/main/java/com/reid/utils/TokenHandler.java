package com.reid.utils;

/**
 * <p>
 * 令牌处理程序
 * </p>
 *
 * @ClassName TokenHandler
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public interface TokenHandler {

    /**
     * 处理令牌
     * @param content
     * @return String
     */
    String handleToken(String content);
}
