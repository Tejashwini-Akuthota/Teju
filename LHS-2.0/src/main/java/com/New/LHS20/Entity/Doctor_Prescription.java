package com.New.LHS20.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Doctor_Prescription {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String medicineName;
	private String dosage;
	private int quantity;
	private String duration;
	private long amount;
	private String investigations;
	
	

	@OneToOne
	@JoinColumn(name="patId",referencedColumnName = "userId")
	 private Patient patient;
	
	@ManyToOne
	 @JoinColumn(name="doc_id",referencedColumnName = "Id")
	 private Doctor doctor;
	
	

}
