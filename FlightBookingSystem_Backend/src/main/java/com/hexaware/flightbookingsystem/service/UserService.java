package com.hexaware.flightbookingsystem.service;

import java.util.Collection;

import com.hexaware.flightbookingsystem.bean.Login;
import com.hexaware.flightbookingsystem.entity.User;
import com.hexaware.flightbookingsystem.exception.UserException;

public interface UserService {
	
	 int createUser(User user) throws UserException;
	
	 User fetchUserById(int user_id) throws UserException;
	 
	 User validate(Login login);
	
	 public Collection<User> fetchAllUsers();
	
}
