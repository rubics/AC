package app.models;

import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import app.AirCrewApp;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;

public abstract class SignoutRequest implements HttpRequestListener{
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;

	public SignoutRequest(){}

	public void sign_out(){
		dispatcher = new HttpRequestDispatcher(AirCrew.signout, method, requestListener, "");
		dispatcher.start();
	}

	public void httpsuccess(byte[] array, String str) {
		
		final String json_response = new String(array);
		
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("response") & !json.isNull("response")) {

				JSONObject response = json.getJSONObject("response");
				final String message = response.getString("message");
				AirCrewApp.app.persistenceController.removeUser();
				afterSuccess(message);

			} else if (json.has("error") & !json.isNull("error")){
				JSONObject response = json.getJSONObject("error");
				final String code = response.getString("code");
				final String message = response.getString("message");
				
				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					
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
	
	public void httpfailure(String error_msg){};
	
	public abstract void afterSuccess(String message);
}
