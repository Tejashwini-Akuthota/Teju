package com.New.LHS20.Service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Dao.PatientRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
@RunWith(SpringRunner.class)
@SpringBootTest
class PatientServiceImplTest {
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    RegistrationRepository registrationRepository;
    @Mock
    PatientRepository patientRepository;
    void setUp() throws Exception 
    {

        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findAll() {
        List<Doctor> doctor=  new ArrayList();
        Doctor doc= new Doctor();
        doc.setId(1);
        doc.setFirstName("usha");
        doc.setLastName("rani");
        doc.setSpeciality("Cardiologist");
        doctor.add(doc);
        when(doctorRepository.findAll()).thenReturn(doctor);
        assertEquals(doctor,doctorRepository.findAll());
    }


//  Patient can update his profile
          @Test
          public void updateMyProfile() {
              List<RegistrationForm> regform1=  new ArrayList();
              RegistrationForm regform2=new RegistrationForm();
              regform2.setUserId(1);
              regform2.setFirstName("usha");
              regform2.setLastName("rani");
              regform2.setUsername("usha@gmail.com");
              regform2.setGender("female");
              regform2.setDob("10/02/2000");
              regform2.setPassword("123");
              regform2.setPhoneNo("7793972515");

              regform1.add(regform2);
              registrationRepository.saveAll(regform1);

              RegistrationForm regform3=new RegistrationForm();
              regform3.setUserId(1);
              regform3.setFirstName("ushauha");
              regform3.setLastName("rani");
              regform2.setUsername("usha@gmail.com");
              regform3.setGender("female");
              regform3.setDob("10/02/2000");
              regform3.setPassword("123");
              regform3.setPhoneNo("7793972515");

                    if (registrationRepository.existsByUsername(regform3.getUsername())) {
              Patient patient = patientRepository.findByUserId(regform3.getUserId());

              registrationRepository.save(regform3);
                       ModelMapper modelmapper1 = new ModelMapper();
                     modelmapper1.map(regform3, patient);
                     patientRepository.save(patient);
                         registrationRepository.save(regform3);
                assertEquals("ushauha",patient.getFirstName());

                    }

    }



}