package com.New.LHS20.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String  policies;
	private LocalDate  date;
	private LocalTime  time;

}
