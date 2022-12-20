package com.New.LHS20.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.Suppliments;

public interface NurseService {

	public ResponseEntity getAllAdmittedPatients();
	public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto) ;
	public ResponseEntity getupcommingappointments() ;
	public ResponseEntity getcurrentappointments() ;
	public ResponseEntity addsuppliments(@RequestBody Suppliments suppliments) ;
	public ResponseEntity addMonitoringData(@RequestBody MonitoringData monitoringData);
}
