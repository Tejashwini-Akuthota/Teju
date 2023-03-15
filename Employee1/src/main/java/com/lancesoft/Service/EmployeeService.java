package com.lancesoft.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lancesoft.Entity.Employee;
import com.lancesoft.Repository.EmployeeRepository;
import com.lancesoft.Response.AddressResponse;
import com.lancesoft.Response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	// @Autowired
	private RestTemplate restTemplate;

	// @Value("${addressservice.base.url}")
	// private String addressBaseURL;

	public EmployeeService(@Value("${addressservice.base.url}") String addressBaseURL, RestTemplateBuilder builder) {

		// System.out.println("uri" + addressBaseURL);
		this.restTemplate = builder
							.rootUri(addressBaseURL)
							.build();
	}

	public EmployeeResponse getEmployeeById(int id) {

		Employee employee = employeeRepository.findById(id).get();

		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);

		AddressResponse addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);

		employeeResponse.setAddressResponse(addressResponse);

		return employeeResponse;
	}
}
