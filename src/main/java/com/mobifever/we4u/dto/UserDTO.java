package com.mobifever.we4u.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {

	private Integer userId;
	private String name;
	private String phoneNumber;
	private List<String> emergencyNumbers;
	private String location;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<String> getEmergencyNumbers() {
		return emergencyNumbers;
	}
	public void setEmergencyNumbers(List<String> emergencyNumbers) {
		this.emergencyNumbers = emergencyNumbers;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + ", emergencyNumbers="
				+ emergencyNumbers + ", location=" + location + "]";
	}
	
	
}
