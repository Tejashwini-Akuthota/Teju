package com.New.LHS20.Service;

import javax.servlet.http.HttpServletResponse;

import com.New.LHS20.Entity.Bill;
import com.lowagie.text.DocumentException;

import io.jsonwebtoken.io.IOException;

public interface BillPdfService {
	public void export(HttpServletResponse response, Bill bill)  throws IOException, DocumentException, java.io.IOException;

}
