package com.example.demo.controller;

import com.example.demo.service.ExportService;
import com.example.demo.service.ScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/export")
public class ExportController {
    @Autowired
    private ExportService exportService;

    @Autowired
    private ScanService scanService;


    @PostMapping("/resident/brgy/{brgyCode}")
    public String exportBrgyResidentToExcel(String brgyCode){
        try {
            exportService.exportToExcel(brgyCode);
            return "done";
        } catch (IOException e) {
            return e.getMessage();
//            throw new RuntimeException(e);
        }

    }

    @PostMapping("/resident/muni/{muniCode}/brgy/bulk")
    public String exportBrgyResidentToExcelBulk(@PathVariable("muniCode") String muniCode){
        try {
            exportService.exportToExcelByMuni(muniCode);
            return "done";
        } catch (IOException e) {
            return e.getMessage();
//            throw new RuntimeException(e);
        }

    }

    @GetMapping()
    public void test(){
        try {
            scanService.processFiles();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
