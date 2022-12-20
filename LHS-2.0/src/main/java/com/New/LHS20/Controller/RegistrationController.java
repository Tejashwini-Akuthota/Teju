package com.New.LHS20.Controller;

import javax.validation.Valid;

//import com.New.LHS20.Dto.JwtRequestPayload;
//import com.New.LHS20.Dto.JwtResponse;
import com.New.LHS20.Dto.RegistrationDto;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Service.RegistrationServiceImpl;
import com.New.LHS20.Service.TwilioOtpValidationServiceImpl;
import com.New.LHS20.Service.TwillioServImpl;
import com.New.LHS20.payload.ForgotPasswordTwilio;
import com.New.LHS20.payload.TwilioOtpValidationPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RegistrationController {

	@Autowired
	private RegistrationServiceImpl registrationServiceImpl;

	@Autowired
	private TwillioServImpl twillioServImpl;

	@Autowired
	private TwilioOtpValidationServiceImpl  twilliootpOtpValidationServiceImpl;
	
	
	
    //User can register
	@PostMapping("/register")
	public ResponseEntity add(@RequestBody @Valid RegistrationDto dto) {

		

	  registrationServiceImpl.addUser(dto);
	  return new ResponseEntity("Registered Successfully", HttpStatus.OK);

	}

	
	//To receive mobile otp
	@PostMapping("/sendotp")
	public ResponseEntity sendotp(@RequestBody ForgotPasswordTwilio twilio) {

		twillioServImpl.send(twilio);
		return new ResponseEntity("Otp sent successfully", HttpStatus.OK);

	}

	
	//Mobile otp  validation
	@PostMapping("/validateotp")
	public ResponseEntity validateEmail(@RequestBody TwilioOtpValidationPayload otpValidationPayload) {
		return twilliootpOtpValidationServiceImpl.reset(otpValidationPayload);
	}

}
