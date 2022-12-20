package com.New.LHS20.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;

public interface MonitoringDataRepository extends JpaRepository<MonitoringData, Integer> {

	List<MonitoringData> findByPatient(Patient patient);
}
