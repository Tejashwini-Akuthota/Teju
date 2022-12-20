package com.New.LHS20.Service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Location;
import com.New.LHS20.Entity.Nurse;
import com.New.LHS20.Entity.Policies;
import com.New.LHS20.Entity.RegistrationForm;

public interface AdminServiceInterface {
	
	public String addAdmin(RegistrationForm regform);
	public Doctor addDoctor(RegistrationForm regform);
	public Nurse addNurse(RegistrationForm regform);
	public String addPharmacist(RegistrationForm regform);
	public String addReceptionist(RegistrationForm regform);
	public List<RegistrationForm> getRole(String rolename);
	public ResponseEntity updateDoctor(RegistrationFormUpdateDto regformFormUpdateDto);
	public ResponseEntity updateNurse(RegistrationFormUpdateDto regformuFormUpdateDto);
	public ResponseEntity updateReceptionist(RegistrationFormUpdateDto registrationFormUpdateDto);
	public ResponseEntity updatePharmacist(RegistrationFormUpdateDto registrationFormUpdateDto);
	public RegistrationForm fetchById(long userId) ;
	public String DeleteById(long userId) ;
	public String DeleteById1(long userId);
	public String DeleteById2(long userId);
	public String DeleteById3(long userId);
	public void addLocation(Location location);
	public List<Location> findAll();
	public void addPolicy(Policies policies);
	 public List<Policies> findAll1();
	 public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto);
	
	

}
