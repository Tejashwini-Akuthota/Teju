package com.New.LHS20.Service;

import org.springframework.http.ResponseEntity;

import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.AdmissionForm;

public interface ReceptionService {
	
	public AdmissionForm addAdmittedPatients(AdmissionForm admissionform);
	
	public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto);
}
