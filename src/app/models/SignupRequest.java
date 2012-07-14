package app.models;

import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;

public abstract class SignupRequest implements HttpRequestListener{
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public SignupRequest(){}
	
	public void sign_up(String _user_email,
			String _user_pass,
			String _user_name,
			String _airline,
			String _designation,
			String _gender,
			String _country,
			String _city,
			String _terms,
			String _user_pass1,
			String _confirm_email){
		
		StringBuffer postString = new StringBuffer("");
		postString.append("user_email=");
		postString.append(_user_email);
		postString.append("&user_pass=");
		postString.append(_user_pass);
		postString.append("&user_name=");
		postString.append(_user_name);
		postString.append("&airline=");
		postString.append(_airline);
		postString.append("&designation=");
		postString.append(_designation);
		postString.append("&gender=");
		postString.append(_gender);
		postString.append("&country=");
		postString.append(_country);
		postString.append("&city=");
		postString.append(_city);
		postString.append("&terms=");
		postString.append(_terms);
		postString.append("&user_pass1=");
		postString.append(_user_pass1);
		postString.append("&confirm_email=");
		postString.append(_confirm_email);
		
		
		dispatcher = new HttpRequestDispatcher(AirCrew.signup, method, requestListener, postString.toString());
		dispatcher.start();
	}
	public abstract void httpfailure(String errmsg);
	public abstract void httpsuccess(byte[] array, String str);
}
