package com.New.LHS20;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 
import com.twilio.Twilio;

@SpringBootApplication
public class Application {
	
//	@Autowired
//	private TwilioConfig twilioConfig;
//	
//	@PostConstruct
//	public void initTwilio() {
//	Twilio.init(twilioConfig.getSid(), twilioConfig.getToken());
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SimpleMailMessage simpleMail() {
	return new SimpleMailMessage();
	}
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
