package com.reid.pojo;

/**
 * <p>
 * 解析后的Mapper.xml文件
 * 将mapper.xml文件里的标签解析成MapperStatement对象
 * </p>
 *
 * @ClassName MapperStatement
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public class MappedStatement {

    /**
     * id标识
     */
    private String id;
    /**
     * 返回值lx
     */
    private String resultType;
    /**
     * 参数值类型
     */
    private String paramterType;
    /**
     * sql语句
     */
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
