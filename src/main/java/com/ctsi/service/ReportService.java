package com.ctsi.service;

import com.ctsi.common.ResultBack;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ReportService {

    public ResultBack importReportingProcess(HttpServletRequest request,MultipartFile file);

    public ResponseEntity<FileSystemResource> exportReportingProcess();

    public ResultBack importReceivingProcess(HttpServletRequest request,MultipartFile file);

    public ResponseEntity<FileSystemResource> exportReceivingProcess();

    public ResultBack importJudgementProcess(HttpServletRequest request,MultipartFile file);

    public ResponseEntity<FileSystemResource> exportJudgementProcess();

    public ResultBack importDisposalProcess(HttpServletRequest request,MultipartFile file);

    public ResponseEntity<FileSystemResource> exportDisposalProcess();

    public ResponseEntity<FileSystemResource> exportById(String id);

    public ResultBack isUploadReportingProcess();

    public ResultBack isUploadReceivingProcess();

    public ResultBack isUploadJudgementProcess();

    public ResultBack isUploadDisposalProcess();

    public ResultBack reportingProcessRecord(int page, int limit);

    public ResultBack receivingProcessRecord(int page, int limit);

    public ResultBack judgementProcessRecord(int page, int limit);

    public ResultBack disposalProcessRecord(int page, int limit);

}
