package com.New.LHS20.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.New.LHS20.Dao.ReceptionRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Service.ReceptionServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ReceptionController {
	@Autowired
	ReceptionServiceImpl receptionserviceimpl;
	
	
//	receptionist can Update his profile
@PutMapping("/reception/myprofileupdate")
public ResponseEntity updateMyProfile(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {
		 
			
			     return  receptionserviceimpl.updateMyProfile(regformuFormUpdateDto);

			   
		}
//Receptionist can view his profile
	@GetMapping("/reception/myprofile/{userId}")
	public ResponseEntity viewmyprofile(@PathVariable long userId) {
		RegistrationForm regform = receptionserviceimpl.viewprofile(userId);
		System.out.println(regform);

		return new ResponseEntity(regform, HttpStatus.OK);

		
	}
 
  
//Receptionist can update admitted patient Details
	@PutMapping("/reception")
  public AdmissionForm updateAdmittedPatient(@RequestBody AdmissionForm admissionform) {
   System.out.println("receptioncontroller called");
  return  this.receptionserviceimpl.addAdmittedPatients(admissionform);

  }
	
	@Autowired
	ReceptionRepository receptionRepository;
	
	
    //Receptionist or Nurse can fetch admitted patient details  using pat id
	@GetMapping("/getPatientById")
	  public List<AdmissionForm> getPatientById(@RequestBody AdmissionForm admissionform) {	
		
	  return  receptionRepository.findByRegdNo(admissionform.getRegdNo());

	  }

	
	}
