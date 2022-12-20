package com.New.LHS20.Service;

import org.springframework.http.ResponseEntity;

import com.New.LHS20.payload.TwilioOtpValidationPayload;

public interface TwillioOtpValidationService {
	public ResponseEntity<String> reset(TwilioOtpValidationPayload   twilioOtpValidationPayload);
}
