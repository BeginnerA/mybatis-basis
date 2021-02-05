package org.reid.mybatis.utils;

/**
 * <p>
 * 特殊字符串处理
 * </p>
 *
 * @Author REID
 * @Blog https://blog.csdn.net/qq_39035773
 * @GitHub https://github.com/BeginnerA
 * @Data 2021/1/28
 * @Version V1.0
 **/
public class NameMaster {
    /**
     * 重置表/字段名称
     * @param name 名称
     * @return 表名
     */
    public static String resetName(String name) {
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
}
