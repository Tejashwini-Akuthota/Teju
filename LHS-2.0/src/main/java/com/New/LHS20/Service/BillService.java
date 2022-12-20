package com.New.LHS20.Service;

import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Entity.Bill;

public interface BillService {
	
	public Bill addBill(@RequestBody Bill bill);
	public int addBillMedical(@RequestBody Bill bill);

}
