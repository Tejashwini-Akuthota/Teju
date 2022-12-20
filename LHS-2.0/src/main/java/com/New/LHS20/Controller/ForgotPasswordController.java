package com.New.LHS20.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.New.LHS20.Service.MailServiceImpl;
import com.New.LHS20.Service.OtpValidationServiceImpl;
import com.New.LHS20.payload.ForgotPassword;
import com.New.LHS20.payload.OtpValidationPayload;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ForgotPasswordController {

	@Autowired
	OtpValidationServiceImpl otpValidationServiceImpl;

	@Autowired
	MailServiceImpl mailServiceImpl;

	
	//otp will be sent to email
	@PostMapping("/email")
	public ResponseEntity checkEmail(@RequestBody ForgotPassword forgotPassword) {
		return mailServiceImpl.forgotPassword(forgotPassword);
    // return new ResponseEntity("otp sent",HttpStatus.ACCEPTED);

	}

	
	//To validate otp
	@PostMapping("/validate")
	public ResponseEntity validateEmail(@RequestBody OtpValidationPayload otpValidationPayload) {
		return otpValidationServiceImpl.reset(otpValidationPayload);
		//return new ResponseEntity("password Reset",HttpStatus.ACCEPTED);
	}
}
