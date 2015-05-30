package com.mobifever.we4u.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mobifever.we4u.constant.Constants;
import com.mobifever.we4u.dao.HelplineDAO;
import com.mobifever.we4u.dto.CasualityDTO;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.service.CasualityService;
import com.mobifever.we4u.service.SMSService;
import com.mobifever.we4u.service.impl.SMSBean;
import com.mobifever.we4u.vo.CommonDataVO;
import com.mobifever.we4u.vo.StatusVO;

@Controller
@RequestMapping(value = "/")
public class SMSController {

	@Autowired
	SMSService smsService;
	
	@Autowired
	HelplineDAO helpLineDao;

	@Autowired
	private CasualityService casualityService;
	
	private StatusVO statusVO;

	private CommonDataVO commonDataVO;

	@RequestMapping(value = "/sms", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody CommonDataVO sendSMS(@RequestBody SMSBean sms)
			throws We4UException, IOException {
		JSONObject statusJSON = new JSONObject();
		String response = smsService.sendSMS(sms);
		if (response != null) {
			statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
					Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
					null);
		} else {
			statusVO = new StatusVO(Constants.ltc_FAILURECODE,
					Constants.ltc_RESPONSECODEFAILURE, response, null);
		}
		commonDataVO = new CommonDataVO(statusVO);

		return commonDataVO;
	}

	@RequestMapping(value = "/smsgatewayhub", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO receiveSMS(
			@ModelAttribute(value = "who") String who,
			@ModelAttribute(value = "what") String what) throws We4UException, NumberFormatException, ParseException, IOException {
		addCasualityViaSMS(who, what);

		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);

		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}

	@RequestMapping(value = "/smshorizon", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO receiveSMS1(
			@ModelAttribute(value = "from") String from,
			@ModelAttribute(value = "message") String message)
			throws We4UException, NumberFormatException, ParseException, IOException {
		addCasualityViaSMS(from, message);
		
		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);

		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}

	public void addCasualityViaSMS(String from, String message) throws NumberFormatException, We4UException, ParseException, IOException {
		SMSBean smsBean;
		try{
		CasualityDTO casualityDTO=new Gson().fromJson(message, CasualityDTO.class);
		casualityDTO.setPhoneNumber(from);
		casualityService.add(casualityDTO);
		List<String> helplineNumbers=helpLineDao.getALLHelpLinesForLocation(casualityDTO.getMyLocation());
		List toList=new ArrayList<String>();
		toList.add(from);
		smsBean=new SMSBean(toList,helplineNumbers.toString(),"smshorizon");
		smsService.sendSMS(smsBean);
		}catch(Exception e){
			CasualityDTO casualityDTO=new CasualityDTO();
			String[] messageDetails=message.split(" ");
			casualityDTO.setPersonName(messageDetails[0]);
			casualityDTO.setMyLocation(messageDetails[1]);
			casualityDTO.setDisasterType(messageDetails[2]);	
			casualityDTO.setCasualityId(0);
			casualityDTO.setPhoneNumber(from);
			casualityService.add(casualityDTO);
			List<String> helplineNumbers=helpLineDao.getALLHelpLinesForLocation(casualityDTO.getMyLocation());
			List toList=new ArrayList<String>();
			toList.add(from);
			smsBean=new SMSBean(toList,"Helpline Number: "+helplineNumbers.toString().replace("[", "").replace("]", ""),"smshorizon");
			smsService.sendSMS(smsBean);
		}
	}

	@ExceptionHandler(Exception.class)
	public @ResponseBody CommonDataVO handleException(Exception ex,
			HttpServletRequest request, HttpServletResponse response) {

		System.out.println(ex);

		statusVO = new StatusVO(Constants.ltc_FAILURECODE,
				Constants.ltc_RESPONSECODEFAILURE, ex.getMessage(), null);

		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}

}
