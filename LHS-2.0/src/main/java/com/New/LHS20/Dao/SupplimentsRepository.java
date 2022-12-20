package com.New.LHS20.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.Suppliments;

@Repository
public interface SupplimentsRepository extends JpaRepository<Suppliments, Long> {

	List<Suppliments> findByPatient(Patient patient);

}
