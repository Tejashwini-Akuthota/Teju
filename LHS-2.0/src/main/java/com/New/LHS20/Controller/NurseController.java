package com.New.LHS20.Controller;

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

import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Dao.MonitoringDataRepository;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Service.NurseServiceImpl;

@RestController
@RequestMapping("/nurse")
@CrossOrigin("*")
public class NurseController {
    @Autowired
    private NurseServiceImpl nurseserviceImpl;
    @Autowired
    private RegistrationRepository registrationrepository;
    @Autowired
    private SlotRepo slotrepo;

    @Autowired
    private MonitoringDataRepository monitoringdatarepository;


     
    //Nurse can able to see all the admitted patients who admitted today
    @GetMapping("/pull")
    public ResponseEntity getAllAdmittedPatients()

    {
    	System.err.println("Api called");
    	
        return nurseserviceImpl.getAllAdmittedPatients();
    }
    
    
    
	// Nurse can Update his profile
    @PutMapping("/myprofileupdate")
 	public ResponseEntity updateMyProfile(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {
 			 
 				
 				     return  nurseserviceImpl.updateMyProfile(regformuFormUpdateDto);

 				 
 			}
    
    //Nurse can view his profile
  		@GetMapping("/myprofile/{userId}")
  		public ResponseEntity viewmyprofile(@PathVariable long userId) {
  			RegistrationForm regform = nurseserviceImpl.viewprofile(userId);
  			System.out.println(regform);

  			return new ResponseEntity(regform, HttpStatus.OK);

  			
  		}
    // Nurse was able to see all the appointments upto one week
    @GetMapping("/getupcommingappointments")
    public ResponseEntity getupcommingappointments() {
        return nurseserviceImpl.getupcommingappointments();
    }

    // Nurse can able to see today appointments
    @GetMapping("/gettodayappointments")
    public ResponseEntity getcurrentappointments() {
      return nurseserviceImpl.getcurrentappointments();
    }

    
    //Nurse can add suppliments
    @PostMapping("/addsuppliments")
    public ResponseEntity addsuppliments(@RequestBody Suppliments suppliments) {

        return nurseserviceImpl.addsuppliments(suppliments);

    }

    //Nurse can add Monitoring Data
    @PostMapping("/addMonitoringData")
    public ResponseEntity addMonitoringData(@RequestBody MonitoringData monitoringData) {
        return nurseserviceImpl.addMonitoringData(monitoringData);
    }

}