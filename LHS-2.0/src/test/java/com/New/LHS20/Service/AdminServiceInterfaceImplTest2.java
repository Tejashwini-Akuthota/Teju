package com.New.LHS20.Service;

	import static org.junit.Assert.assertEquals;
	import static org.mockito.Mockito.when;

	import org.junit.jupiter.api.Test;
	import org.mockito.Mockito;

	import com.New.LHS20.Dao.RegistrationRepository;
	import com.New.LHS20.Entity.RegistrationForm;


class AdminServiceInterfaceImplTest2 {
	 

	 

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
	    public void addAdmin() {

	        RegistrationForm registrationForm = new RegistrationForm(1,"Usha", "rani","usha@gmail.com","+917793972515",
	                "14/05/2000","female","usha@gmail.com","usha123","ADMIN",null);
	         RegistrationRepository registrationRepo = Mockito.mock(RegistrationRepository.class);

	        when(registrationRepo.save(registrationForm)).thenReturn(registrationForm);
	        assertEquals(registrationForm, registrationRepo.save(registrationForm));
	    }
	    

	    @Test
	    public void addNurse() {

	        RegistrationForm registrationForm = new RegistrationForm(2,"santhosh", "kumar","santhosh@gmail.com","+917793972515",
	                "14/05/1995","male","santhosh@gmail.com","santhu123","DOCTOR",null);
	         RegistrationRepository registrationRepo = Mockito.mock(RegistrationRepository.class);

	        when(registrationRepo.save(registrationForm)).thenReturn(registrationForm);
	        assertEquals(registrationForm, registrationRepo.save(registrationForm));
	    }
	    
	    @Test
	    public void addDoctor() {

	        RegistrationForm registrationForm = new RegistrationForm(1,"padma", "va","padma@gmail.com","+919010889395",
	                "14/05/1995","female","padma@gmail.com","padam123","NURSE",null);
	         RegistrationRepository registrationRepo = Mockito.mock(RegistrationRepository.class);

	        when(registrationRepo.save(registrationForm)).thenReturn(registrationForm);
	        assertEquals(registrationForm, registrationRepo.save(registrationForm));
	    }
	    @Test
	    public void addPharmacist() {

	        RegistrationForm registrationForm = new RegistrationForm(1,"teju", "ka","teju@gmail.com","+919010989395",
	                "14/05/1994","female","teju@gmail.com","teju123","PHARMACIST",null);
	         RegistrationRepository registrationRepo = Mockito.mock(RegistrationRepository.class);

	        when(registrationRepo.save(registrationForm)).thenReturn(registrationForm);
	        assertEquals(registrationForm, registrationRepo.save(registrationForm));
	    }
	    @Test
	    public void addReceptionist() {

	        RegistrationForm registrationForm = new RegistrationForm(1,"ravi", "ua","ravi@gmail.com","+918010889395",
	                "04/06/1996","male","ravi@gmail.com","ravi123","RECEPIONIST",null);
	         RegistrationRepository registrationRepo = Mockito.mock(RegistrationRepository.class);

	        when(registrationRepo.save(registrationForm)).thenReturn(registrationForm);
	        assertEquals(registrationForm, registrationRepo.save(registrationForm));
	    }

	}