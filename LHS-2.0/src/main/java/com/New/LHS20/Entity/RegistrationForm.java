package com.New.LHS20.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegistrationForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	@NotNull
	@Size(min = 2, message = "firstname should have at least 2 characters")
	private String firstName;

	private String lastName;

	@NotNull
	@Size(min = 2, message = "Please enter EmailId")
	@Email
	private String email;

	
	@NotNull
    @Size(min = 12, message = "Please enter valid phone number")
	private String phoneNo;
	
	
	@NotNull 
    @Size(min = 6,message = "Please enter your Date of Birth") 
	private String dob;
	
    @NotNull 
	@Size(min = 4,message = "Please select your Gender")  
	private String gender;
    
    
    @Column(unique = true)
    @NotNull
	private String username;

    @NotNull
    @Size(min = 8,message = "Please enter password with minimum 8 characters")
	private String password;

	private String roleName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Registrationform_Authorities", joinColumns = @JoinColumn(name = "reg_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Authorities> roles = new ArrayList<>();
	
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name="Date",referencedColumnName = "Date")
//	SlotTime2 slotTime2;
	
	
}
