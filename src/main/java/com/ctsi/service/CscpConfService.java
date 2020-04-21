package com.ctsi.service;

/**
 * ClassName CscpConfService
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/6 11:21
 * Version v1.0
 **/
public interface CscpConfService {
    String getConfig(String key);
    void insert(String key,String value);
}
