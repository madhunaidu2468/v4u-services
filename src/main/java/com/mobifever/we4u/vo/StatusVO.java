package com.mobifever.we4u.vo;

public class StatusVO {

	private String statusCode;
	private String responseCode;
	private String responseMessage;
	
	public StatusVO(){
		
	}
	
	public StatusVO(String statusCode, String responseCode, String responseMessage, String userResponseMessage){
		this.statusCode=statusCode;
		this.responseCode=responseCode;
		this.responseMessage=responseMessage;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "StatusVO [statusCode=" + statusCode + ", responseCode="
				+ responseCode + ", responseMessage=" + responseMessage + "]";
	}
	
	
}
