package com.New.LHS20.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.Nurse;


@Repository
public interface ReceptionRepository extends JpaRepository<AdmissionForm, Long> {

	List<AdmissionForm> findByRegdNo(long regdNo);

	boolean existsByRegdNo(long regdNo);
	
	@Query("From AdmissionForm where regdNo=?1 And admissionDate=?2 ")
	AdmissionForm findByRegdNoAndDate(Long id, String Date);

	List<AdmissionForm> findByAdmissionDate(String today);

 
	
//	@Query("UPDATE  admission_form  SET bedNo=?1  WHERE regd_no=1;")
//	List<AdmissionForm> setAdmissionForm(Long s, Long s1,String s2,String s3);
	
}
