package com.example.demo.service;

import com.example.demo.model.Resident;
import com.example.demo.repo.ResidentRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ScanService {

    private static final String DIRECTORY_TO_SCAN = "D:\\test\\VBtest\\ForUpdating";
    private static final String FILE_PATTERN = ".*ELBAT2024.*\\.xlsx";
    @Autowired
    private ResidentRepository residentRepo;


    public void processFiles() throws IOException {
        File dirToScan = new File(DIRECTORY_TO_SCAN);
        if(!dirToScan.exists()){
            dirToScan.mkdir();
        }
        Path dirPath = Paths.get(DIRECTORY_TO_SCAN);

        List<Path> files = Files.walk(dirPath)
                .filter(Files::isRegularFile)
                .filter(file -> file.toString().matches(FILE_PATTERN))
                .collect(Collectors.toList());

        for(Path filePath: files){
            System.out.println(filePath.toString());
            processAndUpdateRes(filePath.toFile());
//            File file = filePath.toFile();
        }
    }

    public void processAndUpdateRes(File file){

        try {
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);

            for (Row row : sheet){
                System.out.println(row.getCell(0));
                if(!commonUtil.isEmptyCell(row.getCell(0))){

                    Optional<Resident> resident = residentRepo.findById((long) row.getCell(0).getNumericCellValue());
                    if(resident.isPresent()){

                        residentRepo.save(resident.get().buildResident2(resident.get(),row));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
