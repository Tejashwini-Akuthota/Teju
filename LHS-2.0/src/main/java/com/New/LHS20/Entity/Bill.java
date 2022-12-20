package com.New.LHS20.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate bill_date;
	private LocalTime bill_time;
	private String appointmentdate;
	private String appointmenttime;
	private LocalDate dischargedate;
	private LocalTime dischargetime;
	private long pharmacy;
	private long investigations;
	private long roomrent;
	private long medicalEquipments;
	private long consultations;
	private long consumables;
	private long total;

	@OneToOne
	@JoinColumn(name = "patId", referencedColumnName = "userId")
	private Patient patient;

	@OneToOne
	@JoinColumn(name = "doc_id", referencedColumnName = "Id")
	private Doctor doctor;

}
