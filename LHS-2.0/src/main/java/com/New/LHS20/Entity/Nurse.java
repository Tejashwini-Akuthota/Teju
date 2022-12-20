package com.New.LHS20.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nurse {
	
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
