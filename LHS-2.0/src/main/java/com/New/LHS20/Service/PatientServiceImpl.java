package com.New.LHS20.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Dao.PatientRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Nurse;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Exception.ResourceAlreadyExistsException;


 
@Service
public class PatientServiceImpl  implements PatientService{
	 
   @Autowired
   private  DoctorRepository doctorRepository;
   
   @Autowired
   private RegistrationRepository regrepo;
   
   @Autowired
   private PatientRepository patientrepository;
   
   @Autowired
   private RegistrationRepository registrationRepository;
   
 
     
   
   
   //Patient can find the list of doctors
	    public Collection<Doctor> findAll() {
	        return doctorRepository.findAll();
	    }
	    
	    // Patient can update his profile(MyProfile)
	 	public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto) {

	 		System.out.println(registrationFormUpdateDto.getUserId());

	 		List<RegistrationForm> registrationForm = registrationRepository
	 				.findByUserId(registrationFormUpdateDto.getUserId());

	 		RegistrationForm registrationForm1 = registrationForm.get(0);

	 		if (registrationFormUpdateDto == null) {
	 			throw new RuntimeException("Please Enter All The Fields");

	 		} else if (registrationForm1.getPhoneNo().equals(registrationFormUpdateDto.getPhoneNo())) {

	 		} else if (registrationRepository.existsByPhoneNo(registrationFormUpdateDto.getPhoneNo())) {

	 			throw new ResourceAlreadyExistsException(
	 					"Phone number already exists please register with different phone number");
	 		}

	 		if (registrationRepository.existsById(registrationFormUpdateDto.getUserId())) {

	 			System.err.println(registrationForm1);

	 			registrationForm1.setFirstName(registrationFormUpdateDto.getFirstName());
	 			registrationForm1.setLastName(registrationFormUpdateDto.getLastName());
	 			registrationForm1.setDob(registrationFormUpdateDto.getDob());
	 			registrationForm1.setEmail(registrationFormUpdateDto.getEmail());
	 			registrationForm1.setGender(registrationFormUpdateDto.getGender());
	 			registrationForm1.setPhoneNo(registrationFormUpdateDto.getPhoneNo());
	 			registrationForm1.setUsername(registrationFormUpdateDto.getEmail());

	 			registrationRepository.save(registrationForm1);

	 			  
	 		      Patient patient = patientrepository.findByUserId(registrationForm1.getUserId());

	   System.err.println(patient);

	 			  ModelMapper mapper1 = new ModelMapper();
	 			  mapper1.map(registrationForm1, patient);
	 			  



	 			  patientrepository.save(patient);
	 			   
	 			  return new ResponseEntity("Your Profile Updated Successfully",HttpStatus.OK);

	 		} else {
	 			return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
	 		}
	 	}
		  
		  
 
	 	
	 	//Patient can view his/her profile
		 
			public RegistrationForm viewprofile(long userId) {
				System.err.println(userId);

				Optional<RegistrationForm> registrationForm = registrationRepository.findById(userId);

				if (registrationForm.isPresent()) {

					return registrationForm.get();

				} else {

					throw new RuntimeException("Please provide Correct Id" + userId);
				}

			}

	    



}


