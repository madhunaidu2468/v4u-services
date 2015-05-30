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
import com.mobifever.we4u.dto.CasualityDTO;
import com.mobifever.we4u.model.Casuality;
import com.mobifever.we4u.service.CasualityService;
import com.mobifever.we4u.service.CasualityService;
import com.mobifever.we4u.utils.DozerListMapping;
import com.mobifever.we4u.vo.CommonDataVO;
import com.mobifever.we4u.vo.StatusVO;

@Controller
@RequestMapping(ControllerMapping.CASUALITY)
public class CasualityController {

	@Autowired
	private CasualityService casualityService;

	private StatusVO statusVO;

	private CommonDataVO commonDataVO;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CommonDataVO addCasuality(@RequestBody CasualityDTO casualityDto)
			throws Exception {
		String result = null;

		if (casualityDto != null) {
			result = casualityService.add(casualityDto);
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
	public @ResponseBody CommonDataVO updateCasuality(
			@RequestBody CasualityDTO casualityDto) throws Exception {
		
			statusVO = new StatusVO("Not yet implemented",
					Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
					null);
		

		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody CommonDataVO deleteCasuality(
			@RequestBody CasualityDTO casualityDto) throws Exception {
		statusVO = new StatusVO("Not yet implemented",
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
				null);
	

	commonDataVO = new CommonDataVO(statusVO);
	return commonDataVO;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getCasualitys() throws Exception {
		List<Casuality> casualitys=new ArrayList<Casuality>();
		casualitys=casualityService.getCasualitys();
		List<CasualityDTO> casualityDtoList=DozerListMapping.mapList(casualitys, CasualityDTO.class);
		
		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS,
				null);
		commonDataVO = new CommonDataVO(casualityDtoList,null,statusVO);
		
		
		return commonDataVO;
	}
	

	@RequestMapping(value = "/{casualityId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getCasualityDetails(
			@PathVariable(value = "casualityId") String casualityId) throws Exception {
		
		CasualityDTO casualityDTO=DozerListMapping.map(casualityService.getCasualityDetails(Integer.parseInt(casualityId)), CasualityDTO.class);
		
		
		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);
		commonDataVO = new CommonDataVO(null, casualityDTO, 
				statusVO);
		return commonDataVO;
	}

	
	@RequestMapping(value = "/query",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CommonDataVO getCasualitysRequired(@RequestBody CasualityDTO casualityDto)
			throws Exception {		
		List<Casuality> casualitys=new ArrayList<Casuality>();
		
		casualitys=casualityService.getCasualitysRequired(casualityDto.getCasualityId(),casualityDto.getDisasterId(),casualityDto.getPersonName(),casualityDto.getMyLocation(),casualityDto.getDisasterType());
		List<CasualityDTO> casualityDtoList=DozerListMapping.mapList(casualitys, CasualityDTO.class);
				
		
		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);
		commonDataVO = new CommonDataVO(casualityDtoList, null,
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
