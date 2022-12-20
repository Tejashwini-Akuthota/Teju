package com.New.LHS20.Service;

import java.util.Random;

import javax.servlet.http.HttpSession;

import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.payload.ForgotPasswordTwilio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwillioServImpl implements TwillioServ {

	@Autowired
	HttpSession httpSession;

	@Autowired
	RegistrationRepository registrationRepository;

	private String sid = "AC8b6cb8f79292b8dbe3657f9a4dcba339";
	private String token = "f68d124cd2810f81d192df1e4b094f5e";
	private String trialnumber = "+19705572198";

	public void send(ForgotPasswordTwilio forgotPasswordTwillio) {

		if (true && registrationRepository.existsByPhoneNo(forgotPasswordTwillio.getPhoneNo())) {
			Random r = new Random();
			int i = r.nextInt(9999);
			if (i < 1000) {
				i += 1000;
			}

			String msg = "your otp is " + i;
			Twilio.init(sid, token);
			MessageCreator message = Message.creator(new PhoneNumber(forgotPasswordTwillio.getPhoneNo()), new PhoneNumber(trialnumber),
					msg);
			message.create();
			String otp = String.valueOf(i);
			this.httpSession.setAttribute("otp", otp);
			this.httpSession.setAttribute("to", forgotPasswordTwillio.getPhoneNo());
			System.out.println("sent");
		}
	}

}
