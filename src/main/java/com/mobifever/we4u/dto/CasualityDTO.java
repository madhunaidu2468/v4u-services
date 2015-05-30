package com.mobifever.we4u.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CasualityDTO {


	private Integer casualityId;
	private String personName;
	private String disasterType;
	private String myLocation;
	private String phoneNumber;
	private Long date;
	private List<String> statusOfPerson;
	private String kindOfHelpNeeded;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getDisasterType() {
		return disasterType;
	}
	public void setDisasterType(String disasterType) {
		this.disasterType = disasterType;
	}
	public String getMyLocation() {
		return myLocation;
	}
	public void setMyLocation(String myLocation) {
		this.myLocation = myLocation;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public List<String> getStatusOfPerson() {
		return statusOfPerson;
	}
	public void setStatusOfPerson(List<String> statusOfPerson) {
		this.statusOfPerson = statusOfPerson;
	}
	public String getKindOfHelpNeeded() {
		return kindOfHelpNeeded;
	}
	public void setKindOfHelpNeeded(String kindOfHelpNeeded) {
		this.kindOfHelpNeeded = kindOfHelpNeeded;
	}
	
	
	public Integer getCasualityId() {
		return casualityId;
	}
	public void setCasualityId(Integer casualityId) {
		this.casualityId = casualityId;
	}
	@Override
	public String toString() {
		return "CasualityDTO [personName=" + personName + ", disasterType="
				+ disasterType + ", myLocation=" + myLocation
				+ ", phoneNumber=" + phoneNumber + ", date=" + date
				+ ", statusOfPerson=" + statusOfPerson + ", kindOfHelpNeeded="
				+ kindOfHelpNeeded + "]";
	}
	
	
	
}
