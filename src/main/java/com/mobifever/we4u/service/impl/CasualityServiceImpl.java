package com.mobifever.we4u.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifever.we4u.constant.ServiceErrors;
import com.mobifever.we4u.dao.CasualityDAO;
import com.mobifever.we4u.dto.CasualityDTO;
import com.mobifever.we4u.dto.DisasterDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Casuality;
import com.mobifever.we4u.model.Disaster;
import com.mobifever.we4u.service.CasualityService;
import com.mobifever.we4u.service.DisasterService;
import com.mobifever.we4u.utils.DozerListMapping;

@Service
public class CasualityServiceImpl implements CasualityService {

	@Autowired
	private CasualityDAO casualityDao;
	
	@Autowired
	private DisasterService disasterService;

	@Override
	public String add(CasualityDTO casualityDto) throws We4UException, NumberFormatException, ParseException {
		int tempCasualityId = casualityDao.getCasualityId();
		Casuality casuality = DozerListMapping.map(casualityDto, Casuality.class);
		//Mapper mapper = new DozerBeanMapper();
		//mapper.map(disasterDto, disaster);
		if (casualityDto.getCasualityId() != 0) {
			throw new We4UException(ServiceErrors.ltc_CASUALITYINVALID);
		}
		casuality.setCasualityId(tempCasualityId);
		DisasterDTO disasterDto=new DisasterDTO();
		disasterDto.setDisasterType(casualityDto.getDisasterType());
		disasterDto.setLocation(casualityDto.getMyLocation());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();
		Date d=formatter.parse(cal.getTime().toString());
		disasterDto.setTime(d.getTime());
		disasterDto.setTime(casualityDto.getTime());
		Disaster disaster=disasterService.checkIfDisasterExists(disasterDto);
		if(disaster != null){
			casuality.setDisasterId(disaster.getDisasterId());
		}else{
			casuality.setDisasterId(Integer.parseInt(disasterService.add(disasterDto)));
		}
		return casualityDao.add(casuality);
	}

	@Override
	public void update(CasualityDTO casuality) throws We4UException {
		// TODO Auto-generated method stub

	}

	@Override
	public Casuality getCasualityDetails(int casualityId) throws We4UException {
		// TODO Auto-generated method stub
		return casualityDao.getCasualityDetails(casualityId);
	}

	@Override
	public List<Casuality> getCasualitys() throws We4UException {
		// TODO Auto-generated method stub
		return casualityDao.getCasualitys();
	}

	@Override
	public List<Casuality> getCasualitysRequired(int casualityId,int disasterId,
			String personName, String location,
			String disasterType) throws We4UException {
		// TODO Auto-generated method stub
		return casualityDao.getCasualitysRequired(casualityId,disasterId,  personName, location, disasterType);
	}

}
