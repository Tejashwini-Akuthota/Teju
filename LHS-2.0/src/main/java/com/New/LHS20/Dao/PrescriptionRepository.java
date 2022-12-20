package com.New.LHS20.Dao;

import java.util.List;

import javax.print.Doc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;

public interface PrescriptionRepository extends JpaRepository<Doctor_Prescription, Long>{

	List<Doctor_Prescription> findByDoctor(Doctor doctor);
	
	List<Doctor_Prescription> findByPatient(Patient patient);
	
}
