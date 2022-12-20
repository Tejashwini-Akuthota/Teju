package com.New.LHS20.Controller;



import static org.assertj.core.api.Assertions.assertThat;



import java.util.ArrayList;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.New.LHS20.Dto.RegistrationDto;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Service.RegistrationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
public class RegistrationControllerTest2 {
    
    RegistrationServiceImpl registrationServiceImpl= Mockito.mock(RegistrationServiceImpl.class);
    @Test
    public void add() throws Exception {



       RegistrationDto regdto = new RegistrationDto();
        regdto.setEmail("usharanivuha@gmail.com");
        regdto.setUserId(1);
        regdto.setFirstName("usha");
        regdto.setGender("female");
        regdto.setPhoneNo("7793918454");
        regdto.setLastName("rani");
        regdto.setDob("05/07/1997");
        regdto.setUsername("usharanivuha@gmail.com");
        
        
        Patient patient=new Patient();
        
        ModelMapper mapper=new ModelMapper();
        mapper.map(regdto, patient);
        
        List<RegistrationDto> specialityList1 = new ArrayList<RegistrationDto>();



       specialityList1.add(regdto);
        
        String inputInJson = this.mapToJson(specialityList1);
        String URI = "/api/doctor";
                  
        
        Mockito.when(registrationServiceImpl.addUser(regdto)).thenReturn(patient);
        Patient regform=registrationServiceImpl.addUser(regdto);
        
        
        assertThat(regform.getFirstName()).isEqualTo(regdto.getFirstName());
     
}
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}