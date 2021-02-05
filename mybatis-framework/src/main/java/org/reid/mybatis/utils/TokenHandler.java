package org.reid.mybatis.utils;

/**
 * <p>
 *  令牌处理程序
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/22
 * @Version V1.0
 **/
public interface TokenHandler {

    /**
     * 处理
     * @param content 比如说${username}，那么expression就是username username也就是Ognl表达式
     * @return 获取相应的值
     */
    String handleToken(String content);
}

