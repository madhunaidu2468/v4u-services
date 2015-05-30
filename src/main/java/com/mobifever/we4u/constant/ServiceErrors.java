package com.mobifever.we4u.constant;

public interface ServiceErrors {

	public static final String ltc_DISASTEREXISTS="Disaster Exists";
	public static final String ltc_DISASTERINVALID="Disaster Invalid";
	public static final String ltc_CASUALITYABSENT="Casualitys first or last name not present";
	public static final String ltc_CASUALITYEXISTS="Casuality Exists";
	public static final String ltc_CASUALITYINVALID="Casuality Invalid";
	public static final String ltc_DISASTERIDNOTNULL="Disaster Id should be null";
	public static final String ltc_DISASTERIDNULL="Disaster Id is null";
	public static final String  ltc_CASUALITYIDINVALID="Casuality id is not null";
	public static final String ltc_CASUALITY_ABSENT="Casuality value is missing";
	
	public static final String EMAIL_TO_ADDRESS_MISSING="To address is invalid";
	public static final String EMAIL_TO_ADDRESS_INVALID="To address is invalid";
	public static final String EMAIL_FROM_ADDRESS_MISSING="From address is invalid";
	public static final String EMAIL_FROM_ADDRESS_INVALID="From address is invalid";
	public static final String EMAIL_TO_NAME_MISSING="To name is invalid";
	public static final String EMAIL_FROM_NAME_MISSING="From name is invalid";
	public static final String EMAIL_SUBJECT_MISSING="Missing Email Subject";
	public static final String EMAIL_BODY_MISSING="Email Body is missing";
	
	public static final String PUSH_APP_NAME_MISSING="App name is missing";
	public static final String PUSH_APP_PACKAGE_MISSING="App package is missing";
	public static final String PUSH_MESSAGE_MISSING="Message is missing";
	public static final String PUSH_TITLE_MISSING="Title is missing";
	
	public static final String SMS_TO_MISSING="Sender numbers is missing";
	public static final String SMS_MESSAGE_MISSING="SMS Text is missing";
}
