package app.models;

import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;

public abstract class SignoutRequest implements HttpRequestListener{
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;

	public SignoutRequest(){}

	public void sign_out(){
		dispatcher = new HttpRequestDispatcher(AirCrew.url + AirCrew.signout, method, requestListener, "");
		dispatcher.start();
	}

	public abstract void httpfailure(String errmsg);

	public abstract void httpsuccess(byte[] array, String str);
}
