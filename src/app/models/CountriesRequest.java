package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.user.DealController;

public class CountriesRequest implements HttpRequestListener {
	
	private Deal deal;
	private DealController dealController;
	private static final String method = "GET";
	private HttpRequestDispatcher dispatcher;
	
	public CountriesRequest(DealController _dealController){
		dealController = _dealController;
	}
	
	public void getCountries(){
		dispatcher = new HttpRequestDispatcher(AirCrew.countries, method, this, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("Country")){

				JSONArray countries_arr = json.getJSONArray("Country");
				
				Country[] countries = new Country[countries_arr.length()];
				
				for(int i=0; i<countries_arr.length(); i++){
					String country_id = ((JSONObject)(countries_arr.get(i))).getString("country_code");
					String country_name = ((JSONObject)(countries_arr.get(i))).getString("country");
					countries[i] = new Country(country_id, country_name);
				}
				
				dealController.setCountries(countries);
	
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
