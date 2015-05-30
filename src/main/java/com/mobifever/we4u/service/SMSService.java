package com.mobifever.we4u.service;

import java.io.IOException;
import java.net.MalformedURLException;

import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.service.impl.SMSBean;

public interface SMSService {

	public String sendSMS(SMSBean sms) throws IOException,
	We4UException;


public String sendViaSMSGatewayHub(SMSBean sms) throws We4UException;

public String sendViaSMS19(SMSBean sms) throws We4UException;

public String sendViaSMSHorizon(SMSBean sms) throws We4UException, MalformedURLException, IOException;
public String getCountViaSMSGatewayHub(SMSBean sms) throws We4UException;

}
