package com.mobifever.we4u.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifever.we4u.constant.ServiceErrors;
import com.mobifever.we4u.dao.DisasterDAO;
import com.mobifever.we4u.dto.DisasterDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Disaster;
import com.mobifever.we4u.service.DisasterService;
import com.mobifever.we4u.utils.DozerListMapping;

@Service
public class DisasterServiceImpl implements DisasterService {


	@Autowired
	private DisasterDAO disasterDao;

	@Override
	public String add(DisasterDTO disasterDto) throws We4UException {
		int tempDisasterId = disasterDao.getDisasterId();
		Disaster disaster = DozerListMapping.map(disasterDto, Disaster.class);
		//Mapper mapper = new DozerBeanMapper();
		//mapper.map(disasterDto, disaster);
		if (disasterDto.getDisasterId() != 0) {
			throw new We4UException(ServiceErrors.ltc_DISASTERIDNOTNULL);
		}
		disaster.setDisasterId(tempDisasterId);
		return disasterDao.add(disaster);
	}

	@Override
	public void update(DisasterDTO disaster) throws We4UException {
		// TODO Auto-generated method stub

	}

	@Override
	public Disaster getDisasterDetails(int disasterId) throws We4UException {
		// TODO Auto-generated method stub
		return disasterDao.getDisasterDetails(disasterId);
	}

	@Override
	public List<Disaster> getDisasters() throws We4UException {
		// TODO Auto-generated method stub
		return disasterDao.getDisasters();
	}

	@Override
	public List<Disaster> getDisastersRequired(int id, String location,
			String disasterType) throws We4UException {
		// TODO Auto-generated method stub
		return disasterDao.getDisastersRequired(id, location, disasterType);
	}

	
	
}
