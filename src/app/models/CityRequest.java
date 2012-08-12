package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.user.DealController;
import app.views.screens.deals.DealFilterScreen;

public class CityRequest implements HttpRequestListener {
	
	private Deal deal;
	private DealController dealController;
	private DealFilterScreen dealFilterScreen;
	private static final String method = "GET";
	private HttpRequestDispatcher dispatcher;
	
	public CityRequest(DealController _dealController, DealFilterScreen _dealFilterScreen){
		dealController = _dealController;
		dealFilterScreen = _dealFilterScreen;
		
	}
	
	public void getCity(String _country_code){
		dispatcher = new HttpRequestDispatcher(AirCrew.cities + _country_code, method, this, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("City")){

				JSONArray cities_arr = json.getJSONArray("City");
				
				City[] cities = new City[cities_arr.length()+1];
				cities[0] = new City("0", "All Cities");
				for(int i=1; i<cities_arr.length()+1; i++){
					String city_id = ((JSONObject)(cities_arr.get(i-1))).getString("id");
					String city_name = ((JSONObject)(cities_arr.get(i-1))).getString("city");
					cities[i] = new City(city_id, city_name);
				}
				dealController.setCity(cities);
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
	
	public void httpfailure(String errmsg) {
		// TODO Auto-generated method stub
		
	}
}
