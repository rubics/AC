package app.models;

import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;

public class DealsRequest implements HttpRequestListener {
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public void getAllDeals(){
		dispatcher = new HttpRequestDispatcher(AirCrew.all_deals, method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		
	}
	
	public void httpfailure(String errmsg) {}

	
}
