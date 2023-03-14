package com.practise.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practise.Model.User;

@Service
public class UserService {
	
	private List<User> userList;
	
	public UserService() {
		userList = new ArrayList<>();
		User user1 = new User(1,"Teju",24,"teju@gmail.com");
		User user2 = new User(2,"Yugi",26,"yugi@gmail.com");
		User user3 = new User(3,"sujji",24,"sujji@gmail.com");
		User user4 = new User(4,"vidya",24,"vidya@gmail.com");
		User user5 = new User(5,"sindhu",24,"sindhu@gmail.com");
		
		userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
	}

	public Optional <User> getUser(Integer id) {
		Optional optional = Optional.empty();
		for( User user : userList) {
			if (id == user.getId()) {
				optional = Optional.of(user);
				return optional;
			}
			
		}
		return optional;
	}

}
