package com.mobifever.we4u.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mobifever.we4u.dao.HelplineDAO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Helpline;

@Repository
public class HelplineDaoImpl extends BaseDaoImpl implements HelplineDAO {

	@SuppressWarnings("rawtypes")
	public String getCollectionName() {
		return "helpline";
	}

	@SuppressWarnings("rawtypes")
	public Class getEntityClass() {
		return Helpline.class;
	}

	@Override
	public List<String> getALLHelpLines() throws We4UException {
		Query searchQuery = new Query();
		return loadAll(searchQuery);
	}

	@Override
	public List<String> getALLHelpLinesForLocation(String location)
			throws We4UException {
		Query searchQuery = new Query();
		location = location.trim();	
		if (!location.isEmpty()) {
			searchQuery.addCriteria(Criteria.where("location").is(location));
		}
		return loadAll(searchQuery);
	}

	@Override
	public List<String> getALLHelpLinesForDisasterType(String disasterType)
			throws We4UException {
		Query searchQuery = new Query();
		disasterType = disasterType.trim();
		if (!disasterType.isEmpty()) {
			searchQuery.addCriteria(Criteria.where("disasterType").is(
					disasterType));
		}
		return loadAll(searchQuery);
	}


	@Override
	public Helpline getHelpLineDetails(int helplineId) throws We4UException {
		Query searchQuery = new Query(Criteria.where("helplineId").is(
				helplineId));

		return (Helpline) load(searchQuery);
	
	}
}
