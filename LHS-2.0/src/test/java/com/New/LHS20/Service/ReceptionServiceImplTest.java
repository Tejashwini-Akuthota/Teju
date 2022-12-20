 package com.New.LHS20.Service;



	import static org.junit.Assert.assertEquals;
	import static org.junit.jupiter.api.Assertions.*;



	import java.util.List;



	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.junit.runner.RunWith;
	import org.mockito.Mock;
	import org.mockito.MockitoAnnotations;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.test.context.junit4.SpringRunner;



	import com.New.LHS20.Dao.BillRepositry;
	import com.New.LHS20.Dao.DoctorRepository;
	 
	import com.New.LHS20.Dao.PatientRepository;
	import com.New.LHS20.Dao.PrescriptionRepository;
	import com.New.LHS20.Dao.ReceptionRepository;
	import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Dao.MonitoringDataRepository;
import com.New.LHS20.Entity.AdmissionForm;
	import com.New.LHS20.Entity.Bill;
	import com.New.LHS20.Entity.Doctor;
	import com.New.LHS20.Entity.Doctor_Prescription;
	import com.New.LHS20.Entity.MonitoringData;
	import com.New.LHS20.Entity.Patient;
	import com.New.LHS20.Entity.SlotTime;
	import com.New.LHS20.Entity.Suppliments;
	
	
	@RunWith(SpringRunner.class)
	@SpringBootTest
	class ReceptionServiceImplTest {
	        @Mock
	        ReceptionRepository receprepo;
	        @Mock
	        PatientRepository patientrepo;
	        @Mock
	        SupplimentsRepository supplimentsrepo;
	        @Mock
	        DoctorRepository doctorrepo;
	        @Mock
	        PrescriptionRepository prescriptionrepo;
	        @Mock
	        SlotRepo slotrepo;
	        @Mock
	        BillRepositry billRepositry;
	        @Mock
	       MonitoringDataRepository monitoringdatarepository;
	        @BeforeEach
	        void setUp() throws Exception
	        {
	            
	            MockitoAnnotations.initMocks(this);
	        }




	    // receptionist updating the admitted patients
	      @Test
	    public void addAdmittedPatients() {
	        AdmissionForm admissionForm=new AdmissionForm();
	         admissionForm.setId(1);
	         admissionForm.setRegdNo(2);
	         admissionForm.setAdmissionDate("10/08/2022");
	         admissionForm.setDoctor("usharanivuha@gmail.com");
	         admissionForm.setDisease("Malaria");
	         receprepo.save(admissionForm);
	         
	        if (receprepo.existsByRegdNo(admissionForm.getRegdNo())) {



	           AdmissionForm details = receprepo.findByRegdNoAndDate(admissionForm.getRegdNo(),
	                    admissionForm.getAdmissionDate());
	                    details.setBedNo(15);
	                    details.setRoomNo(101);
	                    details.setWard("GENERAL");
	     

	            receprepo.save(details);

	             
	                   
	             Patient patient= new Patient();
	             patient.setUserId(2);
	             patientrepo.save(patient);
	             
	             Doctor doctor =new Doctor();
	             doctor.setId(1);
	             doctor.setEmail("usharanivuha@gmail.com");
	             doctorrepo.save(doctor);
	             
	             
	             Suppliments suppliments1 = new Suppliments();
	             Patient patient1 = patientrepo.findByUserId(details.getRegdNo());
	                 suppliments1.setPatient(patient1);
	             
	             
	             suppliments1.setQuantity("5");
	             supplimentsrepo.save(suppliments1);
	             
	             
	     

	               if(suppliments1.getQuantity()==null) {
	                 suppliments1.setQuantity("0");
	             }
	         
	             supplimentsrepo.save(suppliments1);
	            
	             
	             
	             Doctor_Prescription presc = new Doctor_Prescription();
	            Patient patient2 = patientrepo.findByUserId(patient.getUserId());            presc.setPatient(patient2);
	         
	         Doctor doctor1 = doctorrepo.findByEmail(details.getDoctor());
	             presc.setDoctor(doctor1);
	         prescriptionrepo.save(presc);

	             MonitoringData data = new MonitoringData();
	                  data.setPatient(patient);
	               
	                  Bill bill = new Bill();
	                
	                  
	             SlotTime slottime2 = new SlotTime();
	             
	             slottime2.setPatientId((long)2);
	             
	             List<SlotTime> slot = slotrepo.findByPatientId(admissionForm.getRegdNo());
	                  SlotTime slottime1 = slot.get(0);
	                  slottime1.setStartTime("10:30");
	                 slottime1.setDate("10/08/2022");
	                     
	            bill.setAppointmenttime(slottime1.getStartTime());
	            bill.setAppointmentdate(slottime1.getDate());
	            bill.setPatient(patient);
	         
	            bill.setDoctor(doctor);
	            
	            
	            billRepositry.save(bill);



	            monitoringdatarepository.save(data);
	             receprepo.save(admissionForm);
	                assertEquals(2,admissionForm.getRegdNo());
	                assertEquals(101,admissionForm.getRoomNo());
	                assertEquals(15, admissionForm.getBedNo());
	                assertEquals("General", admissionForm.getWard());
	                assertEquals("usharanivuha@gmail.com", admissionForm.getDoctor());
	                assertEquals("Malaria", admissionForm.getDisease());
	        



	   }



	}



	}
