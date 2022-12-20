package com.New.LHS20.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Dao.NurseRepository;
import com.New.LHS20.Dao.ReceptionRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Dao.MonitoringDataRepository;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Nurse;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Exception.ResourceAlreadyExistsException;

@Service
public class NurseServiceImpl  implements NurseService{
    @Autowired
    private NurseRepository nurserepository;
    @Autowired
    private RegistrationRepository registrationrepository;

    @Autowired
    private ReceptionRepository receptionrepository;
    @Autowired
    private SlotRepo slotrepo;

    @Autowired
    SupplimentsRepository supplimentsRepository;

    @Autowired
    private MonitoringDataRepository monitoringdatarepository;


//    public List<AdmissionForm> getByDoctor(String doctor) {
//     return nurserepository.findAll();
//    }
//
//    
    
 

     public ResponseEntity getAllAdmittedPatients() 
     {
    	 
    	 
     	DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String today = LocalDate.now().format(format1);
    	 
    	List<AdmissionForm> list = receptionrepository.findByAdmissionDate(today);
        return new ResponseEntity<>(list, HttpStatus.OK);
    	 
     	
    }
   //nurse can view his/her profile
	 
		public RegistrationForm viewprofile(long userId) {
			System.err.println(userId);

			Optional<RegistrationForm> registrationForm = registrationrepository.findById(userId);

			if (registrationForm.isPresent()) {

				return registrationForm.get();

			} else {

				throw new RuntimeException("Please provide Correct Id" + userId);
			}

		}

    

  // Nurse can update his profile(MyProfile)
 	public ResponseEntity updateMyProfile(RegistrationFormUpdateDto registrationFormUpdateDto) {

 		System.out.println(registrationFormUpdateDto.getUserId());

 		List<RegistrationForm> registrationForm = registrationrepository
 				.findByUserId(registrationFormUpdateDto.getUserId());

 		RegistrationForm registrationForm1 = registrationForm.get(0);

 		if (registrationFormUpdateDto == null) {
 			throw new RuntimeException("Please Enter All The Fields");

 		} else if (registrationForm1.getPhoneNo().equals(registrationFormUpdateDto.getPhoneNo())) {

 		} else if (registrationrepository.existsByPhoneNo(registrationFormUpdateDto.getPhoneNo())) {

 			throw new ResourceAlreadyExistsException(
 					"Phone number already exists please register with different phone number");
 		}

 		if (registrationrepository.existsById(registrationFormUpdateDto.getUserId())) {

 			System.err.println(registrationForm1);

 			registrationForm1.setFirstName(registrationFormUpdateDto.getFirstName());
 			registrationForm1.setLastName(registrationFormUpdateDto.getLastName());
 			registrationForm1.setDob(registrationFormUpdateDto.getDob());
 			registrationForm1.setEmail(registrationFormUpdateDto.getEmail());
 			registrationForm1.setGender(registrationFormUpdateDto.getGender());
 			registrationForm1.setPhoneNo(registrationFormUpdateDto.getPhoneNo());
 			registrationForm1.setUsername(registrationFormUpdateDto.getEmail());

 			registrationrepository.save(registrationForm1);

 			  
 		      Nurse nurse = nurserepository.findByUserId(registrationForm1.getUserId());

   System.err.println(nurse);

 			  ModelMapper mapper1 = new ModelMapper();
 			  mapper1.map(registrationForm1, nurse);
 			  



 			  nurserepository.save(nurse);
 			   
 			  return new ResponseEntity("Your Profile Updated Successfully",HttpStatus.OK);

 		} else {
 			return  new ResponseEntity("Please Enter Valid Username",HttpStatus.BAD_REQUEST);
 		}
 	}

    

    // Nurse was able to see all the appointments upto one week
      public ResponseEntity getupcommingappointments() {
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String today = LocalDate.now().format(format1);
        String tillDate = LocalDate.now().plusDays(7).format(format1);
        List<SlotTime> list = slotrepo.findBySevenDaysSlots(today, tillDate);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

      // Nurse can able to see today appointments
 public ResponseEntity getcurrentappointments() {
          DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          String today = LocalDate.now().format(format1);

          List<SlotTime> list = slotrepo.findByDate(today);
          return new ResponseEntity<>(list, HttpStatus.OK);
      }


//Nurse can add suppliments

    public ResponseEntity addsuppliments(@RequestBody Suppliments suppliments) {

        //String userName = "padamavathilancesoft@gmail.com";
        List<Suppliments> suppliments2 = supplimentsRepository.findByPatient(suppliments.getPatient());
        Iterator iterator = suppliments2.iterator();
        int i = 0;
        for (Suppliments suppliments3 : suppliments2) {
            if (suppliments3.getQuantity() == null) {
                suppliments.setQuantity("0");
                suppliments.setId(suppliments3.getId());
                supplimentsRepository.save(suppliments3);
                i++;
            }
        }
        if (i == 0) {
        	suppliments.setId(suppliments.getId());
            supplimentsRepository.save(suppliments);
        }
        return null;

    }

    //Nurse can add Monitoring Data

    public ResponseEntity addMonitoringData(@RequestBody MonitoringData monitoringData) {
        List<MonitoringData> datas = monitoringdatarepository.findByPatient(monitoringData.getPatient());
        MonitoringData data = datas.get(0);

        monitoringData.setPatient(data.getPatient());
        monitoringData.setId(data.getId());
       
        return new ResponseEntity<>(monitoringdatarepository.save(monitoringData),  HttpStatus.ACCEPTED);
    }


 

     
}