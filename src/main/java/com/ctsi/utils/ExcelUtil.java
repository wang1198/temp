package com.ctsi.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.ctsi.ssdc.admin.domain.util.ExcelColumn;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ClassName ExcelUtil
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/24 15:43
 * Version v1.0
 **/
public class ExcelUtil {
    private final static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
    private static Workbook wb = null;
    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";

    /**
     * 导出数据到Excel
     *
     * @param list           数据集合
     * @param title          数据第一行标题
     * @param sheetName      Sheet名称
     * @param pojoClass      实体Class
     * @param fileName       文件名
     * @param isCreateHeader 是否创建数据表头
     * @param response       响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    /**
     * 导出数据到Excel
     *
     * @param list      数据集合
     * @param title     数据第一行标题
     * @param sheetName Sheet名称
     * @param pojoClass 实体Class
     * @param fileName  文件名
     * @param response  响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName,
                                   HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
            throws Exception {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response,
                                      ExportParams exportParams) {
        Workbook workbook = null;
        try {
            workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workbook != null)
            try {
                downLoadExcel(fileName, response, workbook);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook)
            throws Exception {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response)
            throws Exception {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null)
            ;
        downLoadExcel(fileName, response, workbook);
    }

    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass)
            throws Exception {
        if (org.apache.commons.lang3.StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new Exception("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return list;
    }

    /**
     * 导入(解析)Excel
     *
     * @param file       Excel文件
     * @param titleRows  标题行数
     * @param headerRows 表头行数
     * @param pojoClass  实体类
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)
            throws Exception {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new Exception("excel文件不能为空");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return list;
    }

    public static <T> List<T> readExcel(Class<T> cls, MultipartFile file) {

        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.error("上传文件格式不正确");
        }
        List<T> dataList = new ArrayList<>();
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith(EXCEL2007)) {
                workbook = new XSSFWorkbook(is);
            }
            if (fileName.endsWith(EXCEL2003)) {
                workbook = new HSSFWorkbook(is);
            }
            if (workbook != null) {
                //类映射  注解 value-->bean columns
                Map<String, List<Field>> classMap = new HashMap<>();
                List<Field> fields = Stream.of(cls.getDeclaredFields()).collect(Collectors.toList());
                fields.forEach(
                        field -> {
                            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                            if (annotation != null) {
                                String value = annotation.value();
                                if (StringUtils.isBlank(value)) {
                                    return;
                                }
                                if (!classMap.containsKey(value)) {
                                    classMap.put(value, new ArrayList<>());
                                }
                                field.setAccessible(true);
                                classMap.get(value).add(field);
                            }
                        }
                );
                //索引-->columns
                Map<Integer, List<Field>> reflectionMap = new HashMap<>(16);
                //默认读取第一个sheet
                Sheet sheet = workbook.getSheetAt(0);

                boolean firstRow = true;
                for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    //首行  提取注解
                    if (firstRow) {
                        for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String cellValue = getCellValue(cell);
                            if (classMap.containsKey(cellValue)) {
                                reflectionMap.put(j, classMap.get(cellValue));
                            }
                        }
                        firstRow = false;
                    } else {
                        //忽略空白行
                        if (row == null) {
                            continue;
                        }
                        try {
                            T t = cls.newInstance();
                            //判断是否为空白行
                            boolean allBlank = true;
                            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                                if (reflectionMap.containsKey(j)) {
                                    Cell cell = row.getCell(j);
                                    String cellValue = getCellValue(cell);
                                    if (StringUtils.isNotBlank(cellValue)) {
                                        allBlank = false;
                                    }
                                    List<Field> fieldList = reflectionMap.get(j);
                                    fieldList.forEach(
                                            x -> {
                                                try {
                                                    handleField(t, cellValue, x);
                                                } catch (Exception e) {
                                                    log.error(String.format("reflect field:%s value:%s exception!", x.getName(), cellValue), e);
                                                }
                                            }
                                    );
                                }
                            }
                            if (!allBlank) {
                                dataList.add(t);
                            } else {
                                log.warn(String.format("row:%s is blank ignore!", i));
                            }
                        } catch (Exception e) {
                            log.error(String.format("parse row:%s exception!", i), e);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(String.format("parse excel exception!"), e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    log.error(String.format("parse excel exception!"), e);
                }
            }
        }
        return dataList;
    }

    private static <T> void handleField(T t, String value, Field field) throws Exception {
        Class<?> type = field.getType();
        if (type == null || type == void.class || StringUtils.isBlank(value)) {
            return;
        }
        if (type == Object.class) {
            field.set(t, value);
            //数字类型
        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
            if (type == int.class || type == Integer.class) {
                field.set(t, NumberUtils.toInt(value));
            } else if (type == long.class || type == Long.class) {
                field.set(t, NumberUtils.toLong(value));
            } else if (type == byte.class || type == Byte.class) {
                field.set(t, NumberUtils.toByte(value));
            } else if (type == short.class || type == Short.class) {
                field.set(t, NumberUtils.toShort(value));
            } else if (type == double.class || type == Double.class) {
                field.set(t, NumberUtils.toDouble(value));
            } else if (type == float.class || type == Float.class) {
                field.set(t, NumberUtils.toFloat(value));
            } else if (type == char.class || type == Character.class) {
                field.set(t, CharUtils.toChar(value));
            } else if (type == boolean.class) {
                field.set(t, BooleanUtils.toBoolean(value));
            } else if (type == BigDecimal.class) {
                field.set(t, new BigDecimal(value));
            }
        } else if (type == Boolean.class) {
            field.set(t, BooleanUtils.toBoolean(value));
        } else if (type == Date.class) {
            field.set(t, value);
        } else if (type == String.class) {
            field.set(t, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(t, constructor.newInstance(value));
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return StringUtils.trimToEmpty(cell.getCellFormula());
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }

    }

    public static <T> void writeExcel(List<T> dataList, Class<T> cls, String fileName) {
        try {
            Field[] fields = cls.getDeclaredFields();
            List<Field> fieldList = Arrays.stream(fields)
                    .filter(field -> {
                        ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                        if (annotation != null && annotation.col() > 0) {
                            field.setAccessible(true);
                            return true;
                        }
                        return false;
                    }).sorted(Comparator.comparing(field -> {
                        int col = 0;
                        ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                        if (annotation != null) {
                            col = annotation.col();
                        }
                        return col;
                    })).collect(Collectors.toList());
            String sheetNum = "Sheet1";
            if (wb == null)
                wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet(sheetNum);
            Row row = sheet.createRow(0);
            //写入头部
            for(int i=0;i<fieldList.size();i++){
                ExcelColumn annotation = fieldList.get(i).getAnnotation(ExcelColumn.class);
                String columnName = "";
                if (annotation != null) {
                    columnName = annotation.value();
                }
                Cell cell = row.createCell(i);

                CellStyle cellStyle = wb.createCellStyle();
                cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

                Font font = wb.createFont();
                font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(columnName);
            }
            if (CollectionUtils.isNotEmpty(dataList)) {
                for (int j = 1; j <= dataList.size(); j++) {
                    Row row1 = sheet.createRow(j);
                    for (int f = 0; f < fieldList.size(); f++) {
                        Object value = "";
                        try {
                            value = fieldList.get(f).get(dataList.get(j-1));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Cell cell = row1.createCell(f);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                }
            }
            //冻结窗格
            wb.getSheet(sheetNum).createFreezePane(0, 1, 0, 1);
            buildExcelFile(fileName, wb);
            wb.close();
            wb = null;
        } catch (Exception e) {
            wb = null;
        }
    }

    /**
     * 浏览器下载excel
     *
     * @param fileName
     * @param wb
     * @param response
     */

    public static void buildExcelDocument(String fileName, Workbook wb, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        response.flushBuffer();
        wb.write(response.getOutputStream());
    }

    /**
     * 生成excel文件
     *
     * @param path 生成excel路径
     * @param wb
     */
    private static void buildExcelFile(String path, Workbook wb) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            wb.write(new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wb.close();
        }
    }
}
