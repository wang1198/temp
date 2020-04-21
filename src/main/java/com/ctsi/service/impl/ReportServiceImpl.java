package com.ctsi.service.impl;

import com.ctsi.common.ResultBack;
import com.ctsi.dao.ReportMapper;
import com.ctsi.domain.Report;
import com.ctsi.service.ReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportMapper reportMapper;
    @Value("${web.download-path}")
    private String path;
    /**
     * 上传上报流程
     * @param file
     * @return
     */
    @Override
    public ResultBack importReportingProcess(HttpServletRequest request,MultipartFile file) {
        return uploadFile(request,file,1,"上传上报流程失败");
    }

    /**
     * 下载最新上报流程
     * @return
     */
    @Override
    public ResponseEntity<FileSystemResource> exportReportingProcess() {
        Report report = reportMapper.queryLastestReportByType(1);
        return downloadFile(report.getId());
    }

    /**
     * 上传接收流程
     * @param file
     * @return
     */
    @Override
    public ResultBack importReceivingProcess(HttpServletRequest request,MultipartFile file) {
        return uploadFile(request,file,2,"上传接收流程失败");
    }

    /**
     * 下载最新接收流程
     * @return
     */
    @Override
    public ResponseEntity<FileSystemResource> exportReceivingProcess() {
        Report report = reportMapper.queryLastestReportByType(2);
        return downloadFile(report.getId());
    }

    /**
     * 上传研判流程
     * @param file
     * @return
     */
    @Override
    public ResultBack importJudgementProcess(HttpServletRequest request,MultipartFile file) {
        return uploadFile(request,file,3,"上传研判流程失败");
    }

    /**
     * 下载最新研判流程
     * @return
     */
    @Override
    public ResponseEntity<FileSystemResource> exportJudgementProcess() {
        Report report = reportMapper.queryLastestReportByType(3);
        return downloadFile(report.getId());
    }

    /**
     * 上传处置流程
     * @param file
     * @return
     */
    @Override
    public ResultBack importDisposalProcess(HttpServletRequest request,MultipartFile file) {
        return uploadFile(request,file,4,"上传处置流程失败");
    }

    /**
     * 下载最新处置流程
     * @return
     */
    @Override
    public ResponseEntity<FileSystemResource> exportDisposalProcess() {
        Report report = reportMapper.queryLastestReportByType(4);
        return downloadFile(report.getId());
    }

    @Override
    public ResponseEntity<FileSystemResource> exportById(String id) {
        return downloadFile(id);
    }

    /**
     * 是否上传上报流程
     * @return
     */
    @Override
    public ResultBack isUploadReportingProcess() {
        return isUpload(1);
    }
    /**
     * 是否上传接收流程
     * @return
     */
    @Override
    public ResultBack isUploadReceivingProcess() {
        return isUpload(2);
    }
    /**
     * 是否上传研判流程
     * @return
     */
    @Override
    public ResultBack isUploadJudgementProcess() {
        return isUpload(3);
    }
    /**
     * 是否上传处置流程
     * @return
     */
    @Override
    public ResultBack isUploadDisposalProcess() {
        return isUpload(4);
    }

    @Override
    public ResultBack reportingProcessRecord(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<Report> reportList = reportMapper.queryReportByType(1);
        PageInfo<Report> pageInfo = new PageInfo<>(reportList);
        return ResultBack.ok(pageInfo);
    }

    @Override
    public ResultBack receivingProcessRecord(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<Report> reportList = reportMapper.queryReportByType(2);
        PageInfo<Report> pageInfo = new PageInfo<>(reportList);
        return ResultBack.ok(pageInfo);
    }

    @Override
    public ResultBack judgementProcessRecord(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<Report> reportList = reportMapper.queryReportByType(3);
        PageInfo<Report> pageInfo = new PageInfo<>(reportList);
        return ResultBack.ok(pageInfo);
    }

    @Override
    public ResultBack disposalProcessRecord(int page, int limit) {
        PageHelper.startPage(page, limit, true);
        List<Report> reportList = reportMapper.queryReportByType(4);
        PageInfo<Report> pageInfo = new PageInfo<>(reportList);
        return ResultBack.ok(pageInfo);
    }

    //判断是否上传公共方法
    private ResultBack isUpload(Integer reportType){
        List<Report> reportList = reportMapper.queryReportByType(reportType);
        if (CollectionUtils.isEmpty(reportList)){
            return ResultBack.build(0,"");
        }else{
            Report report = reportMapper.queryLastestReportByType(reportType);
            return ResultBack.build(1,"",report);

        }
    }

    //上传公共方法
    private ResultBack uploadFile(HttpServletRequest request,MultipartFile file, Integer reportType, String msg){
        if (file==null||file.isEmpty()) {
            return ResultBack.build(-1,"请选择上传的文件！");
        }
        try {
            String fileName = file.getOriginalFilename();
            File dest = new File(path + fileName);
            file.transferTo(dest);
            Report report = new Report();
            String id = UUID.randomUUID().toString();
            report.setId(id);
            report.setFileName(fileName);
            report.setReportType(reportType);
            report.setCreateTime(new Date());
            String userName = request.getRemoteUser();
            report.setCreateBy(userName);
            reportMapper.addReport(report);
            Map<String,Object> map = new HashMap<>();
            map.put("reportType",reportType);
            map.put("fileName",fileName);
            return ResultBack.ok(map);
        } catch (IOException e) {
            e.printStackTrace();
            return  ResultBack.build(-1,msg);
        }
    }

    private ResponseEntity<FileSystemResource> downloadFile(String id){
        try {
            String fileName = reportMapper.queryFileNameById(id);
            System.out.println(fileName);

            // 获取文件名称，中文可能被URL编码
            fileName = URLDecoder.decode(fileName, "UTF-8");

            // 获取本地文件系统中的文件资源
            FileSystemResource resource = new FileSystemResource(path+fileName);

            // 解析文件的 mime 类型
            String mediaTypeStr = URLConnection.getFileNameMap().getContentTypeFor(fileName);
            // 无法判断MIME类型时，作为流类型
            mediaTypeStr = (mediaTypeStr == null) ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mediaTypeStr;
            // 实例化MIME
            MediaType mediaType = MediaType.parseMediaType(mediaTypeStr);
            /*
             * 构造响应的头
             */
            HttpHeaders headers = new HttpHeaders();
            // 下载之后需要在请求头中放置文件名，该文件名按照ISO_8859_1编码。
            // String filenames = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            String filenames = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            headers.setContentDispositionFormData("attachment", filenames);
            headers.setContentType(mediaType);

            /*
             * 返还资源
             */
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.getInputStream().available())
                    .body(resource);
        } catch (Exception e) {
            return null;
        }
    }
}
