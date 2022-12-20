package com.New.LHS20.Entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity		

public class SlotTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String date;
	private String startTime;
	private String endTime;	
//	@OneToOne(cascade = CascadeType.ALL)
//	private BookSlot bookSlot;
	private Long patientId;
	
	private Long doctorId;
	
	private String status;	
    
	private String disease;
	
}
