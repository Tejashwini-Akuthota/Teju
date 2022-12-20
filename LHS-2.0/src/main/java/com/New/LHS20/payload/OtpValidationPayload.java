package com.New.LHS20.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
	public class OtpValidationPayload {
	private String otp;
	private String password;
	
}


