package com.lancesoft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lancesoft.Response.AddressResponse;
import com.lancesoft.Service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse>getAddressByEmployeeId(@PathVariable(required = false) int employeeId ) {
		
		AddressResponse addressResponse = null;
		
		addressResponse =  addressService.findAddressByEmployeeId(employeeId);
		
		return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
	}
}
