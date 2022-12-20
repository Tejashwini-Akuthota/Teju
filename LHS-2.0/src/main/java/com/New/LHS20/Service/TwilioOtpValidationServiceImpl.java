package com.New.LHS20.Service;

import javax.servlet.http.HttpSession;

import com.New.LHS20.Controller.ForgotPasswordController;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.payload.TwilioOtpValidationPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TwilioOtpValidationServiceImpl  implements TwillioOtpValidationService{
	@Autowired
	HttpSession httpSession;

	@Autowired
	RegistrationRepository registrationRepository;




	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	public ResponseEntity<String> reset(TwilioOtpValidationPayload twilliotpvalidationpayload) {

		try {
			if (httpSession.getAttribute("otp").equals(twilliotpvalidationpayload.getOtp().toString())) {
				String number = String.valueOf(httpSession.getAttribute("to"));

				RegistrationForm registrationForm = registrationRepository.findByPhoneNo(number);
				System.out.println(registrationForm);
				registrationForm.setPassword(bcryptPasswordEncoder.encode(twilliotpvalidationpayload.getPassword().toString()));
				registrationRepository.save(registrationForm);
				httpSession.invalidate();

				return new ResponseEntity<String>("password changed", HttpStatus.OK);

			} else {
				return new ResponseEntity<String>("wrong otp", HttpStatus.BAD_REQUEST);

			}
		} catch (NullPointerException e) {
			return new ResponseEntity<String>("wrong otp", HttpStatus.BAD_REQUEST);

		}
	}
}
