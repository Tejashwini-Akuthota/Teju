package com.New.LHS20.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Entity.RegistrationForm;

@SpringBootTest
public class RegistrationRepoTest {

@Autowired
	private RegistrationRepository registrationRepository;

	
	// boolean existsByUsername(String username);
	@Test
	void existsByUsername() {
		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);
     	Boolean actualResult = registrationRepository.existsByUsername("Usharanivuha@gmail.com");
		 
		assertThat(actualResult).isTrue();
	}

	
	// boolean existsByEmail(String email);
	@Test
	void existsByEmail() {
		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);
	 

		Boolean actualResult = registrationRepository.existsByEmail("Usharanivuha@gmail.com");
		System.err.println(actualResult);
		assertThat(actualResult).isTrue();
	}

	
	// RegistrationForm findByUsername(String username);
	@Test
	void findByUsername() {

		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);

		RegistrationForm actualResult = registrationRepository.findByUsername("Usharanivuha@gmail.com");

		assertThat(actualResult.getUsername()).isEqualTo("Usharanivuha@gmail.com");
	}

	
	// RegistrationForm findByEmail(String email);
	@Test
	void findByEmail() {

		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);

		RegistrationForm actualResult = registrationRepository.findByEmail("Usharanivuha@gmail.com");

		assertThat(actualResult.getEmail()).isEqualTo("Usharanivuha@gmail.com");
	}

	
	
	// RegistrationForm findByPhoneNo(String phoneno);
	@Test
	void findByPhoneNo() {

		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);

		RegistrationForm actualResult = registrationRepository.findByPhoneNo("+918186918990");

		assertThat(actualResult.getPhoneNo()).isEqualTo("+918186918990");
	}

	
	// boolean existsByPhoneNo(String number);
	@Test
	void existsByPhoneNo() {
		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);
		registrationRepository.save(registrationForm);

		Boolean actualResult = registrationRepository.existsByPhoneNo("+918186918990");

		assertThat(actualResult).isTrue();
	}

     
	
	//List<RegistrationForm> findByRoleName(String rolename);
	@Test
	void findByRoleName() {
		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);
		List<RegistrationForm> actualResult = registrationRepository.findByRoleName("USER");
		RegistrationForm abc = actualResult.get(0);
		assertThat(abc.getRoleName()).isEqualTo("USER");
	}

//boolean existsByRoleName(String number);	
	@Test
	void existsByRoleName() {
		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);

		Boolean actualResult = registrationRepository.existsByRoleName("USER");

		assertThat(actualResult).isTrue();
	}

//  List<RegistrationForm> findByUserId(Long specid);
	@Test
	void findByUserId() {
		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);
		List<RegistrationForm> actualResult = registrationRepository.findByUserId((long) 1);
		RegistrationForm abc = actualResult.get(0);
		assertThat(abc.getUserId()).isEqualTo(1);
	}

	// Optional<RegistrationForm> findById(long userId);
	@Test
	void findById() {
		RegistrationForm registrationForm = new RegistrationForm(1, "Usha", "rani", "Usharanivuha@gmail.com",
				"+918186918990", "04/08/1997", "female", "Usharanivuha@gmail.com", "Rani@1234", "USER", null);
		Optional<RegistrationForm> actualResult = registrationRepository.findById((long) 1);
		RegistrationForm abc = actualResult.get();
		assertThat(abc.getUserId()).isEqualTo(1);
	}

}

 

	

