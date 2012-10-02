package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;

public abstract class MyRosterRequest implements HttpRequestListener {
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public MyRosterRequest(){}
	
	public void getMyRoster(){
		System.out.println(AirCrew.my_roster + AirCrewApp.app.getUserController().getUser().getUserId());
		dispatcher = new HttpRequestDispatcher(AirCrew.my_roster + AirCrewApp.app.getUserController().getUser().getUserId(), method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		System.out.println(json_response);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("My Roster")) {
			
				JSONObject response = json.getJSONObject("My Roster");
				String country = response.getString("country");
				String city = response.getString("city");
				String from_date = response.getString("from_date");
				String to_date = response.getString("todate");
				String view_all = response.getString("view_all");
				
				Roster roster = new Roster(country, city, from_date, to_date, view_all);
				System.out.println(roster);
				afterSuccess(roster);
			} else if (json.has("error") & !json.isNull("error")){
				JSONObject response = json.getJSONObject("error");
				final String code = response.getString("code");
				final String message = response.getString("message");
				System.out.println("Response Error @ MyRoster: " + message);
			}
		}catch(Exception e){
			System.out.println(">> Exception @ " + e.getClass().getName());
			e.printStackTrace();
		}
	}
	
	public abstract void afterSuccess(Roster roster);
	
	public void httpfailure(String errmsg) {}
}

//	{"My Roster":[{"id":"6","u_id":"574","details":"","city":"1","country":"Algeria","from_date":"00\/00\/0000","todate":"00\/00\/0000","view_all":"my_fav"}]}
// {"My Roster":[{"id":"6","u_id":"574","details":"","city":"1","country":"Algeria","from_date":"00\/00\/0000","todate":"00\/00\/0000","view_all":"my_fav"}]}