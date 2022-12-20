package com.New.LHS20.Controller;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
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
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Entity.AdmissionForm;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Service.AdminServiceInterfaceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class AdminControllerTest {
    
	 @MockBean
	 AdminServiceInterfaceImpl adminServiceInterfaceImpl ;
	 
	  
	    
	    @Autowired
		private MockMvc mockMvc;



		private Object jsonParser;
	    
		
		
	//addAdmin
	    @Test
	    public void addAdmin() throws Exception {



	       RegistrationForm regform1 = new RegistrationForm();
	        regform1.setEmail("usharanivuha@gmail.com");
	        regform1.setUserId(1);
	        regform1.setFirstName("usha");
	        regform1.setGender("female");
	        regform1.setPhoneNo("+917793918454");
	        regform1.setLastName("rani");
	        regform1.setRoleName("ADMIN");
	        regform1.setPassword("Usha@1234");
	        regform1.setUsername("usharanivuha@gmail.com");
	        regform1.setDob("01/05/1997");
	        
	        
	        List<RegistrationForm> regformlist = new ArrayList();
	        regformlist.add(regform1);
	        String abc=regformlist.get(0).toString();
	      
			Mockito.when(adminServiceInterfaceImpl.addAdmin(regform1)).thenReturn(abc);
             
	      
	        
	        String URI = "/api/admin";
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(abc)).accept(MediaType.APPLICATION_JSON);
			
			System.out.println(requestBuilder);
		 
			
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
		    
			
			String expectedJson = this.mapToJson(abc);
			
			 
		
			String outputInJson =  result.getResponse().getContentAsString();
			 
			assertThat(outputInJson).isEqualTo(expectedJson);
	     
	}
	    
//	    //addDoctor
        @Test
        public void addDoctor() throws Exception {



           RegistrationForm mockspecialistdto = new RegistrationForm();
            mockspecialistdto.setEmail("usharanivuha@gmail.com");
            mockspecialistdto.setUserId(1);
            mockspecialistdto.setFirstName("usha");
            mockspecialistdto.setGender("female");
            mockspecialistdto.setPhoneNo("7793918454");
            mockspecialistdto.setLastName("rani");
            mockspecialistdto.setRoleName("ADMIN");
            
            Doctor doctor=new Doctor();
            
            ModelMapper mapper=new ModelMapper();
            mapper.map(mockspecialistdto, doctor);
            
            List<RegistrationForm> specialityList1 = new ArrayList<RegistrationForm>();



           specialityList1.add(mockspecialistdto);
            
            String inputInJson = this.mapToJson(specialityList1);
            String URI = "/api/doctor";
                      
            
            Mockito.when(adminServiceInterfaceImpl.addDoctor(mockspecialistdto)).thenReturn(doctor);
            Doctor regform=adminServiceInterfaceImpl.addDoctor(mockspecialistdto);
            
            assertThat(regform.getFirstName()).isEqualTo(mockspecialistdto.getFirstName());
         
    }
        
        
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
        
    }
   
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    

}
