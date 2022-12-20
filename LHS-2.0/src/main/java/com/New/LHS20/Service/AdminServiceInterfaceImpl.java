package com.New.LHS20.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.New.LHS20.Dao.AdminRepository;
import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Dao.LocationRepository;
import com.New.LHS20.Dao.NurseRepository;
import com.New.LHS20.Dao.PoliciesRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Authorities;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Location;
import com.New.LHS20.Entity.Nurse;
import com.New.LHS20.Entity.Policies;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Exception.RegistrationCustomException;
import com.New.LHS20.Exception.ResourceAlreadyExistsException;

@Service
public class AdminServiceInterfaceImpl implements AdminServiceInterface {

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private LocationRepository locationrepository;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private NurseRepository nurserepository;

	@Autowired
	private PoliciesRepository policiesrepository;

	// Admin can register himself
	public String addAdmin(RegistrationForm registrationForm) {

		System.out.println("Add admin method called");

		if (registrationForm == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (registrationRepository.existsByUsername(registrationForm.getUsername())) {
			throw new RegistrationCustomException("707", "Username Already Exists please enter different One");
		} else if (registrationRepository.existsByPhoneNo(registrationForm.getPhoneNo())) {

			throw new ResourceAlreadyExistsException(
					"Phone number already exists please register with different phone number");
		} else

		{
			BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
			registrationForm.setPassword(bcryptpasswordencoder.encode(registrationForm.getPassword()));

			Authorities authorities = new Authorities();

			authorities.setRoleName("ADMIN");
			registrationForm.setRoleName(authorities.getRoleName());

			registrationForm.getRoles().add(authorities);

			registrationRepository.save(registrationForm);

			return "saved";

		}
	}

	// Admin can register doctor
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Doctor addDoctor(RegistrationForm registrationForm) {
//	(RegistrationForm registrationForm) ->{
		System.out.println("Hello");

		if (registrationForm == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (registrationRepository.existsByUsername(registrationForm.getUsername())) {
			throw new RegistrationCustomException("707", "Username Already Exists please enter different One");
		} else if (registrationRepository.existsByPhoneNo(registrationForm.getPhoneNo())) {
			System.out.println(registrationRepository.existsByPhoneNo(registrationForm.getPhoneNo()));
			throw new ResourceAlreadyExistsException(
					"Phone number already exists please register with different phone number");
		} else

		{
			BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
			registrationForm.setPassword(bcryptpasswordencoder.encode(registrationForm.getPassword()));
			Authorities authorities = new Authorities();
			Doctor doctor = new Doctor();

			authorities.setRoleName("DOCTOR");
			registrationForm.setRoleName(authorities.getRoleName());

			registrationForm.getRoles().add(authorities);

			registrationRepository.save(registrationForm);
			ModelMapper modelmapper = new ModelMapper();
			modelmapper.map(registrationForm, doctor);

			return doctorRepository.save(doctor);

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

	// Admin can register nurse
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Nurse addNurse(RegistrationForm registrationForm) {

		if (registrationForm == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (registrationRepository.existsByUsername(registrationForm.getUsername())) {
			throw new RegistrationCustomException("707", "Username Already Exists please enter different One");
		} else if (registrationRepository.existsByPhoneNo(registrationForm.getPhoneNo())) {
			System.out.println(registrationRepository.existsByPhoneNo(registrationForm.getPhoneNo()));
			throw new ResourceAlreadyExistsException(
					"Phone number already exists please register with different phone number");
		} else

		{
			BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
			registrationForm.setPassword(bcryptpasswordencoder.encode(registrationForm.getPassword()));
			Authorities authorities = new Authorities();
			Nurse nurse = new Nurse();
			authorities.setRoleName("NURSE");
			registrationForm.setRoleName(authorities.getRoleName());

			registrationForm.getRoles().add(authorities);
			registrationRepository.save(registrationForm);
			ModelMapper modelmapper = new ModelMapper();
			modelmapper.map(registrationForm, nurse);

			return nurserepository.save(nurse);

		}
	}

	// Admin can register pharmacist
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String addPharmacist(RegistrationForm registationForm) {

		if (registationForm == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (registrationRepository.existsByUsername(registationForm.getUsername())) {
			throw new RegistrationCustomException("707", "Username Already Exists please enter different One");
		} else if (registrationRepository.existsByPhoneNo(registationForm.getPhoneNo())) {
			System.out.println(registrationRepository.existsByPhoneNo(registationForm.getPhoneNo()));
			throw new ResourceAlreadyExistsException(
					"Phone number already exists please register with different phone number");
		} else

		{
			BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
			registationForm.setPassword(bcryptpasswordencoder.encode(registationForm.getPassword()));
			Authorities authorities = new Authorities();

			authorities.setRoleName("PHARMACIST");

			registationForm.setRoleName(authorities.getRoleName());

			registationForm.getRoles().add(authorities);

			registrationRepository.save(registationForm);
			return "saved";
		}
	}

	// Admin can register receptionist
//	@PreAuthorize("hasAuthority('ADMIN')")
	public String addReceptionist(RegistrationForm registrationForm) {
		if (registrationForm == null) {
			throw new RuntimeException("null found in registration plss check");
		} else if (registrationRepository.existsByUsername(registrationForm.getUsername())) {
			throw new RegistrationCustomException("707", "Username Already Exists please enter different One");
		} else if (registrationRepository.existsByPhoneNo(registrationForm.getPhoneNo())) {
			System.out.println(registrationRepository.existsByPhoneNo(registrationForm.getPhoneNo()));
			throw new ResourceAlreadyExistsException(
					"Phone number already exists please register with different phone number");
		} else {
			BCryptPasswordEncoder bcryptpasswordencoder = new BCryptPasswordEncoder();
			registrationForm.setPassword(bcryptpasswordencoder.encode(registrationForm.getPassword()));
			Authorities authorities = new Authorities();
			authorities.setRoleName("RECEPTIONIST");
			registrationForm.setRoleName(authorities.getRoleName());
			registrationForm.getRoles().add(authorities);
			registrationRepository.save(registrationForm);
			return "saved";
		}
	}

//	@PreAuthorize("hasAuthority('ADMIN')")
	public List<RegistrationForm> getRole(String rolename) {
		return registrationRepository.findByRoleName(rolename);
	}


	
	// Admin can update details for doctor
	public ResponseEntity updateDoctor(RegistrationFormUpdateDto registrationFormUpdateDto) {
		
		     
		  List<RegistrationForm> registrationForm = registrationRepository.findByUserId(registrationFormUpdateDto.getUserId());

		RegistrationForm registrationForm1=  registrationForm.get(0);
	      System.err.println(registrationFormUpdateDto);
	      
		if (registrationFormUpdateDto == null) {
			throw new RuntimeException("Please Enter All The Fields");
			 
		}else if(registrationForm1.getPhoneNo().equals(registrationFormUpdateDto.getPhoneNo())){
		

		}else if (registrationRepository.existsByPhoneNo(registrationFormUpdateDto.getPhoneNo())) {
		

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
				  
			 
				  
				  
		      Doctor doctor1 = doctorRepository.findById(registrationForm1.getUserId());

 

			  ModelMapper mapper1 = new ModelMapper();
			  mapper1.map(registrationForm1, doctor1);
			  



			  doctorRepository.save(doctor1);
			   
			  return new ResponseEntity("Updated Successfully",HttpStatus.OK);

		} else {
			return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
		}
	}

	// Admin can update details for NURSE
		public ResponseEntity updateNurse(RegistrationFormUpdateDto registrationFormUpdateDto) {
			
			     
		     
			  List<RegistrationForm> registrationForm = registrationRepository.findByUserId(registrationFormUpdateDto.getUserId());

			          RegistrationForm registrationForm1=  registrationForm.get(0);

			
		      System.err.println(registrationFormUpdateDto);
		      
			if (registrationFormUpdateDto == null) {
				throw new RuntimeException("Please Enter All The Fields");
				 
			}else if(registrationForm1.getPhoneNo().equals(registrationFormUpdateDto.getPhoneNo())){
			
		
			}else if (registrationRepository.existsByPhoneNo(registrationFormUpdateDto.getPhoneNo())) {
			

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
				 
					  
					  
			      Nurse nurse = nurserepository.findByUserId(registrationForm1.getUserId());

	 

				  ModelMapper mapper1 = new ModelMapper();
				  mapper1.map(registrationForm1, nurse);
				  



				  nurserepository.save(nurse);
				   
				  return new ResponseEntity("Updated Successfully",HttpStatus.OK);

			} else {
				return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
			}
		}


		// Admin can update details for Pharmacist
				public ResponseEntity updatePharmacist(RegistrationFormUpdateDto registrationFormUpdateDto) {
					
					     
				     
					  List<RegistrationForm> registrationForm = registrationRepository.findByUserId(registrationFormUpdateDto.getUserId());

					          RegistrationForm registrationForm1=  registrationForm.get(0);

					
				      System.err.println(registrationFormUpdateDto);
				      
					if (registrationFormUpdateDto == null) {
						throw new RuntimeException("Please Enter All The Fields");
						 
					}else if(registrationForm1.getPhoneNo().equals(registrationFormUpdateDto.getPhoneNo())){
					
				
					}else if (registrationRepository.existsByPhoneNo(registrationFormUpdateDto.getPhoneNo())) {
					

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
						 
							  
				   
				  return new ResponseEntity("Updated Successfully",HttpStatus.OK);

			} else {
				return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
			}
		}

				// Admin can update details for Receptionist
				public ResponseEntity updateReceptionist(RegistrationFormUpdateDto registrationFormUpdateDto) {
					
					     
				     
					  List<RegistrationForm> registrationForm = registrationRepository.findByUserId(registrationFormUpdateDto.getUserId());

					          RegistrationForm registrationForm1=  registrationForm.get(0);

					
				      System.err.println(registrationFormUpdateDto);
				      
					if (registrationFormUpdateDto == null) {
						throw new RuntimeException("Please Enter All The Fields");
						 
					}else if(registrationForm1.getPhoneNo().equals(registrationFormUpdateDto.getPhoneNo())){
					
				
					}else if (registrationRepository.existsByPhoneNo(registrationFormUpdateDto.getPhoneNo())) {
					

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
						 
							  
				   
				  return new ResponseEntity("Updated Successfully",HttpStatus.OK);

			} else {
				return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
			}
		}

	/// fetching by id
	public RegistrationForm fetchById(long userId) {

		Optional<RegistrationForm> registrationForm = registrationRepository.findById(userId);

		if (registrationForm.isPresent()) {

			return registrationForm.get();

		} else {

			throw new RuntimeException("User not found  for the id" + userId);
		}

	}

	// admin Deleting doctor By using id

	public String DeleteById(long userId) {
		List<RegistrationForm> regform = registrationRepository.findByUserId(userId);
		RegistrationForm form = regform.get(0);
		Doctor doctor = doctorRepository.getById(userId);
		Long docid = doctor.getId();

		if (docid.equals(form.getUserId())) {
			Doctor doctor1 = doctorRepository.getById(userId);
			registrationRepository.delete(regform.get(0));
			doctorRepository.deleteById(doctor1.getId());
			return "Registered User is deleted with id" + userId;
		} else {

			throw new RuntimeException("User not found  for the id" + userId);
		}
	}

	// admin Deleting nurse By using id

	public String DeleteById1(long userId) {
		List<RegistrationForm> registrationForm = registrationRepository.findByUserId(userId);
		RegistrationForm registrationForm1 = registrationForm.get(0);
		Nurse nurse = nurserepository.getById(userId);
		Long nurseid = nurse.getUserId();

		if (nurseid.equals(registrationForm1.getUserId())) {
			Nurse nurse1 = nurserepository.getById(userId);
			registrationRepository.delete(registrationForm.get(0));
			nurserepository.deleteById(nurse1.getUserId());
			return "Registered User is deleted with id" + userId;
		} else {

			throw new RuntimeException("User not found  for the id" + userId);
		}
	}

	// admin Deleting Pharmacist By using id

	public String DeleteById2(long userId) {
		List<RegistrationForm> registrationForm1 = registrationRepository.findByUserId(userId);
		RegistrationForm registrationform = registrationForm1.get(0);
		registrationRepository.deleteById(registrationform.getUserId());

		return "Registered User is deleted with id" + userId;

	}

	// admin Deleting Receptionist By using id

	public String DeleteById3(long userId) {
		List<RegistrationForm> registrationForm1 = registrationRepository.findByUserId(userId);
		RegistrationForm registrationform = registrationForm1.get(0);
		registrationRepository.deleteById(registrationform.getUserId());

		return "Registered User is deleted with id" + userId;

	}

	// Adding Location
	public void addLocation(Location location) {
		locationRepository.save(location);

	}

	// fetching all the locations
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	// Adding policies
	public void addPolicy(Policies policies) {
		LocalDate date = LocalDate.now();
		LocalTime localTime = LocalTime.now();

		policies.setDate(date);
		policies.setTime(localTime);

		policiesrepository.save(policies);

	}

	// fetching all the policies
	public List<Policies> findAll1() {
		return policiesrepository.findAll();
	}

	// Admin deleting the locations
	public String DeleteLocation(int id) {

		Optional<Location> location = locationrepository.findById(id);
		System.err.println(location);
		Location location1 = location.get();
		locationrepository.deleteById(location1.getId());

		return " Location is deleted with Id:" + id;

	}
	//Admin can view his/her profile
 
		public RegistrationForm viewprofile(long userId) {
			System.err.println(userId);

			Optional<RegistrationForm> registrationForm = registrationRepository.findById(userId);

			if (registrationForm.isPresent()) {

				return registrationForm.get();

			} else {

				throw new RuntimeException("Please provide Correct Id" + userId);
			}

		}

	
	       // Admin can update his profile(MyProfile)

          public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto) {
			
			                   System.out.println(registrationFormUpdateDto.getUserId());
		     
			  List<RegistrationForm> registrationForm = registrationRepository.findByUserId(registrationFormUpdateDto.getUserId());
                        
			          RegistrationForm registrationForm1=  registrationForm.get(0);

			
		    
		      
			if (registrationFormUpdateDto == null) {
				throw new RuntimeException("Please Enter All The Fields");
				 
			}else if(registrationForm1.getPhoneNo().equals(registrationFormUpdateDto.getPhoneNo())){
			
		
			}else if (registrationRepository.existsByPhoneNo(registrationFormUpdateDto.getPhoneNo())) {
			

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
				 
					  
		   
		  return new ResponseEntity("Your Profile Updated Successfully",HttpStatus.OK);

	} else {
		return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
	}
}
		

	
	public List<Doctor> getDoctors() {
		 
		return doctorRepository.findAll();
		                          
		
	 
		}
}
