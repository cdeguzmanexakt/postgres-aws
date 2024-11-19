package com.example.demo.service;

import com.example.demo.model.Barangay;
import com.example.demo.model.Municipality;
import com.example.demo.model.Resident;
import com.example.demo.repo.BarangayRepo;
import com.example.demo.repo.MunicipalityRepository;
import com.example.demo.repo.ResidentRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExportService {
    @Autowired
    private ResidentRepository residentRepo;
    @Autowired
    private BarangayRepo brgyRepo;
    @Autowired
    private MunicipalityRepository muniRepo;

    public String exportToExcel(String brgyCode) throws IOException {
        List<Resident> residents = residentRepo.findByLikeBrgyCode(brgyCode);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Residents");
        int rowNum = 1;

        if(residents.size()>0){
            for (Resident resident : residents){
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(resident.getId());
                row.createCell(1).setCellValue(resident.getMobileNum());
                row.createCell(2).setCellValue(resident.getLastName());
                row.createCell(3).setCellValue(resident.getFirstName());
                row.createCell(4).setCellValue(resident.getBrgyCode());
                row.createCell(5).setCellValue(resident.getMuniCode());
            }
        }

        FileOutputStream outputStream = new FileOutputStream("D:\\test\\VBtest\\"+brgyCode+".xlsx");
        wb.write(outputStream);
        wb.close();
        outputStream.close();


        return "test";
    }

    public String exportToExcel(String muniCode, String brgyCode) throws IOException {
        List<Resident> residents = residentRepo.findByLikeMuniCodeAndBrgyCode(muniCode,brgyCode);

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Residents");
        int rowNum = 1;

        if(residents.size()>0){
            for (Resident resident : residents){
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(resident.getId());
                row.createCell(1).setCellValue(resident.getMobileNum());
                row.createCell(2).setCellValue(resident.getFirstName());
                row.createCell(3).setCellValue(resident.getLastName());
                row.createCell(4).setCellValue(resident.getMiddleName());
                row.createCell(5).setCellValue(resident.getAge());
                row.createCell(6).setCellValue(resident.getGender());
                row.createCell(7).setCellValue(resident.isVoter());
                row.createCell(8).setCellValue(resident.isVbFlag());
                row.createCell(9).setCellValue(resident.getBrgyCode());
                row.createCell(10).setCellValue(resident.getMuniCode());
            }
        }
        System.out.println(muniCode + "+++++++++++++++++++==");
        File exportDir = new File("D:\\test\\VBtest\\"+muniCode);
        if(!exportDir.exists()){
            exportDir.mkdir();
        }
        FileOutputStream outputStream = new FileOutputStream(new File("D:\\test\\VBtest\\"+muniCode+
                "\\ELBAT2024_"+brgyCode+".xlsx"));
        wb.write(outputStream);
        wb.close();
        outputStream.close();
        return "test";
    }

    public void exportToExcelByMuni(String muniCode) throws IOException {
        System.out.println(muniCode);
        List<Barangay> barangayList = brgyRepo.findAllByCityDesc(muniCode);

        for (Barangay data : barangayList) {
            exportToExcel(muniCode, data.getBrgyDesc());
        }

    }

}
