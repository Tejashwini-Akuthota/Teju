package com.lancesoft.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
	
	
	private int id;
	private String lane1;
	private String lane2;
	private String state;
	private long zip;
	
	

}
