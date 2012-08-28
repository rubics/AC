package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.DealController;

public abstract class AirlinesRequest implements HttpRequestListener{
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public AirlinesRequest(){}
	
	public void getAirlines(){
		dispatcher = new HttpRequestDispatcher(AirCrew.airlines, method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("Airline")) {

				JSONArray response = json.getJSONArray("Airline");
				final Airline[] airlines = new Airline[response.length()];
				for(int i=0; i<response.length(); i++){
					JSONObject airline = (JSONObject)response.get(i);
					String id = airline.getString("id");
					String name = airline.getString("name");
					String airline_id = airline.getString("airline_id");
					airlines[i] = new Airline(id, name, airline_id);
				}
				
				onSuccess(airlines);
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
	
	public void httpfailure(String errmsg) {}
	
	public abstract void onSuccess(Airline[] airlines);
}
