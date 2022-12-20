package com.New.LHS20.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "doctor")
@NoArgsConstructor(force = true)
@Data
@Component
public class Doctor{



   @Id 
   private long Id;

    @Column(name = "Doctor_First_Name")
   private String firstName;
    @Column(name = "Doctor_Last_Name")
   private String lastName;
    @Column(name = "Email_Id")
   private String email;
    @Column(name = "Phone_No")
    private String phoneNo;
    
    @Column(name = "Gender")
    private String gender;
    
    @Column(name = "DOB")
	private String dob;


//    @ManyToOne
//    @JoinColumn(name="Speciality", referencedColumnName="name")
    private String speciality;



   
   }





