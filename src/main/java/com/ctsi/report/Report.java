package com.ctsi.report;

import com.ctsi.domain.ReportInfo;
import com.ctsi.domain.ReportItem;

/**
 * ClassName Report
 * Description TODO
 * Author tongliwei
 * Date 2020/4/11 18:55
 * Version v1.0
 **/
public interface Report {
    ReportItem generateReport(ReportInfo info);
}
