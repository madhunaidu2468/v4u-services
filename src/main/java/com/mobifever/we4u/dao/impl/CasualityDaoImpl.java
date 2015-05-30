package com.mobifever.we4u.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mobifever.we4u.dao.CasualityDAO;
import com.mobifever.we4u.dto.CasualityDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Casuality;
import com.mobifever.we4u.model.Disaster;

@Repository
public class CasualityDaoImpl extends BaseDaoImpl  implements CasualityDAO {

	@SuppressWarnings("rawtypes")
	public String getCollectionName() {
		return "casuality";
	}

	@SuppressWarnings("rawtypes")
	public Class getEntityClass() {
		return Casuality.class;
	}
	

	@Override
	public String add(Casuality casuality) throws We4UException {
		String result=null;
		try {
			save(casuality);
			result = casuality.getCasualityId().toString();
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
		return result;
	}

	@Override
	public void update(Casuality casuality) throws We4UException {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getCasualityId() throws We4UException {
		return getId("casualityId");
	}

	@Override
	public Casuality getCasualityDetails(int casualityId)
			throws We4UException {
		Query searchQuery = new Query(Criteria.where("casualityId").is(casualityId));	

		try {
			return (Casuality) load(searchQuery);
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
	}

	@Override
	public List<Casuality> getCasualitys() throws We4UException {
		Query searchQuery = new Query();	
		try {
			return loadAll(searchQuery);
		} catch (Exception e) {
			throw new We4UException(e.getMessage());
		}
	}

	@Override
	public List<Casuality> getCasualitysRequired(int casualityId,int disasterId,
			String personName, String location,
			String disasterType) throws We4UException {
		Query searchQuery = new Query();
		personName=personName.trim();
		disasterType=disasterType.trim();
		location=location.trim();
		if(casualityId != 0){
		searchQuery.addCriteria(Criteria.where("casualityId").is(casualityId));
		}
		if(disasterId != 0){
			searchQuery.addCriteria(Criteria.where("disasterId").is(disasterId));
			}
		if(!personName.isEmpty()){
			searchQuery.addCriteria(Criteria.where("personName").is(personName));
			}
		if(!location.isEmpty()){
			searchQuery.addCriteria(Criteria.where("myLocation").is(location));
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
