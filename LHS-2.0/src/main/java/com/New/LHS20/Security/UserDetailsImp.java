package com.New.LHS20.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.New.LHS20.Entity.Authorities;
import com.New.LHS20.Entity.RegistrationForm;


public class UserDetailsImp implements UserDetails {



RegistrationForm regform;



public UserDetailsImp(RegistrationForm regform) {
super();
this.regform =regform;
}



@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
List<Authorities> re=  regform.getRoles();
List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
for(Authorities r:re) {
	System.out.println(r.getRoleName());
	simpleGrantedAuthorities.add(new SimpleGrantedAuthority(r.getRoleName()));
}
return simpleGrantedAuthorities;
}



@Override
public String getPassword() {
// TODO Auto-generated method stub
return regform.getPassword();
}



@Override
public String getUsername() {
// TODO Auto-generated method stub
return regform.getUsername();
}



@Override
public boolean isAccountNonExpired() {
// TODO Auto-generated method stub
return true;
}



@Override
public boolean isAccountNonLocked() {
// TODO Auto-generated method stub
return true;
}



@Override
public boolean isCredentialsNonExpired() {
// TODO Auto-generated method stub
return true;
}



@Override
public boolean isEnabled() {
// TODO Auto-generated method stub
return true;
}



}