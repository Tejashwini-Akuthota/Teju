package com.New.LHS20.Controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dto.JwtRequestPayload;
import com.New.LHS20.Dto.JwtResponse;
import com.New.LHS20.Dto.RegistrationDto;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Location;
import com.New.LHS20.Entity.Policies;
//
//import com.New.LHS20.Dto.JwtRequestPayload;
//import com.New.LHS20.Dto.JwtResponse;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;
import com.New.LHS20.Security.JWTUtility;
//import com.New.LHS20.Security.JWTUtility;
import com.New.LHS20.Security.MyUserDetails;
import com.New.LHS20.Service.AdminServiceInterfaceImpl;
import com.New.LHS20.Service.RegistrationServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	JWTUtility jwtUtility;

	@Autowired
	MyUserDetails myUserDetails;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	SlotRepo slotRepo;

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminServiceInterfaceImpl adminServiceInterfaceImpl;

	// Admin can register himself
	@PostMapping("/admin")
	public ResponseEntity addAdmin(@RequestBody @Valid   RegistrationForm regform) {

		adminServiceInterfaceImpl.addAdmin(regform);

		return new ResponseEntity("Registered successfully", HttpStatus.OK);

	}

	// Admin will register Doctor
	@PostMapping("/doctor")

	public ResponseEntity addDoctor(@RequestBody  @Valid RegistrationForm regform) {

		adminServiceInterfaceImpl.addDoctor(regform);
		System.out.println("Hello");

		return new ResponseEntity("Registered successfully", HttpStatus.OK);

	}

	// admin will register Nurse
	@PostMapping("/nurse")

	public ResponseEntity addNurse(@RequestBody  @Valid RegistrationForm regform) {

		System.out.println("Hello");
		adminServiceInterfaceImpl.addNurse(regform);

		return new ResponseEntity("Registered successfully", HttpStatus.OK);

	}

	// admin will register pharmacist
	@PostMapping("/pharmacist")

	public ResponseEntity addPharmacist(@RequestBody @Valid RegistrationForm regform) {

		System.out.println("Hello");
		adminServiceInterfaceImpl.addPharmacist(regform);

		return new ResponseEntity("Registered successfully", HttpStatus.OK);
	}

	// admin will register receptionist
	@PostMapping("/receptionist")

	public ResponseEntity addReceptionist(@RequestBody @Valid  RegistrationForm regform) {

		System.out.println("Hello");
		adminServiceInterfaceImpl.addReceptionist(regform);

		return new ResponseEntity("Registered successfully", HttpStatus.OK);
	}

	@Autowired
	DoctorRepository doctorRepository;
	
	
	// Admin can Update his profile
		@PutMapping("/myprofile1/update")
		public ResponseEntity updateMyProfile(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {
		 
			
			     return adminServiceInterfaceImpl.updateMyProfile(regformuFormUpdateDto);

			 
		}
		
	//Admin can view his profile
		@GetMapping("/admin/myprofile/{userId}")
		public ResponseEntity viewmyprofile(@PathVariable long userId) {
			RegistrationForm regform = adminServiceInterfaceImpl.fetchById(userId);
			System.out.println(regform);

			return new ResponseEntity(regform, HttpStatus.OK);

			
		}

	// Admin can Update Details for doctor
	@PutMapping("/doctor/update")
	public ResponseEntity updateDoctor(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {

		 return adminServiceInterfaceImpl.updateDoctor(regformuFormUpdateDto);

		 
	}

	// Admin can Update Details for nurse
	@PutMapping("/nurse/update")
	public ResponseEntity updateNurse(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {

		 return adminServiceInterfaceImpl.updateNurse(regformuFormUpdateDto);

		 
	}

	// Admin can update details for receptionist
	@PutMapping("/reception/update")
	public ResponseEntity updatereceptionist(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {
		 return adminServiceInterfaceImpl.updateReceptionist(regformuFormUpdateDto);
	}

	// Admin can update details for pharmacist
	@PutMapping("/pharmacist/update")
	public ResponseEntity update3(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {

		return adminServiceInterfaceImpl.updatePharmacist(regformuFormUpdateDto);
	}

	// admin can fetch employee details by using id
	@GetMapping("/reg/{userId}")
	public ResponseEntity get(@PathVariable long userId) {
		RegistrationForm regform = adminServiceInterfaceImpl.fetchById(userId);
		System.out.println(regform);

		return new ResponseEntity(regform, HttpStatus.OK);
	}

	// admin can Delete doctor details by using id
	@DeleteMapping("/doctor/{userId}")
	public ResponseEntity delete1(@PathVariable long userId) {
		adminServiceInterfaceImpl.DeleteById(userId);
		return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
	}

	// admin can Delete nurse details by using id
	@DeleteMapping("/nurse/{userId}")
	public ResponseEntity delete2(@PathVariable long userId) {
		adminServiceInterfaceImpl.DeleteById1(userId);
		return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
	}

	// admin can Delete pharmacist and receptionist details by using id
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity delete3(@PathVariable long userId) {
		adminServiceInterfaceImpl.DeleteById2(userId);
		return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
	}

	// admin fetching employees by using role name
	@GetMapping("/{roleName}")
	public List<RegistrationForm> getUser(@PathVariable("roleName") String rolename) {
		return adminServiceInterfaceImpl.getRole(rolename);
	}
	
	// admin can fetch list  of doctors

		@GetMapping("/getdoctors")
		public List<Doctor> getdoctor() {
			return adminServiceInterfaceImpl.getDoctors();
		}

	// admin can add Location
	@PostMapping("/addLocation")
	public ResponseEntity addLocation(@RequestBody Location location) {

		adminServiceInterfaceImpl.addLocation(location);

		return new ResponseEntity("Location added successfully", HttpStatus.OK);
	}

	// admin can delete locations
	@DeleteMapping("/deletelocation/{Id}")
	public ResponseEntity delete2(@PathVariable int Id) {
		adminServiceInterfaceImpl.DeleteLocation(Id);
		return new ResponseEntity("Deleted Successfully", HttpStatus.OK);
	}

	// admin can add new policies
	@PostMapping("/addpolicies")

	public ResponseEntity addPolicies(@RequestBody Policies policies) {

		adminServiceInterfaceImpl.addPolicy(policies);

		return new ResponseEntity(" New Policy added successfully", HttpStatus.OK);
	}

	// Admin can fetch all policies
	@GetMapping("/allpolicies")
	List<Policies> findAll1() {
		return adminServiceInterfaceImpl.findAll1();
	}

	// Admin can fetch all locations
	@GetMapping("/allLocations")
	Collection<Location> findAll() {
		return adminServiceInterfaceImpl.findAll();
	}

	// Authentication
	@PostMapping("/authenticate")
	public JwtResponse generate(@RequestBody JwtRequestPayload jwtRequestPayload) {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestPayload.getUsername(),
					jwtRequestPayload.getPassword()));
		}

		catch (BadCredentialsException e) {

			throw new RuntimeException(e.getMessage());

		}

		UserDetails serv = myUserDetails.loadUserByUsername(jwtRequestPayload.getUsername());

		String token = jwtUtility.generateToken(serv);

		List<? extends GrantedAuthority> roles = serv.getAuthorities().stream().collect(Collectors.toList());
		for (GrantedAuthority ga : roles) {
			return new JwtResponse(token, ga.getAuthority());
		}
		return new JwtResponse(token, token);
	}

}