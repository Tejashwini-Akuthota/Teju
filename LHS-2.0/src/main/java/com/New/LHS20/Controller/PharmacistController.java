package com.New.LHS20.Controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.New.LHS20.Dao.DoctorPrescriptionRepository;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Service.PharmacistServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PharmacistController {

    @Autowired
    SupplimentsRepository supplimentsRepository;

    @Autowired
    DoctorPrescriptionRepository doctorPrescriptionRepository;

//		Pharmacist can Update his profile
@PutMapping("/pharmacist/myprofileupdate")
	public ResponseEntity updateMyProfile(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {
			 
				
				     return  pharmacistServiceImpl.updateMyProfile(regformuFormUpdateDto);

				 
			}
//Pharmacist can view his profile
	@GetMapping("/pharmacist/myprofile/{userId}")
	public ResponseEntity viewmyprofile(@PathVariable long userId) {
		RegistrationForm regform = pharmacistServiceImpl.viewprofile(userId);
		System.out.println(regform);

		return new ResponseEntity(regform, HttpStatus.OK);

		
	}

    //pharmacist will add   amount for suppliments
    @PostMapping("/addingAmount")
    public ResponseEntity addingAmountForSuppliments(@RequestBody Suppliments suppliments) {

        return pharmacistServiceImpl.addingAmountForSuppliments(suppliments);
    }

    //pharmacist will add   amount for Medicines
    @PostMapping("/addingMedicinesAmount")
    public ResponseEntity addingMedicinesAmount(@RequestBody Doctor_Prescription doctor_presc) {

        return pharmacistServiceImpl.addingAmountForSuppliments(doctor_presc);
    }


    @Autowired
    PharmacistServiceImpl pharmacistServiceImpl;



    // Pharmacist can fetch supplements data  by using patient id
    @GetMapping("/supliments/{patient}")
    public ResponseEntity get(@PathVariable  Patient patient) {
     List<Suppliments> suppliments=pharmacistServiceImpl.fetchById(patient);

        return new ResponseEntity(suppliments , HttpStatus.OK);
    }



    // Pharmacist can fetch doctorprescription data  by using patient id
    @GetMapping("/docpresc/{patient}")
    public ResponseEntity get1(@PathVariable  Patient patient) {
     List<Doctor_Prescription> prsc=pharmacistServiceImpl.fetchById1(patient);

        return new ResponseEntity(prsc , HttpStatus.OK);
    }
}