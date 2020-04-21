package com.ctsi.datasource;

/**
 * ClassName DataSourceContextHolder
 * Description //TODO
 * Author tongliwei
 * Date 2020/1/31 16:27
 * Version v1.0
 **/
public class DataSourceContextHolder {
    public static final String DEFAULT_DS="primary";
    private static final ThreadLocal<String> contextHolder=new ThreadLocal<>();
    public static void setDB(String dbType){
        contextHolder.set(dbType);
    }
    public static String getDB(){
        return (contextHolder.get());
    }
    public static void clearDB(){
        contextHolder.remove();
    }
}
