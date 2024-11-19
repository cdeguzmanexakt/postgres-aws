package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Resident;
import com.example.demo.repo.ResidentRepository;

@Service
public class ResidentProfileService {

	private final ResidentRepository residentRepo;

	public ResidentProfileService(ResidentRepository residentRepo) {
		super();
		this.residentRepo = residentRepo;
	}

	public Resident updateResident(Resident req) throws Exception {
		if (req.getId() == null || req.getId() == 0) {
			throw new Exception("id required and cannot be zero");
		}
		residentRepo.save(req);
		return req;
	};

	public Resident createResident(Resident req) throws Exception {
		if(validateExist(req)) {
			throw new Exception("already exist");
		}
		residentRepo.save(req);
		return req;
	};

	public boolean validateExist(Resident req) {

		Resident test = residentRepo.findByFirstNameAndLastNameAndMobileNumAndAge(req.getFirstName(), req.getLastName(),
				req.getMobileNum(), req.getAge());
			
		return test == null ? false : true;
	}

}
