package com.New.LHS20.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.New.LHS20.*;
import com.New.LHS20.Dao.DoctorRepository;
import com.New.LHS20.Dao.SlotRepo;
import com.New.LHS20.Dao.SpecialityRepository;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.New.LHS20.Dto.RegistrationFormUpdateDto;
import com.New.LHS20.Dto.SpecialityDto;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;
import com.New.LHS20.Entity.Speciality;
import com.New.LHS20.Service.DoctorServiceImpl;
import com.New.LHS20.TestSecurity.TestSecurityConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
//@WebMvcTest(value = DoctorController.class)
class DoctorControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorServiceImpl doctorServiceImpl;

	@MockBean
	private DoctorRepository doctorRepository;

	@MockBean
	private SpecialityRepository specialityRepository;

	@MockBean
	private SlotRepo slotRepo;

	@Test
	public void fetchById() throws Exception {
		MonitoringData mockTicket = new MonitoringData();
		mockTicket.setId(1);
		mockTicket.setPatient(new Patient(1, "", null, null, null, null, null, null, null, null));
		mockTicket.setTemperature("55");

		Mockito.when(doctorServiceImpl.fetchById(Mockito.any())).thenReturn(mockTicket);

		String URI = "/doctors/reg/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockTicket);

		String outputInJson = result.getResponse().getContentAsString();

		assertThat(outputInJson).isEqualTo(expectedJson);
	}

// getspeciality
	@Test
	public void getspecaility() throws Exception {
		Speciality mockTicket1 = new Speciality();
		mockTicket1.setDoctorId(1);
		mockTicket1.setName("Dentist");
		List<Speciality> specialityList = new ArrayList();
		specialityList.add(mockTicket1);

		Mockito.when(specialityRepository.findAll()).thenReturn(specialityList);

		String URI = "/doctors/getspeciality";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(specialityList);

		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	// getAllSlots
	@Test
	public void getAllSlots() {

		SlotTime slotTime = new SlotTime();

		slotTime.setDate("12/2/2022");
		slotTime.setDoctorId((long) 2);
		slotTime.setStartTime("123");
		List<SlotTime> slotList = new ArrayList();
		slotList.add(slotTime);
		when(slotRepo.findAll()).thenReturn(slotList);

		List<SlotTime> result = slotRepo.findAll();

		assertEquals("12/2/2022", result.get(0).getDate());

	}

	// getdocbyspeciality
	@Test
	public void getdocbyspeciality() throws Exception {
		Doctor mockTicket = new Doctor();
		mockTicket.setId(1);
		mockTicket.setFirstName("usha");
		mockTicket.setSpeciality("Dentist");

		List<Doctor> doclist = new ArrayList<>();
		doclist.add(mockTicket);
		Speciality speciality = new Speciality();
		speciality.setDoctorId(1);
		speciality.setName("usha");
		Mockito.when(doctorRepository.findBySpeciality(speciality.getName())).thenReturn(doclist);
		List<Doctor> doc = doctorRepository.findBySpeciality(speciality.getName());

		assertEquals(doclist, doc);
	}

//addSpeciality
	@Test
	public void addSpeciality() throws Exception {

		SpecialityDto mockspecialistdto = new SpecialityDto();
		mockspecialistdto.setEmail("usharanivuha@gmail.com");
		mockspecialistdto.setSpeciality("DENTIST");

		List<Speciality> specialityList = new ArrayList();
		Speciality mockspecialist = new Speciality();
		mockspecialist.setDoctorId(1);
		mockspecialist.setName("DENTIST");
		specialityList.add(mockspecialist);

		String inputInJson = this.mapToJson(mockspecialist);
		String URI = "/doctors/addspeciality";
		ResponseEntity responseEntity = new ResponseEntity<>(specialityList, HttpStatus.OK);

		Mockito.when(doctorServiceImpl.addSpec(Mockito.any())).thenReturn(responseEntity);

		ResponseEntity abc = doctorServiceImpl.addSpec(Mockito.any());

		assertThat(abc.getStatusCodeValue()).isEqualTo(200);

	}

//addSlot
	@Test
	public void addSlot() throws Exception {

		SlotTime mockspecialistdto = new SlotTime();
		mockspecialistdto.setDate("10/2/2022");
		mockspecialistdto.setDoctorId((long) 1);
		mockspecialistdto.setStartTime("10:30");
		mockspecialistdto.setEndTime("11:30");

		List<SlotTime> specialityList = new ArrayList();

		specialityList.add(mockspecialistdto);

		String inputInJson = this.mapToJson(specialityList);
		String URI = "/doctors/addSlots";
		ResponseEntity responseEntity = new ResponseEntity<>(specialityList, HttpStatus.OK);
		System.err.println(responseEntity);
		Mockito.when(doctorServiceImpl.addSlot(mockspecialistdto, null)).thenReturn(responseEntity);

		ResponseEntity abc = doctorServiceImpl.addSlot(mockspecialistdto, null);

		assertThat(abc.getStatusCodeValue()).isEqualTo(200);

	}

//getappointmentsbydate

	@Test
	public void getAppointbydate() {
		long docid = 6;
		long patid = 13;
		SlotTime slottime = new SlotTime();
		slottime.setId(1);
		slottime.setDate("18/08/2022");
		slottime.setDisease("Typhoid");
		slottime.setDoctorId(docid);
		slottime.setPatientId(patid);
		slottime.setStartTime("10:30");
		slottime.setEndTime("11:30");
		slottime.setStatus("Confirmed");

		long docid1 = 7;
		long patid1 = 14;
		SlotTime slottime1 = new SlotTime();
		slottime1.setId(2);
		slottime1.setDate("18/08/2022");
		slottime1.setDisease("Fever");
		slottime1.setDoctorId(docid1);
		slottime1.setPatientId(patid1);
		slottime1.setStartTime("12:30");
		slottime1.setEndTime("13:30");
		slottime1.setStatus("Confirmed");

		List<SlotTime> slots = new ArrayList();
		slots.add(slottime);
		slots.add(slottime1);

		ResponseEntity responseEntity = new ResponseEntity<>(slots, HttpStatus.OK);

		when(doctorServiceImpl.getAppointbydate(null)).thenReturn(responseEntity);

		ResponseEntity abc1 = doctorServiceImpl.getAppointbydate(null);

		assertThat(abc1.getStatusCodeValue()).isEqualTo(200);

	}

//getupcommingappointments
	@Test
	public void getupcommingappointments() {
		long docid = 3;
		long patid = 11;
		SlotTime slottime = new SlotTime();
		slottime.setId(4);
		slottime.setDate("20/08/2022");
		slottime.setDisease("Malaria");
		slottime.setDoctorId(docid);
		slottime.setPatientId(patid);
		slottime.setStartTime("8:30");
		slottime.setEndTime("9:30");
		slottime.setStatus("Confirmed");

		long docid1 = 5;
		long patid1 = 12;
		SlotTime slottime1 = new SlotTime();
		slottime1.setId(4);
		slottime1.setDate("21/08/2022");
		slottime1.setDisease("Fever");
		slottime1.setDoctorId(docid1);
		slottime1.setPatientId(patid1);
		slottime1.setStartTime("8:30");
		slottime1.setEndTime("9:30");
		slottime1.setStatus("Confirmed");

		List<SlotTime> slots = new ArrayList();
		slots.add(slottime);
		slots.add(slottime1);

		ResponseEntity responseEntity = new ResponseEntity<>(slots, HttpStatus.OK);

		when(doctorServiceImpl.getUpcommingAppointments(null)).thenReturn(responseEntity);

		ResponseEntity abc1 = doctorServiceImpl.getUpcommingAppointments(null);

		assertThat(abc1.getStatusCodeValue()).isEqualTo(200);

	}

////update  
//	@Test
//	public void updateMyProfile() {
//
//		ResponseEntity regform = new ResponseEntity<>(HttpStatus.ACCEPTED);
//		
//
//		regform.setFirstName("usha");
//		regform.setLastName("rani");
//		regform.setEmail("usharanivuha@gmail.com");
//		regform.setPhoneNo("+918186918990");
//		regform.setDob("05/07/1997");
//		regform.setGender("Female");
//		regform.setUsername("usharanivuha@gmail.com");
//		regform.setRoleName("NURSE");
//		regform.setUserId(1);
//		regform.setPassword("usha@1234");
//
//		RegistrationFormUpdateDto output = new RegistrationFormUpdateDto();
//
//		output.setFirstName("usha123");
//		output.setLastName("rani");
//		output.setEmail("usharanivuha1997@gmail.com");
//		output.setPhoneNo("+918186918990");
//		output.setDob("05/07/1997");
//		output.setGender("Female");
//		 
//
//		regform.setFirstName(output.getFirstName());
//		regform.setEmail(output.getEmail());
//
//		Doctor doctor = new Doctor();
//
//		ModelMapper mapper = new ModelMapper();
//		mapper.map(regform, doctor);
//
//		when(doctorServiceImpl.updateMyProfile(output)).thenReturn(regform);
//
//		RegistrationForm abc1 = doctorServiceImpl.updateMyProfile(output);
//
//		assertEquals(abc1.getFirstName(), output.getFirstName());
//
//	}

//addmedicines
	@Test
	public void addmedicines() {
		Patient patient = new Patient();
		patient.setUserId(2);
		Doctor_Prescription docprisc = new Doctor_Prescription();
		docprisc.setMedicineName("Dolo");
		docprisc.setQuantity(3);
		docprisc.setDosage("1 morning,1 night");
		docprisc.setDuration("5 days");
		docprisc.setInvestigations("Xray");
		docprisc.setPatient(patient);

		ResponseEntity responseEntity = new ResponseEntity<>(docprisc, HttpStatus.ACCEPTED);
		when(doctorServiceImpl.addmedicines(null, docprisc)).thenReturn(responseEntity);
		ResponseEntity abc2 = doctorServiceImpl.addmedicines(null, docprisc);
		assertThat(abc2.getStatusCodeValue()).isEqualTo(202);

	}

	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
