package com.reid.sqlSession;

import com.reid.config.BoundSql;
import com.reid.pojo.Configuration;
import com.reid.pojo.MappedStatement;
import com.reid.utils.GenericTokenParser;
import com.reid.utils.ParameterMapping;
import com.reid.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * sql简单执行器
 * </p>
 *
 * @ClassName SimpleExecutor
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/15
 * @Version V1.0
 **/
@SuppressWarnings("unchecked")
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        //注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        //获取sql语句
        String sql = mappedStatement.getSql();
        //转换sql语句，解析参数
        BoundSql boundSql = getBoundSql(sql);
        //获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlTest());
        //设置参数
        String paramterType = mappedStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++){
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i+1, o);
        }
        //执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        //封装结果集
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //字段名
                String columnName = metaData.getColumnName(i);
                //字段值
                Object value = resultSet.getObject(columnName);
                //使用反射或内省，根据数据库表和实体的对应关系，完成封装
                columnName = resetName(columnName);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }

        return (List<E>) objects;
    }

    /**
     * 重置表/字段名称
     * @param name 名称
     * @return 表名
     */
    private static String resetName(String name) {
        String[] names = name.split("_");

        StringBuilder nameBuilder = new StringBuilder();
        for(int i = 0; i < names.length; i++) {
            if (i > 0) {
                String lowercaseName = names[i].toLowerCase();
                lowercaseName = lowercaseName.substring(0, 1).toUpperCase() + lowercaseName.substring(1, lowercaseName.length());
                nameBuilder.append(lowercaseName);
            } else {
                nameBuilder.append(names[i].toLowerCase());
            }
        }
        name = nameBuilder.toString();

        return name;
    }

    /**
     * 获取类对象
     * @param paramterType 类路径
     */
    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            return Class.forName(paramterType);
        }
        return null;
    }

    /**
     * sql语句和参数的解析
     * 1、将#{}使用？进行替代；
     * 2、解析出#{}里面的值进行存储；
     * @param sql sql
     * @return BoundSql
     */
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析后的sql
        String parsSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(parsSql, parameterMappings);
    }
}
