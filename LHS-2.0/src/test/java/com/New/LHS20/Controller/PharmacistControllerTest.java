package com.New.LHS20.Controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



import java.util.ArrayList;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Service.PharmacistServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class PharmacistControllerTest {
    @Mock
    PharmacistServiceImpl pharmacistServiceImpl;
@Test
public void addingAmountForSuppliments() throws JsonProcessingException {
    Patient patient=new Patient();
    patient.setUserId(1);
     Suppliments suppliments=new Suppliments();
     suppliments.setId(1);
     suppliments.setName("cotton");
     suppliments.setPatient(patient);
     suppliments.setQuantity("2");
     suppliments.setAmount(100);
     
     List<Suppliments> supplList=new ArrayList<>();
     supplList.add(suppliments);
     
     String inputInJson = this.mapToJson(supplList);
        String URI = "/api/addingAmount";
     ResponseEntity responseEntity =  new ResponseEntity<>(supplList, HttpStatus.OK);
        Mockito.when(pharmacistServiceImpl.addingAmountForSuppliments(suppliments)).thenReturn(responseEntity);
        
        ResponseEntity  abc  = pharmacistServiceImpl.addingAmountForSuppliments(suppliments);
     
     assertThat(abc.getStatusCodeValue()).isEqualTo(200);
}
@Test
public void addingMedicinesAmount() throws JsonProcessingException {
    Doctor_Prescription docprisc=new Doctor_Prescription();
    docprisc.setId(1);
    docprisc.setMedicineName("Dolo");
    docprisc.setInvestigations("Xray");
    docprisc.setAmount(40);
    docprisc.setQuantity(5);
    List<Doctor_Prescription>supplList=new ArrayList<>();
      supplList.add(docprisc);
  
    String inputInJson = this.mapToJson(supplList);
        String URI = "/api/addingMedicinesAmount";
  ResponseEntity responseEntity =  new ResponseEntity<>(supplList, HttpStatus.OK);
        Mockito.when(pharmacistServiceImpl.addingAmountForSuppliments(docprisc)).thenReturn(responseEntity);
        
        ResponseEntity  abc  = pharmacistServiceImpl.addingAmountForSuppliments(docprisc);
  
  assertThat(abc.getStatusCodeValue()).isEqualTo(200);
    
}
@Test
public void get() {
    Patient patient=new Patient();
    patient.setUserId(2);
    Suppliments suppliments=new Suppliments();
    suppliments.setId(1);
    suppliments.setName("Cotton");
    suppliments.setQuantity("3");
    suppliments.setAmount(40);
    suppliments.setPatient(patient);
    List<Suppliments>supplList=new ArrayList<>();
    supplList.add(suppliments);




        Mockito.when(pharmacistServiceImpl.fetchById(patient)).thenReturn(supplList);
        
        List<Suppliments>  abc  = pharmacistServiceImpl.fetchById(patient);
        assertEquals(40, abc.get(0).getAmount());



}
@Test
public void get1() {
    Patient patient=new Patient();
    patient.setUserId(2);
    Doctor doctor=new Doctor();
    doctor.setId(1);
    Doctor_Prescription docprisc=new Doctor_Prescription();
    docprisc.setId(1);
    docprisc.setMedicineName("Dolo");
    docprisc.setQuantity(4);
    docprisc.setDosage("1mg");
    docprisc.setDuration("4 days");
    docprisc.setAmount(50);
    docprisc.setDoctor(doctor);
    docprisc.setPatient(patient);
    List<Doctor_Prescription>supplList=new ArrayList<>();
    supplList.add(docprisc);




        Mockito.when(pharmacistServiceImpl.fetchById1(patient)).thenReturn(supplList);
        
        List<Doctor_Prescription>  abc  = pharmacistServiceImpl.fetchById1(patient);
        assertEquals(50, abc.get(0).getAmount());



   
}
private String mapToJson(Object object) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(object);
}



}