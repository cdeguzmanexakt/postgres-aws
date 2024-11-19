package com.example.demo.repo;

import com.example.demo.model.LocationStats;
import com.example.demo.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Long> {

    List<Resident> findByBrgyCode(String brgyCode);

    List<Resident> findByMuniCode(String muniCode);

    @Query(value = "SELECT * FROM resident WHERE UPPER(brgy_code) LIKE CONCAT('%',UPPER(:brgyCode),'%')",nativeQuery = true)
    List<Resident> findByLikeBrgyCode(@Param("brgyCode") String brgyCode);

    @Query(value = "SELECT * FROM resident WHERE UPPER(brgy_code) LIKE CONCAT('%',UPPER(:brgyCode),'%') " +
            "AND UPPER(muni_code) LIKE CONCAT('%',UPPER(:muniCode),'%')",nativeQuery = true)
    List<Resident> findByLikeMuniCodeAndBrgyCode(@Param("muniCode") String muniCode, @Param("brgyCode") String brgyCode);

    @Query(value = "SELECT COUNT(id) FROM resident" +
            " WHERE UPPER(brgy_code) LIKE CONCAT('%',UPPER(:brgyCode),'%')" +
            " AND vb_flag = true",nativeQuery = true)
    Integer countTotalVbByBrgy(@Param("brgyCode") String brgyCode);

    @Query(value = "SELECT COUNT(id) FROM resident WHERE UPPER(brgy_code) LIKE CONCAT('%',UPPER(:brgyCode),'%')", nativeQuery = true)
    Integer countTotalByBrgy(@Param("brgyCode") String brgyCode);

    @Query(value = "SELECT * FROM resident WHERE UPPER(muni_code) LIKE CONCAT('%',UPPER(:muniCode),'%')",nativeQuery = true)
    List<Resident> findByLikeMuniCode(@Param("muniCode") String muniCode);

    @Query(value = "SELECT COUNT(id) FROM resident WHERE UPPER(muni_code) LIKE CONCAT('%',UPPER(:muniCode),'%')", nativeQuery = true)
    Integer countTotalByMuni(@Param("muniCode") String muniCode);

    @Query(value = "SELECT COUNT(id) FROM resident WHERE UPPER(muni_code) LIKE CONCAT('%',UPPER(:muniCode),'%') " +
            "AND vb_flag = true", nativeQuery = true)
    Integer countTotalVbByMuni(@Param("muniCode") String muniCode);
    
    @Query(value = "SELECT COUNT(id) FROM resident", nativeQuery = true)
    Integer countTotal();
    
    
    @Query(value = "SELECT COUNT(id) FROM resident WHERE vb_flag = true", nativeQuery = true)
    Integer countTotalVb();
    
    
    

    
   
    
    Resident findByFirstNameAndLastNameAndMobileNumAndAge(String firstName, String lastName, String mobileNum, int age);




}
