package com.New.LHS20.Service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.RegistrationForm;

public interface PatientService {
	 public Collection<Doctor> findAll();
	 public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto) ;
}
