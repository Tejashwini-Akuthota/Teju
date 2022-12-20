
package com.New.LHS20.Controller;



import static org.assertj.core.api.Assertions.assertThat;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



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



import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Service.BillServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
class BillControllerTest {



   @MockBean
    BillServiceImpl billserviceimpl;



   @Autowired
    private MockMvc mockMvc;



   @Test
    public void addBill() throws Exception {

    Doctor doctor = new Doctor();
        doctor.setId(2);

    Patient patient = new Patient();
        patient.setUserId(1);



    Doctor_Prescription docpresc = new Doctor_Prescription();
        docpresc.setAmount(100);
        docpresc.setQuantity(5);



     Bill bill3 = new Bill();
          bill3.setId(5);
          bill3.setAppointmentdate("30/07/2022");
          bill3.setPatient(patient);
          bill3.setDoctor(doctor);

       Bill bill = new Bill();



        bill.setConsultations(15);
        bill.setAppointmentdate(bill3.getAppointmentdate());
        bill.setInvestigations(15);
        bill.setRoomrent(250);
        bill.setConsumables(16);
        bill.setPharmacy(docpresc.getAmount() * docpresc.getQuantity());


        bill.setAppointmenttime(bill3.getAppointmenttime());
        bill.setPatient(bill3.getPatient());
        bill.setDoctor(bill3.getDoctor());



       List<Bill> billlist = new ArrayList();



       billlist.add(bill3);



       Mockito.when(billserviceimpl.addBill(bill3)).thenReturn(billlist.get(0));



       String URI = "/api/addbillpharmacy";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(billlist.get(0))).accept(MediaType.APPLICATION_JSON);

System.out.println(billlist.get(0));

       MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(billlist.get(0));
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);



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