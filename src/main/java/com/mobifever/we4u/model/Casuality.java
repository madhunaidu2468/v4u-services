package com.mobifever.we4u.model;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "casuality")
public class Casuality {

	private static final long serialVersionUID = 1L;

	private BigInteger _id;
	
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
	public BigInteger get_id() {
		return _id;
	}
	@Override
	public String toString() {
		return "Casuality [_id=" + _id + ", personName=" + personName
				+ ", disasterType=" + disasterType + ", myLocation="
				+ myLocation + ", phoneNumber=" + phoneNumber + ", date="
				+ date + ", statusOfPerson=" + statusOfPerson
				+ ", kindOfHelpNeeded=" + kindOfHelpNeeded + "]";
	}
	public Integer getCasualityId() {
		return casualityId;
	}
	public void setCasualityId(Integer casualityId) {
		this.casualityId = casualityId;
	}
	
	
}
