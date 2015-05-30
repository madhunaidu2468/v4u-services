package com.mobifever.we4u.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
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

	public String sendViaSMSHorizon(SMSBean sms) throws IOException {
		ArrayList<String> toList=new ArrayList<String>();
		toList.add("9986512468");
		SMSBean smsBean=new SMSBean();
		smsBean.setToList(toList);
		smsBean.setMessage("hello testing api");
		RestTemplate restTemplate = new RestTemplate();
		String response;
		/*try {
			response = restTemplate.getForObject(
					Constants.SMS_HORIZON_BASE, String.class,sms.getToList()
							.toString().trim(), sms.getMessage());
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String urltext="http://smshorizon.co.in/api/sendsms.php?user=madhuram2468&apikey=K9a3N4jBhMkYhxt3ACtT&mobile="+smsBean.getToList().toString().replace("[", "").replace("]", "")+"&message="+smsBean.getMessage()+"&senderid=SMSTXT&type=txt";
		URL url = new URL(urltext);
	      HttpURLConnection conn= (HttpURLConnection) url.openConnection();
	         conn.setRequestMethod("GET");
	         String USER_AGENT = "Mozilla/5.0";
	         conn.setRequestProperty("User-Agent", USER_AGENT);
	         InputStream is = conn.getInputStream() ;
        System.out.println(conn.getResponseCode());
        BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
        System.out.println(in.readLine());
	      
		return is.toString();
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
