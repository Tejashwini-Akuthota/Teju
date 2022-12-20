package com.New.LHS20.Service;

import org.springframework.http.ResponseEntity;

import com.New.LHS20.payload.OtpValidationPayload;

public interface OtpValidationService {
	
	 public ResponseEntity reset(OtpValidationPayload payload) ;

}
