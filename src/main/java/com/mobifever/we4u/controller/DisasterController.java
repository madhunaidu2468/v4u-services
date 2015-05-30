package com.mobifever.we4u.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobifever.we4u.constant.Constants;
import com.mobifever.we4u.constant.ControllerMapping;
import com.mobifever.we4u.dto.DisasterDTO;
import com.mobifever.we4u.model.Disaster;
import com.mobifever.we4u.service.DisasterService;
import com.mobifever.we4u.utils.DozerListMapping;
import com.mobifever.we4u.vo.CommonDataVO;
import com.mobifever.we4u.vo.StatusVO;

@Controller
@RequestMapping(ControllerMapping.DISASTER)
public class DisasterController {

	@Autowired
	private DisasterService disasterService;

	private StatusVO statusVO;

	private CommonDataVO commonDataVO;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CommonDataVO addDisaster(@RequestBody DisasterDTO disasterDto)
			throws Exception {
		String result = null;

		if (disasterDto != null) {
			result = disasterService.add(disasterDto);
		}
		if (result != null) {
			statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
					Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
					null);
		} else {
			statusVO = new StatusVO(Constants.ltc_FAILURECODE,
					Constants.ltc_RESPONSECODEFAILURE, result, null);
		}
		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public @ResponseBody CommonDataVO updateDisaster(
			@RequestBody DisasterDTO disasterDto) throws Exception {
		
			statusVO = new StatusVO("Not yet implemented",
					Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
					null);
		

		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody CommonDataVO deleteDisaster(
			@RequestBody DisasterDTO disasterDto) throws Exception {
		statusVO = new StatusVO("Not yet implemented",
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
				null);
	

	commonDataVO = new CommonDataVO(statusVO);
	return commonDataVO;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getDisasters() throws Exception {
		List<Disaster> disasters=new ArrayList<Disaster>();
		disasters=disasterService.getDisasters();
		List<DisasterDTO> disasterDtoList=DozerListMapping.mapList(disasters, DisasterDTO.class);
		
		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
				null);
		commonDataVO = new CommonDataVO(disasterDtoList,null,statusVO);
		
		
		return commonDataVO;
	}
	

	@RequestMapping(value = "/{disasterId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getDisasterDetails(
			@PathVariable(value = "disasterId") String disasterId) throws Exception {
		
		DisasterDTO disasterDTO=DozerListMapping.map(disasterService.getDisasterDetails(Integer.parseInt(disasterId)), DisasterDTO.class);
		
		
		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);
		commonDataVO = new CommonDataVO(null, disasterDTO, 
				statusVO);
		return commonDataVO;
	}
			
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getDisastersRequired(
			@ModelAttribute(value = "id") String id, @ModelAttribute(value = "location") String location,
			@ModelAttribute(value = "disasterType") String disasterType) throws Exception {
		
		List<Disaster> disasters=new ArrayList<Disaster>();
		int tempId=0;
		if(!id.isEmpty()){
		 tempId=Integer.parseInt(id);
		}
		disasters=disasterService.getDisastersRequired(tempId,location,disasterType);
		List<DisasterDTO> disasterDtoList=DozerListMapping.mapList(disasters, DisasterDTO.class);
				
		
		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);
		commonDataVO = new CommonDataVO(disasterDtoList, null,
				statusVO);
		return commonDataVO;
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
