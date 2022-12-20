package com.New.LHS20.Service;

 



import static org.junit.jupiter.api.Assertions.*;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;



import com.New.LHS20.Dao.DoctorPrescriptionRepository;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.Suppliments;



@RunWith(SpringRunner.class)
@SpringBootTest
class PharmacistServiceImplTest {



   @Mock
    SupplimentsRepository supplimentsRepository;
    @Mock
    DoctorPrescriptionRepository doctorPrescriptionRepository;



   @Test
    public void fetchById() {
        Patient patient = new Patient();
        patient.setUserId(1);
        Suppliments suppliments = new Suppliments();
        suppliments.setId(1);
        suppliments.setName("Cotton");
        suppliments.setPatient(patient);
        suppliments.setQuantity("2");
        supplimentsRepository.save(suppliments);
        List<Suppliments> supplimentslist = new ArrayList();
        supplimentslist.add(suppliments);
        Mockito.when(supplimentsRepository.findByPatient(patient)).thenReturn(supplimentslist);
        List<Suppliments> suppliments1 = supplimentsRepository.findByPatient(patient);
        Suppliments suppliments2 = suppliments1.get(0);
        System.err.println(suppliments2);
        assertEquals("Cotton", suppliments2.getName());



   }



   @Test
    public void addingAmountForSuppliments() {
        Patient patient=new Patient();
        patient.setUserId(1);
        patient.setFirstName("srikanth");
        patient.setLastName("nallaveli");
        patient.setGender("male");
        patient.setEmail("srikanth@gmail.com");
        patient.setPassword("sri1233");
        patient.setPhoneNo("990858743857");
        patient.setDob("08/12/1990");
        
        Suppliments suppliments=new Suppliments();
        suppliments.setId(1);
        suppliments.setName("cotton");
        suppliments.setPatient(patient);
        suppliments.setQuantity("2");
        suppliments.setAmount(100);
        supplimentsRepository.save(suppliments);
        List<Suppliments> supplimentslist = new ArrayList();
        supplimentslist.add(suppliments);
        Mockito.when(supplimentsRepository.findByPatient(patient)).thenReturn(supplimentslist);
         List<Suppliments> supplimennts2 = supplimentsRepository.findByPatient(suppliments.getPatient());
         Suppliments suppliments3=supplimennts2.get(0);
         


            if (suppliments.getName().equals(suppliments3.getName())) {
                suppliments3.setAmount(100);
                System.err.println(suppliments3);
               assertEquals(100, suppliments3.getAmount());
        }
        
        }
     @Test
     public void addingAmountForMedicines() {
         Patient patient3=new Patient();
         patient3.setUserId(1);
         Doctor doctor=new Doctor();
         doctor.setId(2);
            Doctor_Prescription docprisc=new Doctor_Prescription();
         docprisc.setId(1);
         docprisc.setDoctor(doctor);
         docprisc.setPatient(patient3);
         docprisc.setMedicineName("Dolo");
         docprisc.setQuantity(2);
         docprisc.setDosage("1mg");
         docprisc.setDuration("3 days");
         docprisc.setInvestigations("xray");
         List<Doctor_Prescription> docpriscList1=new ArrayList();
         docpriscList1.add(docprisc);
         Mockito.when(doctorPrescriptionRepository
                 .findByPatient(docprisc.getPatient())).thenReturn(docpriscList1);
         List<Doctor_Prescription> doctor_Prescriptions = doctorPrescriptionRepository
                 .findByPatient(docprisc.getPatient());
         Doctor_Prescription doctor_Prescription3=doctor_Prescriptions.get(0);


             if (docprisc.getMedicineName().equals(doctor_Prescription3.getMedicineName())) {
                 doctor_Prescription3.setAmount(234);
                 assertEquals("Dolo",docprisc.getMedicineName());
                 assertEquals(234, doctor_Prescription3.getAmount());





           }





   }
     @Test
     public void fetchById1() {
         Patient patient=new Patient();
         patient.setUserId(1);
         Doctor doctor=new Doctor();
         doctor.setId(2);
         Doctor_Prescription docprisc=new Doctor_Prescription();
         docprisc.setId(1);
         docprisc.setDoctor(doctor);
         docprisc.setPatient(patient);
         docprisc.setMedicineName("Dolo");
         docprisc.setQuantity(2);
         docprisc.setDosage("1mg");
         docprisc.setDuration("3 days");
         docprisc.setInvestigations("xray");
         //doctorPrescriptionRepository.save(docprisc);
         List<Doctor_Prescription> DoctorPrispLIst=new ArrayList();
         DoctorPrispLIst.add(docprisc);
         Mockito.when(doctorPrescriptionRepository.findByPatient(patient)).thenReturn(DoctorPrispLIst);
          List<Doctor_Prescription> prescc=doctorPrescriptionRepository.findByPatient(patient);
          Doctor_Prescription prescc1=prescc.get(0);
          
             assertEquals("1mg", prescc1.getDosage());





   }
    }