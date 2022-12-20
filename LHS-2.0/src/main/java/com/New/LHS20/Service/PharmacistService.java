package com.New.LHS20.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.Suppliments;

public interface PharmacistService {
	 public List<Suppliments> fetchById(Patient patient) ;
	 public List<Doctor_Prescription> fetchById1(Patient patient);
	 public ResponseEntity addingAmountForSuppliments(@RequestBody Suppliments suppliments);
	 public ResponseEntity addingAmountForSuppliments(@RequestBody Doctor_Prescription doctor_presc);

}
