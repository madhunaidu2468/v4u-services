package com.mobifever.we4u.dao;

import java.util.List;

import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Helpline;

public interface HelplineDAO {

	public List<String> getALLHelpLines() throws We4UException;
	public List<String> getALLHelpLinesForLocation(String location) throws We4UException;
	public List<String> getALLHelpLinesForDisasterType(String disasterType) throws We4UException;
	Helpline getHelpLineDetails(int helplineId) throws We4UException;
}
