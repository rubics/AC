package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;

public abstract class VisitorsRequest implements HttpRequestListener {
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public VisitorsRequest(){}
	
	public void myVisitors(){
		dispatcher = new HttpRequestDispatcher(AirCrew.my_visitors + "547", method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("My Visitors")) {

				JSONArray response = json.getJSONArray("My Visitors");
				Visitor[] visitors = new Visitor[response.length()];
				
				for(int i=0; i<response.length(); i++){
					JSONObject obj = (JSONObject)response.get(i);
					visitors[i] = new Visitor(obj.getString("id"),
							obj.getString("user_id"),
							obj.getString("user_name"),
							obj.getString("country"),
							obj.getString("gender"),
							obj.getString("image_name"),
							obj.getString("city"),
							obj.getString("airline_name"),
							obj.getString("desig"),
							obj.getString("visit_status"),
							obj.getString("block_status"),
							obj.getString("favorite_status"));
				}
				afterSuccess(visitors);
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
	
	public abstract void afterSuccess(Visitor[] visitors);
}
