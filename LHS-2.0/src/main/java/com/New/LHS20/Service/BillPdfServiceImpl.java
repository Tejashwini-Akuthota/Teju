package com.New.LHS20.Service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.New.LHS20.Dao.BillRepositry;
import com.New.LHS20.Dao.DoctorPrescriptionRepository;
import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Dao.MonitoringDataRepository;
import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import io.jsonwebtoken.io.IOException;

@Service
public class BillPdfServiceImpl implements BillPdfService {
 
	@Autowired
	BillRepositry billrepository;
	
	@Autowired
	DoctorPrescriptionRepository prescrepo;
	 
	
	@Autowired
	RegistrationRepository  regrepo;
	
	@Autowired
	MonitoringDataRepository monitoringdatarepository;
	
	public void export(HttpServletResponse response, Bill bill)  throws IOException, DocumentException, java.io.IOException   {
		System.out.println("I am bill pdf service");
		List<Bill> bill1=  billrepository.findByPatient(bill.getPatient());
	    Bill bill2 =bill1.get(0);
	 List<Doctor_Prescription> doctorprescription =  prescrepo. findByPatient(bill.getPatient());
	  Doctor_Prescription doctorprescription1= doctorprescription.get(0);
	   Patient pat = bill2.getPatient();
		                              
	    RegistrationForm regform =regrepo.findByUsername(pat.getUsername());
		Doctor doctor=bill2.getDoctor();
 
 		Document document = new Document(PageSize.A4);
 		PdfWriter.getInstance(document, response.getOutputStream()); 	
 		  Image image= Image.getInstance("lhslogo2.jpg");
 	      image.scaleAbsolute(120,40);
 	      image.setAlignment(50);
 
		document.open();
              Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		fontTitle.setColor(144,238,144);
	    Font fontTitle1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontTitle1.setSize(14);
			fontTitle1.setColor(144,238,144);
//	    Header header = new Header("LIFE LINE HEALTH CARE", "0");
//		Paragraph paragraph = new Paragraph("Doctor_Prescription #6006922 for Order # BPFVO­6012102­211215 : omg.com");
	    Paragraph paragraph = new Paragraph("Bill",fontTitle1);
	    paragraph.setAlignment(Paragraph.ALIGN_CENTER);
	
	                    
		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);
		 fontParagraph.setColor(0, 0, 255);
		 
	Paragraph paragraph2 = new Paragraph("LIFE LINE HEALTH CARE",fontTitle);
//	Paragraph paragraph4 = new Paragraph("DoctorDetails:",fontParagraph1)
	
	
	Font fontParagraph1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	fontParagraph1.setSize(10);
	 fontParagraph1.setColor(100, 0, 60);
	 
	 
		paragraph2.setSpacingAfter(3f);
	paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
 

	Paragraph paragraph06 = new Paragraph("Details:",fontParagraph1);
	paragraph06.setAlignment(Paragraph.ALIGN_LEFT);
	Paragraph paragraph02 = new Paragraph("Appointment Date:"+ bill2.getAppointmentdate());
	paragraph02.setAlignment(Paragraph.ALIGN_LEFT);

	Paragraph paragraph03 = new Paragraph("Appointment Time:"+ bill2.getAppointmenttime());
	paragraph03.setAlignment(Paragraph.ALIGN_LEFT);
	Paragraph paragraph04 = new Paragraph("Discharge Date:"+ bill2.getDischargedate());
	paragraph04.setAlignment(Paragraph.ALIGN_LEFT);
	Paragraph paragraph05 = new Paragraph("Discharge Time:"+ bill2.getDischargetime());
	paragraph05.setAlignment(Paragraph.ALIGN_LEFT);


Paragraph paragraph01 = new Paragraph("Patient Details",fontParagraph1);
                   paragraph01.setAlignment(Paragraph.ALIGN_CENTER);
                
//		        Paragraph paragraph7 = new Paragraph(addressEntity.getState()+","+addressEntity.getPincode());
		Paragraph paragraph3 = new Paragraph("Gender");
		Paragraph paragraph5 = new Paragraph("DOB");
		Paragraph paragraph9 = new Paragraph("PhoneNo");
		
		
		
		
		
		
		
	Table table = new Table(2, 6);
		table.setAlignment(8);
		table.setBorder(3);
		table.setPadding(3);
		Cell cell = new Cell("Patient Name");
	    table.addCell(cell);
		 table.addCell(String.valueOf(pat.getFirstName()+ pat.getLastName()));
		 table.addCell(paragraph3);
		table.addCell(String.valueOf(pat.getGender()));
		 table.addCell(paragraph5);
		table.addCell(String.valueOf(pat.getDob()));
		table.addCell(paragraph9);
		table.addCell(String.valueOf(pat.getPhoneNo()));
		
		
		
		document.add(paragraph);
		
		 
	    document.add(paragraph2);
		document.add(image);
	 
	    document.add(paragraph06);
	    document.add(paragraph02);
	    document.add(paragraph03);
	    document.add(paragraph04);
	    document.add(paragraph05);
	    document.add(paragraph01);
	    document.add(table);
 
		
		 
		 
 
		//Adding table for Bill
 
		Paragraph paragraph10 = new Paragraph("Bill",fontParagraph1);
		 paragraph10.setAlignment(Paragraph.ALIGN_CENTER);
		 
		Paragraph paragraph11 = new Paragraph("Medicine Name",fontParagraph1 );
		Paragraph paragraph12 = new Paragraph("Amount" ,fontParagraph1);
		Paragraph paragraph13 = new Paragraph("Quantity",fontParagraph1 );
		Paragraph paragraph14 = new Paragraph("TotalAmount",fontParagraph1 );
		
	 
	 
		
		Table table1 = new Table(4,6);
//		PdfTable table1 =new  PdfTable();
		table1.setAlignment(8);
		table1.setBorder(3);
		table1.setPadding(3);
	     
		table1.addCell(paragraph11);
		table1.addCell(paragraph12);
		table1.addCell(paragraph13);
		table1.addCell(paragraph14);
	 
	 
	 

		int i=0;
		for( Doctor_Prescription doctorprecription1:doctorprescription ) {
			
			table1.addCell(doctorprecription1.getMedicineName());
			table1.addCell(String.valueOf(doctorprecription1.getAmount()));
			table1.addCell(String.valueOf(doctorprecription1.getQuantity()));
			table1.addCell(String.valueOf(doctorprecription1.getQuantity() *doctorprecription1.getAmount()));
			 
	 
		}
		document.add(paragraph10);
		 
		document.add(table1);
		 
		
		Paragraph paragraph15 = new Paragraph("                      "+"Total Medicines Amount:"+bill2.getPharmacy(),fontParagraph1);
		document.add(paragraph15);
		
		
		//Adding table for Services
		 
				Paragraph paragraph25 = new Paragraph("SERVICES", fontParagraph1);
				 paragraph25.setAlignment(Paragraph.ALIGN_CENTER);
				 
				Paragraph paragraph26 = new Paragraph("Service Name",fontParagraph1 );
				Paragraph paragraph27 = new Paragraph("Amount(Rs.)",fontParagraph1 );
				Paragraph paragraph28 = new Paragraph("Consumables");
				Paragraph paragraph29 = new Paragraph("Consultations");
				Paragraph paragraph30 = new Paragraph("Investigations");
				Paragraph paragraph31 = new Paragraph("RoomRent");
				 
				
			 
			 
				
				Table table2 = new Table(2,6);
//				PdfTable table1 =new  PdfTable();
				table2.setAlignment(8);
				table2.setBorder(3);
				table2.setPadding(3);
			     
				table2.addCell(paragraph26);
				table2.addCell(paragraph27);
				Cell cell1 = new Cell("Medical Equipments");
			    table2.addCell(cell1);
				 table2.addCell(String.valueOf(bill2.getMedicalEquipments()));
				 table2.addCell(paragraph28);
				table2.addCell(String.valueOf( bill2.getConsumables()));
				 table2.addCell(paragraph29);
				table2.addCell(String.valueOf(bill2.getConsultations()));
				table2.addCell(paragraph30);
				table2.addCell(String.valueOf(bill2.getInvestigations()));
				table2.addCell(paragraph31);
				table2.addCell(String.valueOf(bill2.getRoomrent()));
document.add(paragraph25);
document.add(table2);

   System.out.println(bill2.getTotal());
   
Paragraph paragraph21 = new Paragraph("                     "+"Grand Total:"+bill2.getTotal(), fontParagraph1);


    long number=bill2.getTotal();
    
   Paragraph paragraph22 = new  Paragraph("                     "+"Amount in words: " + convertNumberToWords(number),fontParagraph1);
  // Paragraph paragraph23 = new  Paragraph("This is a computer generated statement and requires no signature ",fontParagraph1);
		
 
document.add(paragraph21);
document.add(paragraph22);
//document.add(paragraph23);
    document.close();

}

	private String convertNumberToWords(long number) {
String words = "";
		
		String unitsArray[] = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
				"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		String tensArray[] = { "Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
				"Ninety" };
		if (number == 0) {
			return "zero";
		}
		
		if (number < 0) {
			
			//convert the number to a string
			String numberStr = "" + number;
			// remove minus before the number
			numberStr = numberStr.substring(1);
			// add minus before the number and convert the rest of number
			return "minus " + convertNumberToWords(Integer.parseInt(numberStr));
		}
		// Here We check if number is divisible by 1 million
		if ((number / 1000000) > 0) {
			words += convertNumberToWords(number / 1000000) + " million ";
			number %= 1000000;
		}
		// Here we are checking if number is divisible by 1 thousand
		if ((number / 1000) > 0) {
			words += convertNumberToWords(number / 1000) + " Thousand ";
			number %= 1000;
		}
		// Here we are checking if number is divisible by 1 hundred
		if ((number / 100) > 0) {
			words += convertNumberToWords(number / 100) + " Hundred ";
			number %= 100;
		}
		if (number > 0) {
			// check if number is within unitsArray
			if (number < 20) {
				//fetch the appropriate value from unit array
				words += unitsArray[(int) number];
			} else {
				// fetch the appropriate value from tens array
				words += tensArray[(int) (number / 10)];
				if ((number % 10) > 0) {
					words += "-" + unitsArray[(int) (number % 10)];
				}
			}
		}
		return words;
		
		
		 
	}}
