package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;
import app.controllers.UserController;

public abstract class ChatRequest implements HttpRequestListener {
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public ChatRequest(){}
	
	public void setChat(String chatter_id, String message){
		System.out.println(AirCrew.set_chat + UserController.getInstance().getUser().getUserId() + "/" + chatter_id +"/text/" + message);
		dispatcher = new HttpRequestDispatcher(AirCrew.set_chat + UserController.getInstance().getUser().getUserId() + "/" + chatter_id +"/text/" + message,
				method,
				requestListener,
				"");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		System.out.println(json_response);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("response")) {
				String message = json.getJSONObject("response").getString("message");
				afterSuccess();
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
	
	public abstract void afterSuccess();
	public void httpfailure(String errmsg) {}
}
