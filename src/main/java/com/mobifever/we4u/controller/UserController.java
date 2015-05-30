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
import com.mobifever.we4u.dto.UserDTO;
import com.mobifever.we4u.model.User;
import com.mobifever.we4u.service.UserService;
import com.mobifever.we4u.utils.DozerListMapping;
import com.mobifever.we4u.vo.CommonDataVO;
import com.mobifever.we4u.vo.StatusVO;

@Controller
@RequestMapping(ControllerMapping.USERS)
public class UserController {

	@Autowired
	private UserService userService;

	private StatusVO statusVO;

	private CommonDataVO commonDataVO;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public CommonDataVO addUser(@RequestBody UserDTO userDto) throws Exception {
		String result = null;

		if (userDto != null) {
			result = userService.add(userDto);
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
	public @ResponseBody CommonDataVO updateUser(@RequestBody UserDTO userDto)
			throws Exception {

		statusVO = new StatusVO("Not yet implemented",
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);

		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody CommonDataVO deleteUser(@RequestBody UserDTO userDto)
			throws Exception {
		statusVO = new StatusVO("Not yet implemented",
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);

		commonDataVO = new CommonDataVO(statusVO);
		return commonDataVO;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getUsers() throws Exception {
		List<User> users = new ArrayList<User>();
		users = userService.getUsers();
		List<UserDTO> userDtoList = DozerListMapping.mapList(users,
				UserDTO.class);

		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);
		commonDataVO = new CommonDataVO(userDtoList, null, statusVO);

		return commonDataVO;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getUserDetails(
			@PathVariable(value = "userId") String userId) throws Exception {

		UserDTO userDTO = DozerListMapping.map(
				userService.getUserDetails(Integer.parseInt(userId)),
				UserDTO.class);

		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);
		commonDataVO = new CommonDataVO(null, userDTO, statusVO);
		return commonDataVO;
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommonDataVO getUsersRequired(
			@ModelAttribute(value = "location") String location)
			throws Exception {

		List<User> users = new ArrayList<User>();
		
		users = userService.getUsersRequired(location,"");
		List<UserDTO> userDtoList = DozerListMapping.mapList(users,
				UserDTO.class);

		statusVO = new StatusVO(Constants.ltc_SUCCESSCODE,
				Constants.ltc_RESPONSECODESUCCESS, Constants.ltc_SUCCESS, null);
		commonDataVO = new CommonDataVO(userDtoList, null, statusVO);
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
