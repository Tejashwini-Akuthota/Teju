package com.New.LHS20.Service;

import com.New.LHS20.payload.ForgotPasswordTwilio;

public interface TwillioServ {
	public void send(ForgotPasswordTwilio forgotPasswordTwilio);
}
