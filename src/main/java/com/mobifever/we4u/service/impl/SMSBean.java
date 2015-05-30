package com.mobifever.we4u.service.impl;

import java.util.List;

public class SMSBean {

	public SMSBean() {
		// TODO Auto-generated constructor stub
	}

	
	public SMSBean(List<String> toList, String message, String gateway) {
		super();
		this.toList = toList;
		this.message = message;
		this.gateway = gateway;
	}


	private List<String> toList;
	private String fromName;
	private String message;
	private Long scheduleTime;
	private String gateway;
	private String response;
	
	public List<String> getToList() {
		return toList;
	}
	public void setToList(List<String> toList) {
		this.toList = toList;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getScheduleTime() {
		return scheduleTime;
	}
	public void setScheduleTime(Long scheduleTime) {
		this.scheduleTime = scheduleTime;
	}
	
	
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	
	@Override
	public String toString() {
		return "SMSBean [toList=" + toList + ", fromName=" + fromName
				+ ", message=" + message + ", scheduleTime=" + scheduleTime
				+ ", gateway=" + gateway + ", response=" + response + "]";
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}
