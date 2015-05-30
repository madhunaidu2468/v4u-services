package com.mobifever.we4u.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mobifever.we4u.dao.DisasterDAO;
import com.mobifever.we4u.dto.DisasterDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Disaster;

@Repository
public class DisasterDaoImpl extends BaseDaoImpl implements DisasterDAO {

	@SuppressWarnings("rawtypes")
	public String getCollectionName() {
		return "disaster";
	}

	@SuppressWarnings("rawtypes")
	public Class getEntityClass() {
		return Disaster.class;
	}
	
	@Override
	public String add(Disaster disaster) throws We4UException {
		String result=null;
		try {
			save(disaster);
			result = disaster.getDisasterId().toString();
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
		return result;
	}

	@Override
	public void update(Disaster disaster) throws We4UException {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getDisasterId() throws We4UException {
		return getId("disasterId");
	}

	@Override
	public Disaster getDisasterDetails(int disasterId) throws We4UException {
		Query searchQuery = new Query(Criteria.where("disasterId").is(disasterId));	

		try {
			return (Disaster) load(searchQuery);
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
	}

	@Override
	public List<Disaster> getDisasters() throws We4UException {
		Query searchQuery = new Query();	
		try {
			return loadAll(searchQuery);
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
	}

	@Override
	public List<Disaster> getDisastersRequired(long id, String location,
			String disasterType) throws We4UException {
		Query searchQuery = new Query();
		location=location.trim();
		disasterType=disasterType.trim();
		if(id != 0){
		searchQuery.addCriteria(Criteria.where("disasterId").is(id));
		}if(!location.isEmpty()){
		searchQuery.addCriteria(Criteria.where("location").is(location));
		}
		if(!disasterType.isEmpty()){
			searchQuery.addCriteria(Criteria.where("disasterType").is(disasterType));
			}
		try {
			return loadAll(searchQuery);
				
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
	}

}
