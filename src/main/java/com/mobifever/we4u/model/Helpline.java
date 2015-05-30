package com.mobifever.we4u.model;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "helpline")
public class Helpline {

	private static final long serialVersionUID = 1L;

	private BigInteger _id;
	
	private Integer helplineId;	
	private String location;
	private String disasterType;
	private String kindOfHelp;
	private List<String> helpLineNumbers;
	
	
	public Integer getHelplineId() {
		return helplineId;
	}
	public void setHelplineId(Integer helplineId) {
		this.helplineId = helplineId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDisasterType() {
		return disasterType;
	}
	public void setDisasterType(String disasterType) {
		this.disasterType = disasterType;
	}
	public String getKindOfHelp() {
		return kindOfHelp;
	}
	public void setKindOfHelp(String kindOfHelp) {
		this.kindOfHelp = kindOfHelp;
	}
	public List<String> getHelpLineNumbers() {
		return helpLineNumbers;
	}
	public void setHelpLineNumbers(List<String> helpLineNumbers) {
		this.helpLineNumbers = helpLineNumbers;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigInteger get_id() {
		return _id;
	}
	@Override
	public String toString() {
		return "Helpline [_id=" + _id + ", helplineId=" + helplineId
				+ ", location=" + location + ", disasterType=" + disasterType
				+ ", kindOfHelp=" + kindOfHelp + ", helpLineNumbers="
				+ helpLineNumbers + "]";
	}

	

}
