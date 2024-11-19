package com.example.demo.service;

import com.example.demo.model.Barangay;
import com.example.demo.model.Municipality;
import com.example.demo.model.Resident;
import com.example.demo.repo.BarangayRepo;
import com.example.demo.repo.MunicipalityRepository;
import com.example.demo.repo.ResidentRepository;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {
    private final MunicipalityRepository muniRepo;
    private final BarangayRepo brgyRepo;
    private final ResidentRepository residentRepo;
    @Value("${import.file.path}")
    String baseFilePath;
	private Workbook wb;

    public ImportService(MunicipalityRepository muniRepo, BarangayRepo brgyRepo, ResidentRepository residentRepo) {
        this.muniRepo = muniRepo;
        this.brgyRepo = brgyRepo;
        this.residentRepo = residentRepo;
    }

    private Municipality buildMuni(Row row) {
        System.out.println(row.getCell(2));
        Municipality muni = new Municipality();
        muni.setProvCode((int) row.getCell(4).getNumericCellValue());
        muni.setCitymunCode((int) row.getCell(5).getNumericCellValue());
        muni.setProvDesc("BATANGAS");
        muni.setCitymunDesc(row.getCell(2).getStringCellValue());

        return muni;
    }

    public boolean importResident() {
        List<Resident> residentList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(baseFilePath+"residentsImport.xlsx");
            wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            if (sheet != null) {
                for (Row row : sheet) {
                    if (row.getRowNum() > 0) {
                    	
                    	if(!(row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK)) {
                    		Resident resident = new Resident();
                            residentList.add(resident.buildResident(row));
                    	}
                    }
                }
                residentRepo.saveAll(residentList);
                wb.close();
                return true;
            } else {
                System.out.println("sheet null");
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    

    public boolean importBrgy() {
        List<Barangay> brgyList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(baseFilePath+"refbrgy.xlsx");
            wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            if (sheet != null) {
                for (Row row : sheet) {
                    if (row.getRowNum() > 0) {
                        if (410 == row.getCell(4).getNumericCellValue()) {
                            try {
                            	
                            	if(!(row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK)) {
                            		Barangay bg = new Barangay();
                                    brgyList.add(bg.buildBrgy(row));
                            	}
                                
                            } catch (Exception e) {
                                throw new RuntimeException("There was an error importing the data");
                            }
                        }
                    }
                }
                brgyRepo.saveAll(brgyList);
                wb.close();
                return true;
            } else {
                System.out.println("sheet null");
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public boolean importMuni() {
        List<Municipality> muniList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(baseFilePath+"mun.xlsx");
            wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);
            if (sheet != null) {
                for (Row row : sheet) {
                    if (row.getRowNum() > 0) {
                        if (410 == row.getCell(4).getNumericCellValue()) {
                            try {
                            	
                            	if(!(row.getCell(0) == null || row.getCell(0).getCellType() == CellType.BLANK)) {
                            		muniList.add(buildMuni(row));
                            	}
                                
                            } catch (Exception e) {
                                throw new RuntimeException("There was an error importing the data");
                            }
                        }
                    }

                }
                muniRepo.saveAll(muniList);
                wb.close();
                return true;
            } else {
                System.out.println("sheet null");
            }
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public List<Municipality> getAllMunicipality() {
        return muniRepo.findAll();
    }

    public List<Barangay> getAllBarangay() {
        return brgyRepo.findAll();
    }
    
    public List<Barangay> getAllBarangayByMuni(Integer muniCode) {
        return brgyRepo.findAllByCityCode(muniCode);
    }

    public Object getAllResident() {
        return residentRepo.findAll();
    }
}
