package com.New.LHS20.Service;
import static org.junit.Assert.assertEquals;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import com.New.LHS20.Dao.BillRepositry;
import com.New.LHS20.Dao.PrescriptionRepository;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.Suppliments;
 
	@RunWith(SpringRunner.class)
	@SpringBootTest
	class BillServiceImplTest {
 
		 @Mock
		 PrescriptionRepository prescriptionRepository;

		 @Mock
		    SupplimentsRepository supplimentsRepository;
		    
		    @Mock
		    BillRepositry billRepositry;
	
		    @BeforeEach
		    void setUp() throws Exception
		    {
		        
		        MockitoAnnotations.initMocks(this);
		    }
		    
		       @Test
		        public void addBill() {
		           
		           Doctor doctor = new Doctor();
		                doctor.setId(2);
 
		                Patient patient = new Patient();
		                  patient.setUserId(1);
		               
		           Bill bill1=new Bill();
		           
		            LocalDate date = LocalDate.now();
		            LocalTime localTime=LocalTime.now();
		            
		            LocalDate dischargedate = LocalDate.now();
		            LocalTime dischargetime=LocalTime.now();
		            
		            
		            bill1.setId(1);
		            bill1.setAppointmentdate("10/08/2022");
		            bill1.setAppointmenttime("10:30");
		            bill1.setBill_date(date);
		            bill1.setBill_time(localTime);
		            bill1.setDischargedate(dischargedate);
		            bill1.setDischargetime(dischargetime);
		            
		            bill1.setDoctor(doctor);
		            bill1.setPatient(patient);
		            bill1.setMedicalEquipments(1502);
		            billRepositry.save(bill1);
		            
		            long Totalamt = 0l;
		            long Totalamt2 = 0l;
		            
		            Doctor_Prescription docPrisc=new Doctor_Prescription();
		            docPrisc.setPatient(patient);
		            docPrisc.setAmount(12585);
		            docPrisc.setQuantity(5);
		            
		            List<Doctor_Prescription> bill2 = prescriptionRepository.findByPatient(bill1.getPatient());
		            Mockito.when(prescriptionRepository.findByPatient(bill1.getPatient())).thenReturn(bill2);
		     
		            
		            
		             
		            
		            
		            Totalamt = (docPrisc.getAmount() * docPrisc.getQuantity());
	                Totalamt2 = Totalamt+Totalamt2;

	              bill1.setPharmacy(Totalamt2);
	              billRepositry.save(bill1);
	            assertEquals("10:30", bill1.getAppointmenttime());
	            assertEquals("10/08/2022", bill1.getAppointmentdate());
	            assertEquals(patient, bill1.getPatient());
	            assertEquals(doctor, bill1.getDoctor());
	            assertEquals(62925, bill1.getPharmacy());
		       }
		       
		       
		       @Test
		        public void addBillMedical() {
		            Double Totalamt = 0d;
		            Double Totalamt2 = 0d;
		            
		             Patient patient = new Patient();
		              patient.setUserId(1);
		              
		              Doctor doctor = new Doctor();
		                doctor.setId(2);
		                
		             Bill bill5= new Bill();
		               bill5.setPatient(patient) ;
		               bill5.setDoctor(doctor);
		               bill5.setPharmacy(20000);
		               bill5.setConsultations(30000);
		               bill5.setConsumables(40000);
		               bill5.setInvestigations(1000);
		               bill5.setRoomrent(1000);
		               billRepositry.save(bill5);
		               Suppliments suppliments=new Suppliments();
		               suppliments.setAmount(1000);
		               suppliments.setQuantity("5");
		               suppliments.setPatient(patient);
		               
		               
		               
		               List<Suppliments> bill2 = supplimentsRepository.findByPatient(bill5.getPatient());
		               Totalamt = (double) (suppliments.getAmount() * Long.parseLong(suppliments.getQuantity()));
		                
		                Totalamt2 = Totalamt + Totalamt2;
		                
		                Long Totalamt4 = bill5.getPharmacy()+bill5.getConsultations()+bill5.getConsumables()+bill5.getInvestigations()+bill5.getRoomrent()+Totalamt2.longValue();
		                LocalDate date = LocalDate.now();
		                LocalTime localTime=LocalTime.now();
		                
		                LocalDate dischargedate = LocalDate.now();
		                LocalTime dischargetime=LocalTime.now();
		                
		                billRepositry.setBill(Totalamt4, Totalamt2.longValue(),date,localTime,dischargedate,dischargetime, bill5.getDoctor(),bill5.getPatient());
		                assertEquals(20000, bill5.getPharmacy());



		       

		       }
}
