package com.mobifever.we4u.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mobifever.we4u.dao.UserDAO;
import com.mobifever.we4u.dto.UserDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDAO {

	@SuppressWarnings("rawtypes")
	public String getCollectionName() {
		return "user";
	}

	@SuppressWarnings("rawtypes")
	public Class getEntityClass() {
		return User.class;
	}
	
	@Override
	public String add(User user) throws We4UException {
		String result=null;
			save(user);
			result = user.getUserId().toString();
		
		return result;
	}

	@Override
	public void update(User user) throws We4UException {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getUserId() throws We4UException {
		return getId("userId");
	}

	@Override
	public User getUserDetails(int userId) throws We4UException {
		Query searchQuery = new Query(Criteria.where("userId").is(userId));	

			return (User) load(searchQuery);
		
	}

	@Override
	public List<User> getUsers() throws We4UException {
		Query searchQuery = new Query();	
			return loadAll(searchQuery);
		
	}

	@Override
	public List<User> getUsersRequired(String location,String disasterType) throws We4UException {
		Query searchQuery = new Query();
		location=location.trim();
		disasterType=disasterType.trim();
		if(!location.isEmpty()){
		searchQuery.addCriteria(Criteria.where("location").is(location));
		}
		
			return loadAll(searchQuery);
		
	}

	@Override
	public List<String> getEmergencyContactsOfUser(int userId,
			String phoneNumber) throws We4UException {
		Query searchQuery = new Query();
		phoneNumber=phoneNumber.trim();
		if(userId != 0){
		searchQuery.addCriteria(Criteria.where("userId").is(userId));
		}else if(!phoneNumber.isEmpty()){
			searchQuery.addCriteria(Criteria.where("phoneNumber").is(phoneNumber));
		}
		User user=(User)load(searchQuery);
		return user.getEmergencyNumbers();
	}

	@Override
	public User checkUserExists(String phoneNumber) throws We4UException {
		Query searchQuery = new Query();
		phoneNumber=phoneNumber.trim();
		if(!phoneNumber.isEmpty()){
			searchQuery.addCriteria(Criteria.where("phoneNumber").is(phoneNumber));
		}
		return (User)load(searchQuery);
	}

	
}
