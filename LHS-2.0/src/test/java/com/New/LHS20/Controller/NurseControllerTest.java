package com.New.LHS20.Controller;





import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;





import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;





import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Service.NurseServiceImpl;




@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class NurseControllerTest {
    
    @MockBean
    NurseServiceImpl nurseServiceImpl;
    
    
    
      //pull
       @Test
       public void getAllAdmittedPatients()
       {


         AdmissionForm admissionForm=new AdmissionForm();
         admissionForm.setId(8);
         admissionForm.setAdmissionDate("17/08/2022");
         admissionForm.setBedNo(1);
         admissionForm.setDisease("fever");
         admissionForm.setRegdNo(2);
         admissionForm.setRoomNo(5);
         admissionForm.setWard("ICU");
           
         List<AdmissionForm> slotList1=new ArrayList();
           slotList1.add(admissionForm);
       
           
           
           ResponseEntity responseEntity =  new ResponseEntity<>(slotList1, HttpStatus.OK);
        
           when(nurseServiceImpl.getAllAdmittedPatients()).thenReturn(responseEntity);

           ResponseEntity abc1=nurseServiceImpl.getAllAdmittedPatients();
          



           assertThat(abc1.getStatusCodeValue()).isEqualTo(200);


            



     }
       
       
       //getupcommingappointments
       @Test
       public void getupcommingappointments()
       {
    	   long docid=3;
    	   long patid= 11;
           SlotTime slottime = new SlotTime();
           slottime.setId(4);
           slottime.setDate("20/08/2022");
           slottime.setDisease("Malaria");
           slottime.setDoctorId(docid);
           slottime.setPatientId(patid);
           slottime.setStartTime("8:30");
           slottime.setEndTime("9:30");
           slottime.setStatus("Confirmed");
           
           
           long docid1=5;
    	   long patid1= 12;
           SlotTime slottime1 = new SlotTime();
           slottime1.setId(4);
           slottime1.setDate("21/08/2022");
           slottime1.setDisease("Fever");
           slottime1.setDoctorId(docid1);
           slottime1.setPatientId(patid1);
           slottime1.setStartTime("8:30");
           slottime1.setEndTime("9:30");
           slottime1.setStatus("Confirmed");
           
           
           List<SlotTime> slots=new ArrayList();
           slots.add(slottime);
           slots.add(slottime1);
           
           
           ResponseEntity responseEntity =  new ResponseEntity<>(slots, HttpStatus.OK);
           
        
           when(nurseServiceImpl.getupcommingappointments()).thenReturn(responseEntity);


           ResponseEntity abc1=nurseServiceImpl.getupcommingappointments();
          



           assertThat(abc1.getStatusCodeValue()).isEqualTo(200);
    	   
           
       }
       
       
       //gettodayappointments
       
       @Test
       public void getcurrentappointments()
       {
    	   long docid=6;
    	   long patid= 13;
           SlotTime slottime = new SlotTime();
           slottime.setId(1);
           slottime.setDate("18/08/2022");
           slottime.setDisease("Typhoid");
           slottime.setDoctorId(docid);
           slottime.setPatientId(patid);
           slottime.setStartTime("10:30");
           slottime.setEndTime("11:30");
           slottime.setStatus("Confirmed");
           
           
           long docid1=7;
    	   long patid1= 14;
           SlotTime slottime1 = new SlotTime();
           slottime1.setId(2);
           slottime1.setDate("18/08/2022");
           slottime1.setDisease("Fever");
           slottime1.setDoctorId(docid1);
           slottime1.setPatientId(patid1);
           slottime1.setStartTime("12:30");
           slottime1.setEndTime("13:30");
           slottime1.setStatus("Confirmed");
           
           
           List<SlotTime> slots=new ArrayList();
           slots.add(slottime);
           slots.add(slottime1);
           
           
           ResponseEntity responseEntity =  new ResponseEntity<>(slots, HttpStatus.OK);
          
          
           when(nurseServiceImpl.getcurrentappointments()).thenReturn(responseEntity);


           ResponseEntity abc1=nurseServiceImpl.getcurrentappointments();
            



           assertThat(abc1.getStatusCodeValue()).isEqualTo(200);
           
    	   
           
       }
       
       
// // update  
//       @Test
//public void updateMyProfile() {
//	
//	RegistrationForm regform =new RegistrationForm();
//	
//	regform.setFirstName("usha");
//	regform.setLastName("rani");
//	regform.setEmail("usharanivuha@gmail.com");
//	regform.setPhoneNo("+918186918990");
//	regform.setDob("05/07/1997");
//	regform.setGender("Female");
//	regform.setUsername("usharanivuha@gmail.com");
//	regform.setRoleName("NURSE");
//    regform.setUserId(1);
//    regform.setPassword("usha@1234");
//    
//    RegistrationForm output =new RegistrationForm();
//               
//    output.setFirstName("usha123");
//    output.setLastName("rani");
//    output.setEmail("usharanivuha1997@gmail.com");
//    output.setPhoneNo("+918186918990");
//    output.setDob("05/07/1997");
//    output.setGender("Female");
//    output.setUsername("usharanivuha@gmail.com");
//	           
//               
//    regform.setFirstName(output.getFirstName());
//    regform.setEmail(output.getEmail());
//                  
//    
//   when(nurseServiceImpl.updateMyProfile(output)).thenReturn(regform);
//
//
//    RegistrationForm abc1=nurseServiceImpl.updateMyProfile(output);
//    
//   
//
//
//     assertEquals(abc1.getFirstName(), output.getFirstName());
//     
//    
//	
//	
//	
//}
       //addsuppliments
       
       @Test
       public void addSuppliments()
       {
    	   
    	   
    	   
    	   
    	   Suppliments suppliments = new Suppliments();
    	   suppliments.setId(1);
    	   suppliments.setName("cotton");
    	   suppliments.setQuantity("5");
   	       suppliments.setPatient(new  Patient(2,"usha","rani","usharanivuha@gmail.com","+918186918990","07/08/1997","Female","usharanivuha@gmail.com","usha@1234","NURSE"));
    	    
   	       
   	    Suppliments suppliments1 = new Suppliments();
   	       suppliments1.setId(2);
 	       suppliments1.setName("Saline");
 	       suppliments1.setQuantity("5");
	       suppliments1.setPatient(new  Patient(2,"usha","rani","usharanivuha@gmail.com","+918186918990","07/08/1997","Female","usharanivuha@gmail.com","usha@1234","NURSE"));
 	       
   	       List<Suppliments> list=new ArrayList();
   	       list.add(suppliments);
   	       list.add(suppliments1);
   	                    
   	        
   	       ResponseEntity responseEntity =  new ResponseEntity<>(list, HttpStatus.OK);
           when(nurseServiceImpl.addsuppliments(suppliments)).thenReturn(responseEntity);
           ResponseEntity abc1=nurseServiceImpl.addsuppliments(suppliments);
           assertThat(abc1.getStatusCodeValue()).isEqualTo(200);
    	   
    	   
       }
//addsuppliments
       
       @Test
       public void addMonitoringdata()
       {
    	   
    	   
    	   Patient patient = new Patient();
    	   patient.setUserId(5);
    	   
    	   MonitoringData data = new MonitoringData();
    	   data.setId(1);
    	   data.setBloodPressure(120/80);
    	   data.setHeartRate("50");
    	   data.setPatient(patient);
    	   data.setHeight("5'6");
    	   data.setWeight(70);
    	   data.setTemperature("80");
    			   
    	    
   	       
           ResponseEntity responseEntity =  new ResponseEntity<>(data, HttpStatus.OK);
           when(nurseServiceImpl.addMonitoringData(data)).thenReturn(responseEntity);
           ResponseEntity abc1=nurseServiceImpl.addMonitoringData(data);
           
           assertThat(abc1.getStatusCodeValue()).isEqualTo(200);
    	   System.err.println(abc1.getStatusCodeValue());
    	   
       }


}