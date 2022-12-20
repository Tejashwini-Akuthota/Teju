package com.New.LHS20.Dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RegistrationFormUpdateDto {

	@NotNull
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
    
    
  
}
