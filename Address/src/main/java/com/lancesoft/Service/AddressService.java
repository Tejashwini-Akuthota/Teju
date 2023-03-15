package com.lancesoft.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lancesoft.Entity.Address;
import com.lancesoft.Repository.AddressRepository;
import com.lancesoft.Response.AddressResponse;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressResponse findAddressByEmployeeId(int employeeId) {
		
	Address address = addressRepository.findAddressByEmployeeId(employeeId);
		 
	AddressResponse addressResponse =  modelMapper.map(address,AddressResponse.class);
	
	return addressResponse;
	
	}

}
