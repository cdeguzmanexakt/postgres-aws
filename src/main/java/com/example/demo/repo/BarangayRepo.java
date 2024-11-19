package com.example.demo.repo;

import com.example.demo.model.Barangay;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangayRepo extends JpaRepository<Barangay,Integer> {
	
	List<Barangay> findAllByCityCode(Integer muniCode);

	List<Barangay> findAllByCityDesc(String muniCode);
	

}
