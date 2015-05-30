package com.mobifever.we4u.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DisasterDTO {


	private Integer disasterId;
	private String disasterType;
	private String location;
	private Long time;
	private Integer numberOfCasualities;
	private List<String> helplineNumbers;
	private String disasterName;
	private List<Integer> affectedUsers;
	
	public Integer getDisasterId() {
		return disasterId;
	}
	public void setDisasterId(Integer disasterId) {
		this.disasterId = disasterId;
	}
	public String getDisasterType() {
		return disasterType;
	}
	public void setDisasterType(String disasterType) {
		this.disasterType = disasterType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Integer getNumberOfCasualities() {
		return numberOfCasualities;
	}
	public void setNumberOfCasualities(Integer numberOfCasualities) {
		this.numberOfCasualities = numberOfCasualities;
	}
	public List<String> getHelplineNumbers() {
		return helplineNumbers;
	}
	public void setHelplineNumbers(List<String> helplineNumbers) {
		this.helplineNumbers = helplineNumbers;
	}
	public String getDisasterName() {
		return disasterName;
	}
	public void setDisasterName(String disasterName) {
		this.disasterName = disasterName;
	}
	
	
	public List<Integer> getAffectedUsers() {
		return affectedUsers;
	}
	public void setAffectedUsers(List<Integer> affectedUsers) {
		this.affectedUsers = affectedUsers;
	}
	@Override
	public String toString() {
		return "DisasterDTO [disasterId=" + disasterId + ", disasterType="
				+ disasterType + ", location=" + location + ", time=" + time
				+ ", numberOfCasualities=" + numberOfCasualities
				+ ", helplineNumbers=" + helplineNumbers + ", disasterName="
				+ disasterName + ", affectedUsers=" + affectedUsers + "]";
	}


}
