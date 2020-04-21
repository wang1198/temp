package com.ctsi.web;

import com.ctsi.common.ResultBack;
import com.ctsi.dao.ReportMapper;
import com.ctsi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Resource
    private ReportMapper reportMapper;

    @PostMapping("/importReportingProcess")
    public ResultBack importReportingProcess(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
        return reportService.importReportingProcess(request,file);
    }

    @GetMapping("/exportReportingProcess")
    public ResponseEntity<FileSystemResource> exportReportingProcess(){
        return reportService.exportReportingProcess();
    }

    @PostMapping("/importReceivingProcess")
    public ResultBack importReceivingProcess(HttpServletRequest request,MultipartFile file){
        return reportService.importReceivingProcess(request,file);
    }

    @GetMapping("/exportReceivingProcess")
    public ResponseEntity<FileSystemResource> exportReceivingProcess(){
        return reportService.exportReceivingProcess();
    }

    @PostMapping("/importJudgementProcess")
    public ResultBack importJudgementProcess(HttpServletRequest request,MultipartFile file){
        return reportService.importJudgementProcess(request,file);
    }

    @GetMapping("/exportJudgementProcess")
    public ResponseEntity<FileSystemResource> exportJudgementProcess(){
        return reportService.exportJudgementProcess();
    }

    @PostMapping("/importDisposalProcess")
    public ResultBack importDisposalProcess(HttpServletRequest request,MultipartFile file){
        return reportService.importDisposalProcess(request,file);
    }

    @GetMapping("/exportDisposalProcess")
    public ResponseEntity<FileSystemResource> exportDisposalProcess(){
        return reportService.exportDisposalProcess();
    }

    @GetMapping("/exportById")
    public ResponseEntity<FileSystemResource> exportById(@RequestParam("id") String id) {
        return reportService.exportById(id);
    }


    @GetMapping("/isUploadReportingProcess")
    public ResultBack isUploadReportingProcess(){
        return reportService.isUploadReportingProcess();
    }

    @GetMapping("/isUploadReceivingProcess")
    public ResultBack isUploadReceivingProcess(){
        return reportService.isUploadReceivingProcess();
    }

    @GetMapping("/isUploadJudgementProcess")
    public ResultBack isUploadJudgementProcess(){
        return reportService.isUploadJudgementProcess();
    }

    @GetMapping("/isUploadDisposalProcess")
    public ResultBack isUploadDisposalProcess(){
        return reportService.isUploadDisposalProcess();
    }

    @GetMapping("/reportingProcessRecord")
    public ResultBack reportingProcessRecord(@RequestParam int page,@RequestParam int limit){
        return reportService.reportingProcessRecord(page,limit);
    }

    @GetMapping("/receivingProcessRecord")
    public ResultBack receivingProcessRecord(@RequestParam int page,@RequestParam int limit){
        return reportService.receivingProcessRecord(page,limit);
    }

    @GetMapping("/judgementProcessRecord")
    public ResultBack judgementProcessRecord(@RequestParam int page,@RequestParam int limit){
        return reportService.judgementProcessRecord(page,limit);
    }

    @GetMapping("/disposalProcessRecord")
    public ResultBack disposalProcessRecord(@RequestParam int page,@RequestParam int limit){
        return reportService.disposalProcessRecord(page,limit);
    }
}
