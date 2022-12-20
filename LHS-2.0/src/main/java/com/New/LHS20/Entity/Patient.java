package com.New.LHS20.Entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

 
@Entity
@Table(name = "patient")
@NoArgsConstructor(force = true)
@Data
@Component
@AllArgsConstructor
public class Patient 
{
 
	 

	@Id
	private long userId;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNo;
	private String dob;
	private String gender;

	private String username;

	private String password;

	private String roleName;




	     
	 }



