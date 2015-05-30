package com.mobifever.we4u.constant;

public interface Constants {

	
	public String ltc_ACTIVE="Active";
	public String ltc_INACTIVE="InActive";
	public String ltc_SUCCESS="Success";
	public String ltc_FAILURE="Failure";
	public String ltc_SUCCESSCODE="0";
	public String ltc_FAILURECODE="1";
	public String ltc_RESPONSECODESUCCESS="200";
	public String ltc_RESPONSECODENOTFOUND="400";
	public String ltc_RESPONSECODEFAILURE="401";
	public String ltc_RESPONSECODEERROR="500";
	
	
	/*member*/
	public static final Boolean ltc_RETIRED_DEFAULT=false;
	public static final String ltc_PASSWORD_APPENDER="fyizi";
	public static final int ltc_PASSWORD_LENGTH=6;
	
	
	public static final String port = "587";
	public static final String from = "noreply.mysuppteam@gmail.com";
	public static final String host = "smtp.gmail.com";
	public static final String subject = "Welcome to EmailApp";
	public static final String username = "noreply.mysuppteam@gmail.com";
	public static final String password = "misupport";

	public static final String SMS_GATEWAY_HUB_BASE = "http://login.smsgatewayhub.com/smsapi/pushsms.aspx?user={username}&pwd={password}&to={toList}&sid=WEBSMS&msg={message}&fl=0";
	public static final String SMS_GATEWAY_19_BASE = "http://www.sms19.com/ComposeSMS.aspx?username={username}&password={password}&sender={senderid}&to={toList}&message={message}&priority=1&dnd=1&unicode=0";
	public static final String SMS_HORIZON_BASE="http://smshorizon.co.in/api/sendsms.php?user=madhuram2468&apikey=K9a3N4jBhMkYhxt3ACtT&mobile={toList}&message={message}&senderid=SMSTXT&type=txt";
	public static final String SMS_GATEWAY_HUB_GET_COUNT ="http://login.smsgatewayhub.com/smsapi/CheckBalance.aspx?user={username}&password={password}&gwid=2";
	public static final String SMS_GATEWAY_HUB_USERNAME="madhuram2468";
	public static final String SMS_GATEWAY_HUB_PASSWORD="977580";
	public static final String SMS_GATEWAY_HUB_BASE_APIKEY = "918050481168-eef78714fa8a406b9ca45c01520ae11a";
	public static final String SMS_GATEWAY_19_BASE_USERNAME = "madhunaidu2468246";
	public static final String SMS_GATEWAY_19_BASE_PASSWORD = "24625";
	public static final String SMS_GATEWAY_19_BASE_SENDERID = "fyizi";
}
