package com.reid.config;

import com.reid.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 解析后的sql语句和参数
 * </p>
 *
 * @ClassName BoundSql
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
public class BoundSql {

    /**
     * 解析后的sql
     */
    private String sqlTest;

    /**
     * 参数集
     */
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlTest, List<ParameterMapping> parameterMappingList) {
        this.sqlTest = sqlTest;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlTest() {
        return sqlTest;
    }

    public void setSqlTest(String sqlTest) {
        this.sqlTest = sqlTest;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
