package com.mobifever.we4u.dao;

import java.util.List;

import com.mobifever.we4u.dto.CasualityDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Casuality;

public interface CasualityDAO {


	public String add(Casuality casuality) throws We4UException;

	public void update(Casuality casuality) throws We4UException;

	public Integer getCasualityId() throws We4UException;

	public Casuality getCasualityDetails(int casualityId) throws We4UException;

	public List<Casuality> getCasualitys() throws We4UException;

	public List<Casuality> getCasualitysRequired(int casualityId, int disasterId, String personName, String location,
			String disasterType) throws We4UException;
}
