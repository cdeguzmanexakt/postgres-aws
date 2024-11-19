package com.example.demo.controller;

import com.example.demo.model.LocationStats;
import com.example.demo.model.Resident;
import com.example.demo.model.ValidResponse;
import com.example.demo.service.ResidentLocService;
import com.example.demo.service.ResidentProfileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/resident")
public class ResidentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResidentController.class);
    @Autowired
    private ResidentLocService resLocService;
    @Autowired
    private ResidentProfileService profileService;


    @GetMapping("/{brgyCode}")
    public List<Resident> findAllResByBrgy(@PathVariable("brgyCode") String brgyCode){
        return resLocService.findAllByBrgyCode2(brgyCode);
    }

    @GetMapping("/vb/{brgyCode}")
    public LocationStats findVbTrueResByBrgy(@PathVariable("brgyCode") String brgyCode){
        return resLocService.findVbTrueResByBrgy(brgyCode);
    }

    @PostMapping
    public void importResidentData() throws Exception {
//         resLocService.readExcelUsingEventAPI();
    }

    @GetMapping("/muni/{muniCode}")
    public List<Resident> findAllResByMuni(@PathVariable("muniCode") String muniCode){
        return resLocService.findAllByMuniCode2(muniCode);
    }

    @GetMapping("/muni/vb/{muniCode}")
    public LocationStats findVbTrueResByMuni(@PathVariable("muniCode") String muniCode){

        return resLocService.findVbTrueResByMuni(muniCode);
    }
    
    @PutMapping("/update")
    public ResponseEntity<ValidResponse> updateResident(@RequestBody Resident req){
    	ValidResponse res = new ValidResponse();
    	try {
    		res.Build("OK", profileService.updateResident(req));
    		return new ResponseEntity<ValidResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.Build("ERROR", e.getMessage());
			return new ResponseEntity<ValidResponse>(res, HttpStatus.BAD_REQUEST);
		}
    	
    	
    }
    
    @PostMapping("/create")
    public ResponseEntity<ValidResponse> createResident(@RequestBody Resident req){
    	ValidResponse res = new ValidResponse();
    	try {
    		res.Build("OK", profileService.createResident(req));
    		return new ResponseEntity<ValidResponse>(res, HttpStatus.OK);
		} catch (Exception e) {
			res.Build("ERROR", e.getMessage());
			return new ResponseEntity<ValidResponse>(res, HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
    	
    	
    }
}
