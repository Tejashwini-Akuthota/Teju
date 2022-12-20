package com.New.LHS20.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long bloodPressure;
	private String temperature;
	private int weight;
	private String height;
	private String HeartRate;
	
	
	@OneToOne
	@JoinColumn(name="patId",referencedColumnName = "userId")
	 private Patient patient;
	

}
