package com.New.LHS20.Controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.New.LHS20.Dao.DoctorPrescriptionRepository;
import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Dao.PrescriptionRepository;
import com.New.LHS20.Dao.ReceptionRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dao.SpecialityRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Dto.SpecialityDto;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;
import com.New.LHS20.Entity.Speciality;
import com.New.LHS20.Security.JWTUtility;
import com.New.LHS20.Service.DoctorServiceImpl;

@RestController
@RequestMapping("/doctors")
@CrossOrigin("*")
public class DoctorController {
	@Autowired
	private DoctorServiceImpl doctorServiceImpl;

 

	@Autowired
	private SlotRepo slotRepo;

	@Autowired
	private DoctorPrescriptionRepository doctorPrescriptionRepository;

	@Autowired
	JWTUtility jwtUtility;
//    

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private SpecialityRepository specialityRepository;

	@Autowired
	private AdmissionForm admissionform;

	@Autowired
	private ReceptionRepository receptionRepository;

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	//admin  can add speciality using doctor  email//Admin dashboard
	@PostMapping("/addspeciality")
	public ResponseEntity addSpeciality(@RequestBody SpecialityDto specialityDto) {
		
		return doctorServiceImpl.addSpec(specialityDto);
	}

	// Doctor can schedule an appointment//Doctor Dashboard
	@PostMapping("/addSlots")
	public ResponseEntity addSlot(@RequestBody SlotTime slotTime, Authentication authentication) throws ParseException {
		return doctorServiceImpl.addSlot(slotTime, authentication);
	}

	 
	// admin can get all the scheduled and booked slots//Reception Dashboard//Patient Dashboard//Not Working
	@GetMapping("/getAvailSlots")
	public List<SlotTime> getAvailableSLots(){
		return doctorServiceImpl.getAvailableSlots();
	}

	// doctor can delete the slot//Not Working
	@DeleteMapping("/deleteSlots")
	public String updateAppointment(@RequestBody SlotTime slotTime) {
		System.err.println(slotTime.getDate());
		System.err.println(slotTime.getStartTime());
		System.err.println(slotTime.getId());

		List<SlotTime> slotdata = slotRepo.findByStartTimeAndDateAndId(slotTime.getDate(), slotTime.getStartTime(),
				slotTime.getDoctorId());
		System.err.println(slotdata);
		slotRepo.deleteAll(slotdata);
		return "deleted";
	}

	// patient can book the slot     //Patient dashboard
	@PostMapping("/bookslot")
	public ResponseEntity bookAppointmnet(Authentication authentication, @RequestBody SlotTime bookslot) {
		return doctorServiceImpl.bookAppointmnet(authentication, bookslot);
	}

	// Doctor can fetch List of appointments for doctor using doctor id //Doctor Dashboard
	@GetMapping("/getpatientbydid")
	public List<SlotTime> getPatientByDid(Authentication authentication) {
		String username = authentication.getName();
		RegistrationForm registrationForm = registrationRepository.findByUsername(username);
		List<SlotTime> slotTime = slotRepo.findByDoctorId(registrationForm.getUserId());
		return slotTime;
	}

	// Both patients and Admin can fetch all the doctors with speciality  //patient dashboard, admin dashboard
	//op:Speciality name& doctor id
	@GetMapping("/getspeciality")
	public List<Speciality> getspecaility() {
		return specialityRepository.findAll();
	}

	// Patient can able to fetch the doctors details with speciality//Patient Dashboard
	//request: speciality name & doctor Id
	//respose: Doctor Details
	@GetMapping("/getdocbyspeciality")
	public List<Doctor> getdocbyspeciality(@RequestBody Speciality speciality) {
		List<Doctor> speciality2 = doctorRepository.findBySpeciality(speciality.getName());
		return speciality2;
	}

	// doctor can confirm the slot //Doctor Dashboard
	@PostMapping("/confirmslot")
	public ResponseEntity confirmSlot(Authentication authentication, @RequestBody SlotTime bookSlot) {
		return doctorServiceImpl.confirmSlot(authentication, bookSlot);
	}

	// Doctor can cancel the slot
	@PostMapping("/cancelslot")
	public ResponseEntity cancelslot(Authentication authentication, @RequestBody SlotTime bookSlot) {
		return doctorServiceImpl.cancelslot(authentication, bookSlot);
	}

	// Doctor will login and he can see appointments by date 
	
	@GetMapping("/getappointbydate")
	public ResponseEntity getAppointbydate(Authentication authentication) {

		return doctorServiceImpl.getAppointbydate(authentication);
	}

	// Doctor can fetch upcomming appointments upto one week
	@GetMapping("/getupcommingappointments")
	public ResponseEntity getUpcommingAppointments(Authentication authentication) {

		return doctorServiceImpl.getUpcommingAppointments(authentication);
	}

	// Doctor can Update his profile
   @PutMapping("/myprofileupdate")
	public ResponseEntity updateMyProfile(@RequestBody @Valid RegistrationFormUpdateDto regformuFormUpdateDto) {
			 
				
				     return  doctorServiceImpl.updateMyProfile(regformuFormUpdateDto);

				 
			}

   
   //Doctor can view his profile
 		@GetMapping("/myprofile/{userId}")
 		public ResponseEntity viewmyprofile(@PathVariable long userId) {
 			RegistrationForm regform = doctorServiceImpl.viewprofile(userId);
 			System.out.println(regform);

 			return new ResponseEntity(regform, HttpStatus.OK);

 			
 		}

	// Doctor can add medicines
	@PostMapping("/addMedicines")
	public ResponseEntity addmedicines(Authentication authentication,
			@RequestBody Doctor_Prescription doctorprescription) {

		return doctorServiceImpl.addmedicines(authentication, doctorprescription);
	}

	// doctor and Patient can fetch pre-monitoring data by using patient id
	@GetMapping("/reg/{patient}")
	public ResponseEntity get(@PathVariable Patient patient) {
		MonitoringData monitoringData = doctorServiceImpl.fetchById(patient);
		return new ResponseEntity(monitoringData, HttpStatus.OK);
	}

}