package com.mobifever.we4u.service;

import java.text.ParseException;
import java.util.List;

import com.mobifever.we4u.dto.DisasterDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Disaster;

public interface DisasterService {

	public String add(DisasterDTO disasterDto) throws We4UException, ParseException;

	public void update(DisasterDTO disasterDto) throws We4UException;

	public Disaster getDisasterDetails(int disasterId) throws We4UException;

	public List<Disaster> getDisasters() throws We4UException;

	public List<Disaster> getDisastersRequired(int id, String location,
			String disasterType) throws We4UException;

	public Disaster checkIfDisasterExists(DisasterDTO disasterDto) throws We4UException;

	public void addMemberToDisaster(Integer userId, int disasterId) throws We4UException;
}
