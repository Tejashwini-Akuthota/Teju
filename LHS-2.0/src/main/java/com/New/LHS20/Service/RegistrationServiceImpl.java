package com.New.LHS20.Service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.New.LHS20.Dao.PatientRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dto.RegistrationDto;
import com.New.LHS20.Entity.Authorities;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;
import com.New.LHS20.Exception.RegistrationCustomException;
import com.New.LHS20.Exception.ResourceAlreadyExistsException;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	// Set<RegistrationForm> set = new HashSet<>();

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private SlotRepo slotRepo;

	
	//user registration
	public  Patient addUser(RegistrationDto registrationDto) {

		RegistrationForm registrationForm = new RegistrationForm();
		 

		ModelMapper modelmapper = new ModelMapper();
		if (registrationDto == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (registrationRepository.existsByUsername(registrationDto.getUsername())) {
			throw new RegistrationCustomException("702", "Username Already Exists please enter different One");
		}else if (registrationRepository.existsByPhoneNo(registrationDto.getPhoneNo())) {
            System.out.println(registrationRepository.existsByPhoneNo(registrationDto.getPhoneNo()));
            throw new ResourceAlreadyExistsException("Phone number already exists please register with different phone number");
        } else

		{
        	
        	
			modelmapper.map(registrationDto, registrationForm);
			
			BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
			registrationForm.setPassword(bcryptpasswordencoder.encode(registrationForm.getPassword()));
			Authorities authorities = new Authorities();
			Patient patient=new Patient();

			authorities.setRoleName("USER");
			registrationForm.setRoleName(authorities.getRoleName());

			registrationForm.getRoles().add(authorities);

			registrationRepository.save(registrationForm);
			ModelMapper modelmapper1 = new ModelMapper();
			modelmapper1.map(registrationForm, patient);

			return  patientRepository.save(patient);
 
		}

	}
	 

}
