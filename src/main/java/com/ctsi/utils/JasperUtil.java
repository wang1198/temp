package com.ctsi.utils;

import com.ctsi.common.DocType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * ClassName JasperUtil
 * Description //TODO
 * Author tongliwei
 * Date 2019/8/29 17:05
 * Version v1.0
 **/

@SuppressWarnings("deprecation")
public class JasperUtil {
    private static void prepareReport(JasperReport jasperReport, String docType) {
        /**
         * 如果导出的是excel，则需要去掉周围的margin
         **/

        if (DocType.XLS.name().equals(docType) || DocType.XLSX.name().equals(docType)) {
            try {
                Field margin = JRBaseReport.class
                        .getDeclaredField("leftMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("topMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("bottomMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                Field pageHeight = JRBaseReport.class
                        .getDeclaredField("pageHeight");
                pageHeight.setAccessible(true);
                pageHeight.setInt(jasperReport, 2147483647);
            } catch (Exception exception) {
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private static JRAbstractExporter getJRExporter(DocType docType) {
        JRAbstractExporter exporter = null;
        switch (docType) {
            case PDF:
                exporter = new JRPdfExporter();
                break;
            case DOC:
                exporter = new JRDocxExporter();
                break;
            case DOCX:
                exporter = new JRDocxExporter();
                break;
            case XLS:
                exporter = new JRXlsxExporter();
                break;
            case XLSX:
                exporter = new JRXlsxExporter();
                break;
            case XML:
                exporter = new JRXmlExporter();
                break;
            case RTF:
                exporter = new JRRtfExporter();
                break;
            case HTML:
                exporter = new JRHtmlExporter();
                break;
        }
        return exporter;
    }
    /**
     * 按照类型导出不同格式文件
     *  @param type       文件类型
     * @param fileName   文件名称
     * @param jasperReport       jasper文件的来源
     * @param parameters
     **/

    public static void export(String type, String fileName, JasperReport jasperReport, Map parameters) throws IOException {
        FileOutputStream outputStream=null;
        try {
            prepareReport(jasperReport, type);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            DocType docType = DocType.fromTypeName(type);
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            outputStream=new FileOutputStream(fileName);
            JRAbstractExporter exporter = getJRExporter(docType);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
