package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;

public abstract class ResetPasswordRequest implements HttpRequestListener {
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public ResetPasswordRequest(){}
	
	public void resetPassword(String email){
		dispatcher = new HttpRequestDispatcher(AirCrew.forgot_password + email, method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("response")){
				JSONObject response = json.getJSONObject("response");
				afterSuccess(response.getString("message"));
			} else if (json.has("error") & !json.isNull("error")){
				JSONObject response = json.getJSONObject("error");
				final String code = response.getString("code");
				final String message = response.getString("message");
				UiApplication.getUiApplication().invokeAndWait(new Runnable(){
					public void run() {
						Dialog.alert(message);
					}		
				});
			}
			
		}catch(Exception e){
			System.out.println(">> Exception @ " + e.getClass().getName());
			e.printStackTrace();
		}
	}
	
	public void httpfailure(String errmsg) {}
	
	public abstract void afterSuccess(String message);
}

// {"response":{"message":"Your password has been reset,Please check your email"}}