package com.New.LHS20.Dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;

public interface BillRepositry extends JpaRepository<Bill, Long > {

	@Query("from Bill where doctor=?1 and appointmentdate=?2 AND patient=?3")
	List<Bill> findByDoctorAndAppointdate(Doctor doctor,String string,Patient patient);
	
//	@Transactional
//	@Modifying
//	@Query("UPDATE Bill SET total=?1, medicalEquipments= ?2 WHERE doctor=?3 AND patient=?4")
//	int setBilltime(Long Total, Long medequip,Doctor doctor,Patient patient);
	
	@Transactional
	@Modifying
	@Query("UPDATE Bill SET total=?1, medicalEquipments= ?2,bill_date=?3, bill_time=?4,dischargedate=?5,dischargetime=?6 WHERE doctor=?7 AND patient=?8")
	int setBill(Long Total, Long medequip,LocalDate date, LocalTime localTime,LocalDate dischargedate,LocalTime dischargetime,Doctor doctor, Patient patient);

	List<Bill> findByPatient(Patient patient);
	
	
}
