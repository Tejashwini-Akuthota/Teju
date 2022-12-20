package com.New.LHS20.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Suppliments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private  long id;
	 private  String name;
	 private String Quantity;
	 
	 
	 @OneToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="patId",referencedColumnName = "userId")
	 private Patient patient;
	 
	 private long amount;

}
