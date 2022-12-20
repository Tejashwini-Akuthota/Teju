package com.New.LHS20.TestSecurity;



import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@Order(1)
@EnableWebSecurity
public class TestSecurityConfig extends WebSecurityConfigurerAdapter{



   @Override
    public void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable();    
        
        http.authorizeRequests().anyRequest().permitAll();
        }
}