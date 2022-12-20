package com.New.LHS20.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.New.LHS20.Dao.BillRepositry;
import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Dao.PatientRepository;
import com.New.LHS20.Dao.PrescriptionRepository;
import com.New.LHS20.Dao.ReceptionRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Dao.MonitoringDataRepository;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Exception.ResourceAlreadyExistsException;

@Service
public class ReceptionServiceImpl   implements ReceptionService {

	@Autowired
	SlotRepo slotrepo;
	@Autowired
	ReceptionRepository receprepo;

	@Autowired
	RegistrationRepository regrepo;

	@Autowired
	Doctor_Prescription doctorprescription;

	@Autowired
	PatientRepository patientrepo;

	@Autowired
	SupplimentsRepository supplimentsRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	PrescriptionRepository prescriptionRepository;

	@Autowired
	MonitoringDataRepository MonitoringDataRepository;

	@Autowired
	BillRepositry billrepository;
	
	@Autowired
	 RegistrationRepository   registrationRepository;

 

	// receptionist updating the admitted patients
	public AdmissionForm addAdmittedPatients(AdmissionForm admissionform) {
	System.out.println("receptionservice called");
		if (receprepo.existsByRegdNo(admissionform.getRegdNo())) {

			AdmissionForm details = receprepo.findByRegdNoAndDate(admissionform.getRegdNo(),
					admissionform.getAdmissionDate());
			System.err.println(details);
			admissionform.setId(details.getId());
			admissionform.setDoctor(details.getDoctor());
			admissionform.setAdmissionDate(details.getAdmissionDate());
			admissionform.setBedNo(admissionform.getBedNo());
			admissionform.setDisease(details.getDisease());
			receprepo.save(admissionform);
 
			Suppliments suppliments = new Suppliments();
 

			Patient patient = patientrepo.findByUserId(admissionform.getRegdNo());
			suppliments.setPatient(patient);

			if(suppliments.getQuantity()==null) {
				suppliments.setQuantity("0");
			}
			
			supplimentsRepository.save(suppliments);
			Doctor_Prescription presc = new Doctor_Prescription();
			Patient patient2 = patientrepo.findByUserId(patient.getUserId());
			presc.setPatient(patient2);
			 
			Doctor doctor = doctorRepository.findByEmail(details.getDoctor());
			
			presc.setDoctor(doctor);
			prescriptionRepository.save(presc);

			MonitoringData data = new MonitoringData();

			
			data.setPatient(patient);
			Bill bill = new Bill();

			SlotTime slottime2 = new SlotTime();
			List<SlotTime> slot = slotrepo.findByPatientId(admissionform.getRegdNo());
			SlotTime slottime1 = slot.get(0);
			bill.setAppointmenttime(slottime1.getStartTime());
			bill.setAppointmentdate(slottime1.getDate());
			bill.setPatient(patient);
			 
			bill.setDoctor(doctor);
			
			
			billrepository.save(bill);

			MonitoringDataRepository.save(data);
			return receprepo.save(admissionform);
		} else {
			throw new RuntimeException("User not found  for the id");
		}
	}

	
	
	//Receptionist can view his/her profile
	 
			public RegistrationForm viewprofile(long userId) {
				System.err.println(userId);

				Optional<RegistrationForm> registrationForm = registrationRepository.findById(userId);

				if (registrationForm.isPresent()) {

					return registrationForm.get();

				} else {

					throw new RuntimeException("Please provide Correct Id" + userId);
				}

			}

	    

    // Receptionist can update his profile(MyProfile)
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
   			return new ResponseEntity("Your Profile Updated Successfully",HttpStatus.OK);

 		} else {
 			return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
 		}
 	}


   		}

