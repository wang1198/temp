package com.ctsi.service;

import com.ctsi.common.ResultBack;

public interface DicService {
     public ResultBack getDicByType(String paraType);

     public ResultBack queryLocation();

     public ResultBack queryCompany();
}
