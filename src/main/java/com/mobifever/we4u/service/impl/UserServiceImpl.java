package com.mobifever.we4u.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifever.we4u.constant.ServiceErrors;
import com.mobifever.we4u.dao.UserDAO;
import com.mobifever.we4u.dto.UserDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.User;
import com.mobifever.we4u.service.UserService;
import com.mobifever.we4u.utils.DozerListMapping;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	@Override
	public String add(UserDTO userDto) throws We4UException, ParseException {
		int tempUserId = userDao.getUserId();
		User user = DozerListMapping.map(userDto, User.class);
		//Mapper mapper = new DozerBeanMapper();
		//mapper.map(userDto, user);
		if (userDto.getUserId() != 0) {
			throw new We4UException(ServiceErrors.ltc_DISASTERIDNOTNULL);
		}
		user.setUserId(tempUserId);
		
		return userDao.add(user);
	}

	@Override
	public void update(UserDTO user) throws We4UException {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserDetails(int userId) throws We4UException {
		// TODO Auto-generated method stub
		return userDao.getUserDetails(userId);
	}
	
	@Override
	public User checkUserExists(String phoneNumber) throws We4UException {
		return userDao.checkUserExists(phoneNumber);
	}

	@Override
	public List<User> getUsers() throws We4UException {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}

	@Override
	public List<User> getUsersRequired(String location, String disasterType) throws We4UException {
		// TODO Auto-generated method stub
		return userDao.getUsersRequired( location,disasterType);
	}

	@Override
	public List<String> getEmergencyContactsOfUser(int userId,
			String phoneNumber) throws We4UException {
		return userDao.getEmergencyContactsOfUser(userId,phoneNumber);
	}

}
