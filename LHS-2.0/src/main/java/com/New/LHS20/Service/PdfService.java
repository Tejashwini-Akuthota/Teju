package com.New.LHS20.Service;

import javax.servlet.http.HttpServletResponse;

import com.New.LHS20.Entity.Doctor_Prescription;
import com.lowagie.text.DocumentException;

import io.jsonwebtoken.io.IOException;

public interface PdfService {
	public void export(HttpServletResponse response, Doctor_Prescription doctorpresc)  throws IOException, DocumentException, java.io.IOException;

}
