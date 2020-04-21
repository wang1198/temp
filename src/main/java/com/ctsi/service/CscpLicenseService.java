package com.ctsi.service;

import com.ctsi.common.MyException;
import com.ctsi.domain.CscpLicense;

/**
 * ClassName CscpLicenseService
 * Description TODO
 * Author tongliwei
 * Date 2020/2/6 11:24
 * Version v1.0
 **/
public interface CscpLicenseService {
    CscpLicense license(String license) throws MyException;
    String register(CscpLicense license,String pri);
    CscpLicense getLicense();
}
