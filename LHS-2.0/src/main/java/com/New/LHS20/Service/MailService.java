package com.New.LHS20.Service;

import org.springframework.http.ResponseEntity;

import com.New.LHS20.payload.ForgotPassword;

public interface MailService {
	public void sendMail(String to);
	public ResponseEntity forgotPassword(ForgotPassword forgotPasscode) ;
}
