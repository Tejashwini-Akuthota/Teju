package com.New.LHS20.Dao;

 
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Patient;
 

 

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findByUserId(long userId);

	    
}
