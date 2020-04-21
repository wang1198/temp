package com.ctsi.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * ClassName DynamicDataSource
 * Description //TODO
 * Author tongliwei
 * Date 2020/1/31 16:24
 * Version v1.0
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}
