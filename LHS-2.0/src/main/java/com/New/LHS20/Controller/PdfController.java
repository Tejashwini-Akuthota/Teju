package com.New.LHS20.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Service.BillPdfServiceImpl;
import com.New.LHS20.Service.PdfServiceImpl;
import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PdfController {
	
	
	@Autowired
	PdfServiceImpl pdfservice;
	
	@Autowired
	BillPdfServiceImpl billpdfserviceimpl;
	


	
	
	//Generating pdf for prescription
	@GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response, @RequestBody Doctor_Prescription doctorpresc) throws IOException, DocumentException, java.io.IOException   {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";  
        response.setHeader(headerKey, headerValue);
          
        pdfservice.export(response,doctorpresc);
	 
			 
		 
	}
	
	
	
	
	//Generating pdf for Bill
	@GetMapping("/billpdf/generate")
    public void generatePDF1(HttpServletResponse response, @RequestBody Bill bill) throws IOException, DocumentException, java.io.IOException   {
		System.out.println("I  am called");
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";  
        response.setHeader(headerKey, headerValue);
          
        billpdfserviceimpl.export(response,bill);
	 
			 
		 
	}
}
