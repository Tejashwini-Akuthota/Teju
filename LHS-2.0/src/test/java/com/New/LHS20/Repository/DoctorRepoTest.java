//package com.New.LHS20.Repository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.New.LHS20.Dao.RegistrationRepository;
//import com.New.LHS20.Entity.Doctor;
//import com.New.LHS20.Entity.RegistrationForm;
//
//@DataJpaTest
//@SpringBootTest
//public class DoctorRepoTest {
//	
//	@Autowired
//    private RegistrationRepository registrationRepository;
//
//    @BeforeEach
//    void initUseCase() {
//        List<RegistrationForm> regform = Arrays.asList(
//                new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
//        				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null)
//        );
//        registrationRepository.saveAll(regform);
//    }
//
//    @AfterEach
//    public void DeleteAll(){
//    	registrationRepository.deleteAll();
//    }
//    	//Doctor findById(long Id);
//    	
//    	@Test
//    	void  findById() {
//            List<RegistrationForm> regform = registrationRepository.findByUserId(1);
//            RegistrationForm    regform1 =  regform.get(0);
//            long  REG1 =regform1.getUserId();
//            assertThat(regform1.getUsername()).isEqualTo("Usharanivuha@gmail.com");
//            
//        }
//    	
//    }

	
	


