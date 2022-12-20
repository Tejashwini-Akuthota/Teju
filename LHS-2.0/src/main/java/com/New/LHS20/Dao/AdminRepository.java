package com.New.LHS20.Dao;

 
import java.util.Optional;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 
import com.New.LHS20.Entity.Authorities;
import com.New.LHS20.Entity.RegistrationForm;


@Repository
public interface AdminRepository extends JpaRepository<Authorities,Integer > {

	
  

 

	 
   
    
	
}
