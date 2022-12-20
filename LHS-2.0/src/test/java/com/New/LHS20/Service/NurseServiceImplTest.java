package com.New.LHS20.Service;
	 

	import static org.junit.Assert.assertEquals;
	import static org.mockito.Mockito.when;

	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;
	import java.util.ArrayList;
	import java.util.List;

	import org.junit.jupiter.api.Test;
	import org.junit.runner.RunWith;
	import org.mockito.Mockito;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.test.context.junit4.SpringRunner;

	 
	import com.New.LHS20.Dao.ReceptionRepository;
	import com.New.LHS20.Dao.RegistrationRepository;
	import com.New.LHS20.Dao.SlotRepo;
	import com.New.LHS20.Dao.SupplimentsRepository;
	import com.New.LHS20.Dao.MonitoringDataRepository;
	import com.New.LHS20.Entity.AdmissionForm;
	import com.New.LHS20.Entity.MonitoringData;
	import com.New.LHS20.Entity.RegistrationForm;
	import com.New.LHS20.Entity.SlotTime;
	import com.New.LHS20.Entity.Suppliments;

	@RunWith(SpringRunner.class)
	@SpringBootTest
class NurseServiceImplTest {
		
		
		@MockBean
	    private ReceptionRepository receprepo;
	    @MockBean
	    private SlotRepo slotRepository;
	    @MockBean
	    private SupplimentsRepository supplimentsRepository;
	    @MockBean
	    private  MonitoringDataRepository monitoringdatarepository;

	    @Test
	    public void getAllAdmittedPatients() {
	        List<AdmissionForm> admissionForms=  new ArrayList();
	        admissionForms.add(new AdmissionForm(1, 2, "11/01/2022", "3", "fever", "ward no2", 5, 6));
	        when(receprepo.findAll()).thenReturn( admissionForms);
	        assertEquals(admissionForms,receprepo.findAll());
	    }
	    @Test
	    public void updateProfile() {
	        RegistrationRepository registrationRepo2 = Mockito.mock(RegistrationRepository.class);
	        RegistrationForm inputdata = new RegistrationForm();
	        inputdata.setEmail("abc1234@gmail.com");
	        RegistrationForm outputData = new RegistrationForm();
	        outputData.setEmail("123456789@gmail.com");
	        outputData.setEmail(inputdata.getEmail());
	        Mockito.when(registrationRepo2.save(outputData)).thenReturn(outputData);
	       assertEquals(registrationRepo2.save(outputData), inputdata);
	    }
	    @Test
	    public void getupcommingappointments() {
	        List<SlotTime> slottime=  new ArrayList();
	        slottime.add(new SlotTime(1,"01/08/2022", "11:30", "12:30",(long)3, (long)2, "low fever", "fever"));
	        when(slotRepository.findAll()).thenReturn( slottime);
	        assertEquals(slottime,slotRepository.findAll());
	    }
	 
	    @Test
	     public void getcurrentappointments() {
	         DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	         String today = LocalDate.now().format(format1);

	         List<SlotTime> list = slotRepository.findByDate(today);
	         when(list).thenReturn(list);
	          assertEquals(list, slotRepository.findByDate(today));
	     }

	 
	    
	           
	    @Test
	    public void addsuppliments() {
	        List<Suppliments> suppliments=  new ArrayList();
	        suppliments.add(new Suppliments(1,"Cotton", "5", null, 153));
	        when(supplimentsRepository.saveAll(suppliments)).thenReturn( suppliments);
	        assertEquals(suppliments,supplimentsRepository.saveAll(suppliments));
	    }
	    @Test
	    public void addmoniteringdata() {
	        List<MonitoringData> monitoringDatas=  new ArrayList();
	        monitoringDatas.add(new MonitoringData(1,80, "91C", 50, "5.5", "120/80", null));
	        when(monitoringdatarepository.saveAll(monitoringDatas)).thenReturn( monitoringDatas);
	        assertEquals(monitoringDatas,monitoringdatarepository.saveAll(monitoringDatas));
	    }

	            }


