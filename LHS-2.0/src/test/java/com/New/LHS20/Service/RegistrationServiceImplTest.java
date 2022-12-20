package com.New.LHS20.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
class RegistrationServiceImplTest {


	 


	 
 
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
}
