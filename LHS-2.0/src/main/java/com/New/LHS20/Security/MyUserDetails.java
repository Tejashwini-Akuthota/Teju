package com.New.LHS20.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.Entity.RegistrationForm;
@Service
public class MyUserDetails implements UserDetailsService {


   
@Autowired
RegistrationRepository repo;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  
	
	//System.out.println();
RegistrationForm regform=  repo.findByUsername(username);
System.out.println(regform);

if(regform==null)
{
throw new RuntimeException("exception raised in my userdetails");
}

return new UserDetailsImp(regform);


}



}