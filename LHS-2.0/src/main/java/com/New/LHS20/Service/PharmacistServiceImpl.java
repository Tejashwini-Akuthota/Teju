package com.New.LHS20.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Dao.DoctorPrescriptionRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Nurse;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Exception.ResourceAlreadyExistsException;

@Service
public class PharmacistServiceImpl  implements PharmacistService{

    @Autowired
    SupplimentsRepository supplimentsRepository;
    @Autowired
    DoctorPrescriptionRepository doctorPrescriptionRepository;
    
    @Autowired
    RegistrationRepository registrationRepository;

    public List<Suppliments> fetchById(Patient patient) {
         List<Suppliments> suppliments=supplimentsRepository.findByPatient(patient);
 
            return suppliments;


    }

    public List<Doctor_Prescription> fetchById1(Patient patient) {
         List<Doctor_Prescription> prescc=doctorPrescriptionRepository.findByPatient(patient);
 
           return prescc;
    }


    //pharmacist will add   amount for suppliments

    public ResponseEntity addingAmountForSuppliments(@RequestBody Suppliments suppliments) {
        List<Suppliments> suppliments2 = supplimentsRepository.findByPatient(suppliments.getPatient());
        Iterator iterator = suppliments2.iterator(); 
        while (iterator.hasNext()) {
            Suppliments suppliments3 = (Suppliments) iterator.next();
            if (suppliments.getName().equals(suppliments3.getName())) {
                suppliments3.setAmount(suppliments.getAmount());
                return new 
                        ResponseEntity<>(supplimentsRepository.save(suppliments3), HttpStatus.ACCEPTED);
            }

        }
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    //pharmacist will add   amount for Medicines
    public ResponseEntity addingAmountForSuppliments(@RequestBody Doctor_Prescription doctor_presc) {
        List<Doctor_Prescription> doctor_Prescriptions = doctorPrescriptionRepository
                .findByPatient(doctor_presc.getPatient());
        Iterator iterator = doctor_Prescriptions.iterator();
        while (iterator.hasNext()) {
            Doctor_Prescription doctor_Prescription3 = (Doctor_Prescription) iterator.next();
            if (doctor_presc.getMedicineName().equals(doctor_Prescription3.getMedicineName())) {
                doctor_Prescription3.setAmount(doctor_presc.getAmount());
                return new ResponseEntity<>(doctorPrescriptionRepository.save(doctor_Prescription3),
                        HttpStatus.ACCEPTED);
            }

    }
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
    
    
    //Pharmacist can view his/her profile
	 
	public RegistrationForm viewprofile(long userId) {
		System.err.println(userId);

		Optional<RegistrationForm> registrationForm = registrationRepository.findById(userId);

		if (registrationForm.isPresent()) {

			return registrationForm.get();

		} else {

			throw new RuntimeException("Please provide Correct Id" + userId);
		}

	}

    // Pharmacist can update his profile(MyProfile)
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

