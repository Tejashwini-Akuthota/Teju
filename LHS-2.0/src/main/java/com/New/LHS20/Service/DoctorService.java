package com.New.LHS20.Service;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Dto.SpecialityDto;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;

public interface DoctorService {
	
	public ResponseEntity addSpec(SpecialityDto specialityDto) ;
	public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto);
	 public MonitoringData fetchById(Patient patient) ;
	 public ResponseEntity addSlot(@RequestBody SlotTime slotTime, Authentication authentication) throws ParseException;
	 public ResponseEntity bookAppointmnet(Authentication authentication, @RequestBody SlotTime bookSlot);
	 public ResponseEntity confirmSlot(Authentication authentication, @RequestBody SlotTime bookSlot) ;
	 public ResponseEntity cancelslot(Authentication authentication, @RequestBody SlotTime bookSlot);
	 public ResponseEntity getAppointbydate(Authentication authentication);
	 public ResponseEntity getUpcommingAppointments(Authentication authentication);
	 public ResponseEntity addmedicines(Authentication authentication,@RequestBody Doctor_Prescription doctorprescription) ;
	 List<SlotTime> getAvailableSlots();
	 

}
