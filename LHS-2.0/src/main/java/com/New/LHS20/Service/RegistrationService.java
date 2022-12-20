package com.New.LHS20.Service;

import com.New.LHS20.Dto.RegistrationDto;
import com.New.LHS20.Entity.Patient;

public interface RegistrationService {
	public  Patient addUser(RegistrationDto  registrationDto);
}
