package com.example.demo.repo;

import com.example.demo.model.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunisipalityRepository extends JpaRepository<Municipality,Integer> {

}
