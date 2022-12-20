package com.New.LHS20.Service;

 

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Dao.DoctorPrescriptionRepository;
import com.New.LHS20.Dao.DoctorRepository;
 
import com.New.LHS20.Dao.PrescriptionRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dao.MonitoringDataRepository;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;

@RunWith(SpringRunner.class)
@SpringBootTest
class DoctorServiceImplTest {
    @InjectMocks
    DoctorServiceImpl doctorServiceImpl;
    @Mock
    MonitoringDataRepository monitoringdatarepository ;
    @Mock
    RegistrationRepository registrationRepository;
    @Mock
    SlotRepo slotRepo;
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    PrescriptionRepository prescriptionRepository;
    @Mock
    DoctorPrescriptionRepository doctorPrescriptionRepository;

    void setUp() throws Exception 
    {

        MockitoAnnotations.initMocks(this);
    }
    @Test
    void FetchById() {
        Patient patient=new Patient();
        patient.setUserId(1);


        MonitoringData monitoringData=new MonitoringData();

            List<MonitoringData> monitoringDatas=  new ArrayList();

            monitoringDatas.add(new MonitoringData(2, 51, "41",66, "5.1", "23", patient));
            monitoringdatarepository.saveAll(monitoringDatas);


            when(monitoringdatarepository.findByPatient(patient)).thenReturn(monitoringDatas);
            MonitoringData monitoringData1=doctorServiceImpl.fetchById(patient);

            assertNotNull(monitoringData1);
            assertEquals("5.1", monitoringData1.getHeight());

}
      // doctor adding the slot
    @Test
    public void addSlot() throws ParseException  {
        SlotTime st=new SlotTime();
        st.setDate("26/10/2022");
        st.setStartTime("10:30");
        st.setEndTime("11:30");
        st.setDoctorId((long)1);

        Date slotDate = new SimpleDateFormat("dd/MM/yyyy").parse(st.getDate());
        CharSequence hours = st.getStartTime();

        DateTimeFormatter formatTimeNow = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime timeNow = LocalTime.parse(hours, formatTimeNow);

        RegistrationForm registrationForm1=new RegistrationForm();
        registrationForm1.setUserId(1);
        registrationForm1.setUsername("usha@gamil.com");
        registrationForm1.setRoleName("DOCTOR");
       
        List<SlotTime> listOfSlots = slotRepo.findByDoctorId(registrationForm1.getUserId());
        int booked = 0;
        for (SlotTime slotTimeEntity:listOfSlots) {
            if (registrationForm1.getRoleName().equals("DOCTOR")
                    && st.getStartTime().equals("10:30")
                    && st.getDate().equals("26/10/2022")
                    && st.getEndTime().equals("01:30")) {
                booked++;
            }
        }
        if (booked == 0) {
            slotRepo.save(st);
            List<SlotTime> listOfSlotsOutput = new ArrayList<SlotTime>();
            listOfSlotsOutput.add(st);
            assertEquals("usha@gamil.com",registrationForm1.getUsername());
        }
    }
     // get appointments by the date
    @Test
    public void getAppointbydate() {
        SlotTime st=new SlotTime();
        st.setDate("26/10/2022");
        st.setStartTime("10:30");
        st.setEndTime("11:30");
        st.setDoctorId((long)1);
      
         RegistrationForm registrationForm1=new RegistrationForm();
            registrationForm1.setUserId(1);
            registrationForm1.setUsername("usha@gamil.com");
        RegistrationForm registrationForm = registrationRepository.findByUsername(registrationForm1.getUsername());
        List<SlotTime> slotDate = slotRepo.findByDate(st.getDate());
        Iterator iterator = slotDate.iterator();
        List getDate = new ArrayList<>();
        while (iterator.hasNext()) {
            SlotTime slotTime = (SlotTime) iterator.next();
            if (String.valueOf(registrationForm.getUserId()).equals(slotTime.getDoctorId().toString())) {
                slotTime.setDoctorId(registrationForm.getUserId());
                getDate.add(slotTime);
                assertEquals("usha@gamil.com",registrationForm1.getUsername());
            }
        }

    }

    
    @Test
    public void getcurrentdateappointments() {
        SlotTime st=new SlotTime();
        st.setDate("13/08/2022");
        st.setStartTime("10:30");
        st.setEndTime("11:30");
        st.setDoctorId((long)1);
       
        List<SlotTime> listOfSlots = slotRepo.findByDoctorId(st.getDoctorId());
 
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String today = LocalDate.now().format(format1);
        String tillDate = LocalDate.now().plusDays(7).format(format1);
        assertEquals("13/08/2022",st.getDate());

    }

     // Doctor can add medicines
    @Test
    public void addmedicines() {
       // String userName = authentication.getName();
        Doctor doc=new Doctor();
        doc.setEmail("usha@gamil.com");
        doc.setId(1);

        Doctor doctor = doctorRepository.findByEmail(doc.getEmail());
        Doctor_Prescription docpriscp=new Doctor_Prescription();
        Doctor abc=docpriscp.getDoctor();
        docpriscp.setDoctor(doctor); 
        docpriscp.setMedicineName("Dolo");
        docpriscp.setDosage("1mg");
        docpriscp.setQuantity(4);
        List<Doctor_Prescription> prescriptionList = prescriptionRepository.findByDoctor(doctor);
        int listCount = 0;
        for (Doctor_Prescription prescription2 : prescriptionList) {

            if (prescription2.getDuration() == null) {
                docpriscp.setId(prescription2.getId());
                docpriscp.setDoctor(doctor);
                doctorPrescriptionRepository.save(docpriscp);
                listCount++;
            }
        }
        if (listCount == 0) {
         
            doctorPrescriptionRepository.save(docpriscp);

        }
        assertEquals("Dolo",docpriscp.getMedicineName());

}}
