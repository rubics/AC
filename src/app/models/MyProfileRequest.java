package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;
import app.controllers.user.DealController;

public abstract class MyProfileRequest implements HttpRequestListener{

	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public MyProfileRequest(){}
	
	public void getMyProfileInfo(){
		dispatcher = new HttpRequestDispatcher(AirCrew.my_profile + AirCrewApp.app.getUserController().getUser().getUserId(),
				method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("My Profile")) {

				JSONArray response = json.getJSONArray("My Profile");
				
					JSONObject my_profile = (JSONObject)response.get(0);
					
					String user_id = my_profile.getString("user_id");
					String user_name = my_profile.getString("user_name");
					String user_email = my_profile.getString("user_email");
					String airline = my_profile.getString("airline");
					String gender = my_profile.getString("gender");
					String about_me = my_profile.getString("about_me");
					String designation = my_profile.getString("designation");
					String country = my_profile.getString("country");
					String city = my_profile.getString("city");
					String airline_name = my_profile.getString("airline_name");
					String airline_id = my_profile.getString("airline_id");
					String type = my_profile.getString("type");
					String desig = my_profile.getString("desig");
					String city_name = my_profile.getString("city_name");
					String country_code = my_profile.getString("country_code");
					
					Profile user_profile = new Profile(user_id, user_name, user_email, airline, gender, about_me, designation, country, city, airline_name, airline_id, type, desig, city_name, country_code);
					afterSuccess(user_profile);
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
	
	public abstract void afterSuccess(Profile profile);
}
