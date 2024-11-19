package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

@Entity
@Data
public class Barangay {

    @Id
    private int brgyCode;
    private String brgyDesc;
    private int cityCode;
    private String cityDesc;

    public  Barangay buildBrgy(Row row){
        System.out.println(row.getCell(2));
        Barangay brgy = new Barangay();
        brgy.setBrgyCode((int) row.getCell(1).getNumericCellValue());
        brgy.setCityCode((int) row.getCell(5).getNumericCellValue());
        brgy.setBrgyDesc(row.getCell(2).getStringCellValue());
        brgy.setCityDesc(row.getCell(6).getStringCellValue());

        return brgy;
    }
}
