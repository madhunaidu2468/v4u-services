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
			save(disaster);
			result = disaster.getDisasterId().toString();
		
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

			return (Disaster) load(searchQuery);
		
	}

	@Override
	public List<Disaster> getDisasters() throws We4UException {
		Query searchQuery = new Query();	
			return loadAll(searchQuery);
		
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
			return loadAll(searchQuery);
		
	}

	@Override
	public Disaster checkIfDisasterExists(DisasterDTO disasterDto) throws We4UException {
		Query searchQuery = new Query();
		if(!disasterDto.getLocation().isEmpty()){
		searchQuery.addCriteria(Criteria.where("location").is(disasterDto.getLocation()));
		}
		if(!disasterDto.getDisasterType().isEmpty()){
			searchQuery.addCriteria(Criteria.where("disasterType").is(disasterDto.getDisasterType()));
			}
		if(disasterDto.getTime() != 0){
			searchQuery.addCriteria(Criteria.where("time").is(disasterDto.getTime()));
			}
			return (Disaster) load(searchQuery);
			
	}
	
	

}
