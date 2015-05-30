package com.mobifever.we4u.service;

import java.util.List;

import com.mobifever.we4u.dto.CasualityDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Casuality;

public interface CasualityService {

	public String add(CasualityDTO casualityDto) throws We4UException;

	public void update(CasualityDTO casualityDto) throws We4UException;

	public Casuality getCasualityDetails(int casualityId) throws We4UException;

	public List<Casuality> getCasualitys() throws We4UException;

	public List<Casuality> getCasualitysRequired(int casualityId, String casualityName, String location,
			String disasterType) throws We4UException;
}
