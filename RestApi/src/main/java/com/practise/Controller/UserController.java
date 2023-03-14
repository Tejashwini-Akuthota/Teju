package com.practise.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practise.Model.User;
import com.practise.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/user")
	public User getUser(@RequestParam Integer id ) {
		Optional<User> user = userService.getUser(id);
		if(user.isPresent()) {
			return (User) user.get();
		}
		return null;
	}

}
