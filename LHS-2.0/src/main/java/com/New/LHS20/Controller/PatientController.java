package com.New.LHS20.Controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Service.PatientServiceImpl;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin("*")
public class PatientController {
	
 
	 
        @Autowired
	    private  PatientServiceImpl patientServiceImpl;
 
      
        
        
       
     // patient can Update his profile
        @PutMapping("/myprofileupdate")
     	public ResponseEntity updateMyProfile(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {
     			 
     				
     				     return  patientServiceImpl.updateMyProfile(regformuFormUpdateDto);

     				 
     			}
        
        
      //Patient can view his profile
    	@GetMapping("/myprofile/{userId}")
    	public ResponseEntity viewmyprofile(@PathVariable long userId) {
    		RegistrationForm regform = patientServiceImpl.viewprofile(userId);
    		System.out.println(regform);

    		return new ResponseEntity(regform, HttpStatus.OK);

    		
    	}
        
        
        //patient can find all the list of doctors with speciality
	    @GetMapping("/alldoctors")
	    Collection<Doctor> findAll() {
	        return patientServiceImpl.findAll();
	        
	        }
	    
	    
	    
	    
	    
	    
}
