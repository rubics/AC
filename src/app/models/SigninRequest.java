package app.models;

import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;

public abstract class SigninRequest implements HttpRequestListener{

	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;

	public SigninRequest(){}

	public void sign_in(String userEmail, String password){
		System.out.println(">> URL: " + AirCrew.signin + "?" + "user_email="+userEmail+"&user_pass="+password);
		System.out.println();
		dispatcher = new HttpRequestDispatcher(AirCrew.signin + "?" + "user_email="+userEmail+"&user_pass="+password, method,requestListener,"" );
		dispatcher.start();
	}

	public abstract void httpfailure(String errmsg);

	public abstract void httpsuccess(byte[] array, String str);
}