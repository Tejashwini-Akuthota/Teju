package com.New.LHS20.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Service.DoctorServiceImpl;
import com.New.LHS20.Service.PatientServiceImpl;
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class PatientControllerTest {



   @Mock
    PatientServiceImpl patientServiceImpl;
       
   



        @Test
           public void findAll()
           {
               
            Doctor doctor=new Doctor();
             doctor.setId(1);
             doctor.setFirstName("padhu");
             doctor.setEmail("padma@gmail.com");
             doctor.setLastName("vathi");
             doctor.setSpeciality("Dentist");
             
          
             Doctor doctor1=new Doctor();
             doctor1.setId(1);
             doctor1.setFirstName("USHA");
             doctor1.setEmail("USHA@gmail.com");
             doctor1.setLastName("RANI");
             doctor1.setSpeciality("CARDIOLOGIST");
             
               
             List<Doctor> doctorslist=new ArrayList<>();
             doctorslist.add(doctor);
             doctorslist.add(doctor1);
                         
           
            
             when(patientServiceImpl.findAll()).thenReturn(doctorslist);
             Collection<Doctor>   abc = patientServiceImpl.findAll();
             System.out.println(abc);
             assertEquals( abc,doctorslist);
              
 
           }
        
        
//     // update  
//        @Test
// public void updateMyProfile() {
// 	
// 	RegistrationForm regform =new RegistrationForm();
// 	
// 	regform.setFirstName("usha");
// 	regform.setLastName("rani");
// 	regform.setEmail("usharanivuha@gmail.com");
// 	regform.setPhoneNo("+918186918990");
// 	regform.setDob("05/07/1997");
// 	regform.setGender("Female");
// 	regform.setUsername("usharanivuha@gmail.com");
// 	regform.setRoleName("USER");
//     regform.setUserId(1);
//     regform.setPassword("usha@1234");
//     
//     RegistrationForm output =new RegistrationForm();
//                
//     output.setFirstName("usha123");
//     output.setLastName("rani");
//     output.setEmail("usharanivuha1997@gmail.com");
//     output.setPhoneNo("+918186918990");
//     output.setDob("05/07/1997");
//     output.setGender("Female");
//     output.setUsername("usharanivuha@gmail.com");
// 	           
//                
//     regform.setFirstName(output.getFirstName());
//     regform.setEmail(output.getEmail());
//                   
//     
//    when(patientServiceImpl.updateMyProfile(output)).thenReturn(regform);
//
//
//     RegistrationForm abc1=patientServiceImpl.updateMyProfile(output);
//     
//    
//
//
//      assertEquals(abc1.getFirstName(), output.getFirstName());
//      
//     }
//        
//        
//        
        
        
        
        
}