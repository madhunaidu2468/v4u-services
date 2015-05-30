package com.mobifever.we4u.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobifever.we4u.constant.ServiceErrors;
import com.mobifever.we4u.dao.CasualityDAO;
import com.mobifever.we4u.dao.DisasterDAO;
import com.mobifever.we4u.dao.HelplineDAO;
import com.mobifever.we4u.dto.CasualityDTO;
import com.mobifever.we4u.dto.DisasterDTO;
import com.mobifever.we4u.dto.UserDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.model.Casuality;
import com.mobifever.we4u.model.Disaster;
import com.mobifever.we4u.model.User;
import com.mobifever.we4u.service.CasualityService;
import com.mobifever.we4u.service.DisasterService;
import com.mobifever.we4u.service.UserService;
import com.mobifever.we4u.utils.DozerListMapping;

@Service
public class CasualityServiceImpl implements CasualityService {

	@Autowired
	private CasualityDAO casualityDao;
	
	@Autowired
	private DisasterService disasterService;

	@Autowired
	private UserService userService;
	
	@Autowired
	HelplineDAO helpLineDao;
	
	@Autowired
	DisasterDAO disasterDao;
	
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
		Date today = new Date();
		Date todayWithZeroTime =formatter.parse(formatter.format(today));
		casualityDto.setTime(todayWithZeroTime.getTime());
		disasterDto.setTime(casualityDto.getTime());
		Disaster disaster=disasterService.checkIfDisasterExists(disasterDto);
		int disId=0;
		if(disaster != null){
			casuality.setDisasterId(disaster.getDisasterId());
			disId=disaster.getDisasterId();
			disasterService.update(disaster);
		}else{
			disasterDto.setDisasterId(0);
			disasterDto.setHelplineNumbers(helpLineDao.getALLHelpLinesForLocation(casualityDto.getMyLocation()));
			disasterDto.setNumberOfCasualities(1);
			disId=Integer.parseInt(disasterService.add(disasterDto));
			casuality.setDisasterId(disId);
		}
		addUserToDisaster(casualityDto,disId);
		
		return casualityDao.add(casuality);
	}

	public void addUserToDisaster(CasualityDTO casualityDto, int disasterId) throws We4UException, ParseException{
		User user=userService.checkUserExists(casualityDto.getPhoneNumber());
		if(user != null){
			disasterService.addMemberToDisaster(user.getUserId(),disasterId);
		}else{
			UserDTO userDto=new UserDTO();
			userDto.setUserId(0);
			userDto.setPhoneNumber(casualityDto.getPhoneNumber());
			userDto.setLocation(casualityDto.getMyLocation());
			userDto.setName(casualityDto.getPersonName());
			String userId=userService.add(userDto);
			disasterService.addMemberToDisaster(Integer.parseInt(userId),disasterId);
		}
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
