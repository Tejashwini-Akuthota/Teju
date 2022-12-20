package com.New.LHS20.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
 
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 

 
@Entity
@Data
 
@NoArgsConstructor
@AllArgsConstructor
public class Authorities {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
	private String roleName;
	
 
	
	 
 
	
	
	
	
	
}
