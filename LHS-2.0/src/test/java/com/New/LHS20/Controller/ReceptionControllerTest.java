package com.New.LHS20.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.New.LHS20.Dao.ReceptionRepository;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class ReceptionControllerTest {
	
	@MockBean
	ReceptionRepository receptionRepository;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void fetchById() throws Exception {
		AdmissionForm admission = new AdmissionForm();
		admission.setId(1);
		admission.setBedNo(15);
		admission.setAdmissionDate("19/08/2022");
		admission.setDisease("Malaria");
		admission.setRegdNo(11);
		admission.setRoomNo(103);
		admission.setWard("ICU");
		admission.setDoctor("Padmavathi@gmail.com");
		
		
		List<AdmissionForm> admissionList = new ArrayList<>();
		admissionList.add(admission);
		
		Mockito.when(receptionRepository.findByRegdNo(admission.getRegdNo())).thenReturn(admissionList);

		String URI = "/api/getPatientById";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).contentType(MediaType.APPLICATION_JSON)
				.content("{\"regdNo\":11}").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(admissionList);
		 
		String outputInJson = result.getResponse().getContentAsString();
		 
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
