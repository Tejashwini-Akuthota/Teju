package com.New.LHS20.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.New.LHS20.Dao.BillRepositry;
import com.New.LHS20.Dao.PrescriptionRepository;
import com.New.LHS20.Dao.SupplimentsRepository;
import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Doctor_Prescription;
import com.New.LHS20.Entity.Suppliments;
import com.New.LHS20.Exception.RegistrationCustomException;

@Service
public class BillServiceImpl implements BillService {
	//pharmacist will calaculate all the medicines amount  
	
	
	@Autowired
	PrescriptionRepository prescriptionrepository;
	@Autowired
	BillRepositry billrepository;
	@Autowired
	SupplimentsRepository supplimentsRepository;
		 
		public Bill addBill(@RequestBody Bill bill) {
			long Totalamt = 0l;
			long Totalamt2 = 0l;
			List<Doctor_Prescription> bill2 = prescriptionrepository.findByPatient(bill.getPatient());
	 
			List<Bill> bill3 = billrepository.findByDoctorAndAppointdate(bill.getDoctor(), bill.getAppointmentdate(),
					bill.getPatient());
			System.err.println(bill3);
			Iterator iterator = bill2.iterator();
			while (iterator.hasNext()) {
				Doctor_Prescription doctor_Prescription = (Doctor_Prescription) iterator.next();
				Totalamt = (doctor_Prescription.getAmount() * doctor_Prescription.getQuantity());
				Totalamt2 = Totalamt+Totalamt2;
			}
			bill.setId(bill3.get(0).getId());
			bill.setMedicalEquipments(bill3.get(0).getMedicalEquipments());
			bill.setPharmacy(Totalamt2);
			Bill bi114=bill3.get(0);
			bill.setBill_date(bi114.getBill_date());
			bill.setBill_time(bi114.getBill_time());
			bill.setAppointmenttime(bi114.getAppointmenttime());
			bill.setDischargetime(bi114.getDischargetime());
			bill.setDischargedate(bi114.getDischargedate());
		
			
			return billrepository.save(bill);

		}
		
		
		


		public int addBillMedical(@RequestBody Bill bill) {
			Double Totalamt = 0d;
			Double Totalamt2 = 0d;
			List<Suppliments> bill2 = supplimentsRepository.findByPatient(bill.getPatient());
			Iterator iterator = bill2.iterator();
			List<Bill> bill3 =  billrepository.findByDoctorAndAppointdate(bill.getDoctor(), bill.getAppointmentdate(),
				
					bill.getPatient());
			System.out.println(bill3); 
			

			while (iterator.hasNext()) {
				
				Suppliments suppliments = (Suppliments) iterator.next();
				Totalamt = (double) (suppliments.getAmount() * Long.parseLong(suppliments.getQuantity()));
				System.err.println(suppliments.getAmount() * Long.parseLong(suppliments.getQuantity()));
				Totalamt2 = Totalamt + Totalamt2;
					}
		    Bill bill4	=bill3.get(0);
		    if(bill4.getPharmacy()<=0) {
		    	throw new RegistrationCustomException("abc", "abc");
		    	}
			Long Totalamt4 = bill4.getPharmacy()+bill4.getConsultations()+bill4.getConsumables()+bill4.getInvestigations()+bill4.getRoomrent()+Totalamt2.longValue();
			LocalDate date = LocalDate.now();
			LocalTime localTime=LocalTime.now();
			
			LocalDate dischargedate = LocalDate.now();
			LocalTime dischargetime=LocalTime.now();
	 
				 
			return billrepository.setBill(Totalamt4, Totalamt2.longValue(),date,localTime,dischargedate,dischargetime, bill.getDoctor(),bill.getPatient());
	 

		}



}
