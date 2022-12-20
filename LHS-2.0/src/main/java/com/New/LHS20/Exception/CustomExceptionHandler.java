//package com.New.LHS20.Exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//@ControllerAdvice
//public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
//	RegistrationCustomException customException=new  RegistrationCustomException();
//	@ExceptionHandler(RegistrationCustomException.class)
//	public ResponseEntity<String> customException() {
//		return new ResponseEntity<String>(customException.getErrorMessage(), HttpStatus.BAD_REQUEST);
//		 
//	}
//}
