package com.New.LHS20.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;
@Repository
public interface DoctorPrescriptionRepository extends JpaRepository<Doctor_Prescription, Long> {

	List<Doctor_Prescription> findByPatient(Patient patient);

}
