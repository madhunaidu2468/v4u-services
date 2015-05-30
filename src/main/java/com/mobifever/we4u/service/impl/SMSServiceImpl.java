package com.mobifever.we4u.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mobifever.we4u.constant.Constants;
import com.mobifever.we4u.constant.ServiceErrors;
import com.mobifever.we4u.exception.We4UException;
import com.mobifever.we4u.service.SMSService;

@Component
public class SMSServiceImpl implements SMSService {

	public String sendSMS(SMSBean sms) throws IOException,
	We4UException {
		String result = null;
		validateSMS(sms);
		if (sms.getGateway().equals("smsgatewayhub")) {
			result = sendViaSMSGatewayHub(sms);
		} else if (sms.getGateway().equals("sms19")) {
			result = sendViaSMS19(sms);
		} else if (sms.getGateway().equals("smshorizon")) {
			result = sendViaSMSHorizon(sms);
		}
		return result;

	}

	public void validateSMS(SMSBean sms) throws We4UException {
		if (sms.getMessage().isEmpty()) {
			throw new We4UException(
					ServiceErrors.SMS_MESSAGE_MISSING);
		} else if (sms.getToList().isEmpty()) {
			throw new We4UException(ServiceErrors.SMS_TO_MISSING);
		}
	}

	public String sendViaSMSGatewayHub(SMSBean sms) {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(
				Constants.SMS_GATEWAY_HUB_BASE, String.class,
				Constants.SMS_GATEWAY_HUB_USERNAME,
				Constants.SMS_GATEWAY_HUB_PASSWORD, sms.getToList().toString(),
				sms.getMessage());
		return response;
	}

	public String sendViaSMS19(SMSBean sms) {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(
				Constants.SMS_GATEWAY_19_BASE, String.class,
				Constants.SMS_GATEWAY_19_BASE_USERNAME,
				Constants.SMS_GATEWAY_19_BASE_PASSWORD,
				Constants.SMS_GATEWAY_19_BASE_SENDERID, sms.getToList()
						.toString(), sms.getMessage());
		return response;
	}

	public String sendViaSMSHorizon(SMSBean sms) {
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(
				Constants.SMS_HORIZON_BASE, String.class,sms.getToList()
						.toString(), sms.getMessage());
		return response;
	}

	public String getCountViaSMSGatewayHub(SMSBean sms) {
		RestTemplate restTemplate = new RestTemplate();

		String response = restTemplate.getForObject(
				Constants.SMS_GATEWAY_HUB_GET_COUNT, String.class,
				Constants.SMS_GATEWAY_HUB_USERNAME,
				Constants.SMS_GATEWAY_HUB_PASSWORD);
		return response;
	}
}
