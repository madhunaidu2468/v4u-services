package com.mobifever.we4u.service;

import java.text.ParseException;
import java.util.List;

import com.mobifever.we4u.dto.UserDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.User;

public interface UserService {

	public String add(UserDTO userDto) throws We4UException, ParseException;

	public void update(UserDTO userDto) throws We4UException;

	public User getUserDetails(int userId) throws We4UException;

	public List<User> getUsers() throws We4UException;

	public List<User> getUsersRequired(String location,
			String disasterType) throws We4UException;

	public List<String> getEmergencyContactsOfUser(int userId, String phoneNumber) throws We4UException;

	User checkUserExists(String phoneNumber) throws We4UException;
}
