package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;
import app.controllers.FavoritesController;

public abstract class FavoritesRequest implements HttpRequestListener {
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public FavoritesRequest(){
	}
	
	public void getFavorites(){
		dispatcher = new HttpRequestDispatcher(AirCrew.favorites + AirCrewApp.app.getUserController().getUser().getUserId(), method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		System.out.println(json_response);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("connection")) {

				JSONArray response = json.getJSONArray("connection");
				
				Connection[] connections = new Connection[response.length()];
				
				for(int i=0; i<response.length(); i++){
					JSONObject object = (JSONObject)response.get(i);
					String id = object.getString("id");
					String user_id = object.getString("user_id");
					String user_name = object.getString("user_name");
					String user_email = object.getString("user_email");
					String about_me = object.getString("about_me");
					String airline = object.getString("airline");
					String gender = object.getString("gender");
					String designation = object.getString("designation");
					String country = object.getString("country");
					String city_id = object.getString("city");
					String airline_name = object.getString("airline_name");
					String type = object.getString("type");
					String desig = object.getString("desig");
					String city_name = object.getString("city_name");
					String image_name = object.getString("image_name");
					String last_visit = object.getString("last_visit");
					String visit_status = object.getString("visit_status");
					
					connections[i] = new Connection(id, user_id, user_name, user_email, about_me, airline, gender, designation, country, city_id, airline_name, type, desig, city_name, image_name, last_visit, visit_status);
					System.out.println("----------------------");
					System.out.println(connections[i]);
				}
				
				afterSuccess(connections);
				
				
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
	
	public abstract void afterSuccess(Connection[] connections);
}
