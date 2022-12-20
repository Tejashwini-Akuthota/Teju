package com.New.LHS20.Service;

import java.io.File;
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
import com.New.LHS20.Entity.MonitoringData;
import com.New.LHS20.Entity.Patient;
import com.New.LHS20.Entity.RegistrationForm;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Header;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import io.jsonwebtoken.io.IOException;

@Service
public class PdfServiceImpl implements  PdfService {

	
	@Autowired
	BillRepositry billrepository;
	
	@Autowired
	DoctorPrescriptionRepository prescrepo;
	@Autowired
	RegistrationRepository  regrepo;
	
	@Autowired
	MonitoringDataRepository monitoringdatarepository;
	
	public void export(HttpServletResponse response, Doctor_Prescription doctorpresc)  throws IOException, DocumentException, java.io.IOException   {
		List<Doctor_Prescription> doctorprescList=  prescrepo.findByPatient(doctorpresc.getPatient());
		Doctor_Prescription doctorpresc1 =doctorprescList.get(0);
	   System.out.println(doctorprescList);
	   Patient pat = doctorpresc1.getPatient();
		                              
	    RegistrationForm regform =regrepo.findByUsername(pat.getUsername());
		Doctor doctor=doctorpresc1.getDoctor();
//		MyCartList cartList=ordersEntityList.getCartList();
 		Document document = new Document(PageSize.A4);
	  PdfWriter.getInstance(document, response.getOutputStream()); 	
	  Image image= Image.getInstance("lhslogo2.jpg");
      image.scaleAbsolute(120,40);
      image.setAlignment(50);
	
       document.open();
              Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);
		fontTitle.setColor(144,238,144);
//	    Header header = new Header("LIFE LINE HEALTH CARE", "0");
//		Paragraph paragraph = new Paragraph("Doctor_Prescription #6006922 for Order # BPFVO­6012102­211215 : omg.com");
	    Paragraph paragraph = new Paragraph("Prescription",fontTitle);
	    paragraph.setAlignment(Paragraph.ALIGN_CENTER);
	
	                    
		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);
		 fontParagraph.setColor(0, 0, 255);
		 
	Paragraph paragraph2 = new Paragraph("LIFE LINE HEALTH CARE", fontTitle);
//	Paragraph paragraph4 = new Paragraph("DoctorDetails:",fontParagraph1)
	
	
	Font fontParagraph1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	fontParagraph1.setSize(14);
	 fontParagraph1.setColor(100, 0, 60);
	 
	 
		paragraph2.setSpacingAfter(3f);
	paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
		Paragraph paragraph4 = new Paragraph("Doctor Details:",fontParagraph1);
Paragraph paragraph6 = new Paragraph(doctor.getFirstName());
Paragraph paragraph7 = new Paragraph( doctor.getEmail());
Paragraph paragraph8= new Paragraph( doctor.getSpeciality());



Paragraph paragraph01 = new Paragraph("Patient Details",fontParagraph1);
                   paragraph01.setAlignment(Paragraph.ALIGN_CENTER);
                
//		        Paragraph paragraph7 = new Paragraph(addressEntity.getState()+","+addressEntity.getPincode());
		Paragraph paragraph3 = new Paragraph("Gender");
		Paragraph paragraph5 = new Paragraph("DOB");
		Paragraph paragraph9 = new Paragraph("PhoneNo");
	Table table = new Table(2, 6);
		table.setAlignment(5);
		table.setBorder(3);
		table.setPadding(3);
		Cell cell = new Cell("Patient Name");
	    table.addCell(cell);
		 table.addCell(String.valueOf(pat.getFirstName()));
		 table.addCell(paragraph3);
		table.addCell(String.valueOf(pat.getGender()));
		 table.addCell(paragraph5);
		table.addCell(String.valueOf(pat.getDob()));
		table.addCell(paragraph9);
		table.addCell(String.valueOf(pat.getPhoneNo()));
		
 
	   

    document.add(paragraph);
    
    document.add(paragraph2);
    document.add(image);
//    document.add(image);
    document.add(paragraph4);
    
    document.add(paragraph6);
    document.add(paragraph7);
    document.add(paragraph8);
    document.add(paragraph01);
    document.add(table);
    
    
    
    //Adding table for Doctor prescription
	Paragraph paragraph10 = new Paragraph("Medication", fontParagraph1);
	
	paragraph10.setAlignment(Paragraph.ALIGN_CENTER);
	Paragraph paragraph22 = new Paragraph("Medicine Name",fontParagraph1 );
	Paragraph paragraph11 = new Paragraph("Quantity",fontParagraph1 );
	Paragraph paragraph12 = new Paragraph("Duration",fontParagraph1 );
	Paragraph paragraph13 = new Paragraph("Dosage",fontParagraph1 );
 
	Paragraph paragraph23 = new Paragraph("Medicine Name",fontParagraph1 );
	
    Table table1 = new Table(4,5);
	table1.setAlignment(6);
	table1.setBorder(3);
	table1.setPadding(3);
//    table1.addCell("Medicine Name");
	table1.addCell(paragraph23);
	table1.addCell(paragraph11);
	table1.addCell(paragraph13);
	table1.addCell(paragraph12);
 
 
 

	int i=0;
	for( Doctor_Prescription list1:doctorprescList) {
		
		table1.addCell(list1.getMedicineName());
		table1.addCell(String.valueOf(list1.getQuantity()));
		table1.addCell(list1.getDosage());
		table1.addCell(list1.getDuration());
 
	}
	
	document.add(paragraph10);
	document.add(table1);
	
	
	//Adding table for MonitoringData
Paragraph paragraph14 = new Paragraph("Health Status", fontParagraph1);
	
	paragraph14.setAlignment(Paragraph.ALIGN_CENTER);
	Paragraph paragraph15 = new Paragraph("Heart rate",fontParagraph1 );
	Paragraph paragraph16 = new Paragraph("Blood pressure",fontParagraph1 );
	Paragraph paragraph17 = new Paragraph("Height",fontParagraph1 );
	Paragraph paragraph18 = new Paragraph("Temperature",fontParagraph1 );
	Paragraph paragraph19 = new Paragraph("Weight",fontParagraph1 );
 
	
    Table table2 = new Table(5,5);
	table2.setAlignment(6);
	table2.setBorder(3);
	table2.setPadding(3);
    table2.addCell(paragraph15);
    table2.addCell(paragraph16);
	table2.addCell(paragraph17);
	table2.addCell(paragraph18);
	table2.addCell(paragraph19);
	
	MonitoringData monitoringdata =new MonitoringData();
//	table2.addCell(monitoringdata.getHeartRate());
	List<MonitoringData>  monitoringdata1 =monitoringdatarepository.findByPatient(doctorpresc.getPatient());
	                      
	int i1=0;
	for( MonitoringData data:monitoringdata1) {
		
		table2.addCell(data.getHeartRate());
		System.out.println(data.getHeartRate());
		table2.addCell(String.valueOf(data.getBloodPressure()));
		table2.addCell(data.getHeight());
		table2.addCell(data.getTemperature());
		table2.addCell(String.valueOf(data.getWeight()));
         
	}
	
	 
    document.add(paragraph14);
	document.add(table2);
	
	
	Paragraph paragraph20 = new Paragraph("               "+"Investigations:"+doctorpresc1.getInvestigations(), fontParagraph1);
	Paragraph paragraph21 = new Paragraph("               "+"FollowUp Visit:"+ " "+"After 1 week", fontParagraph1);
	 
	
	document.add(paragraph20);
	document.add(paragraph21);
	
	document.close();
	 
	 
	 
	 
	 
	 
	 
	 
	 
		
	}



	 
 

	
}

