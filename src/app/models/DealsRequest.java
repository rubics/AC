package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;
import app.controllers.user.DealController;

public class DealsRequest implements HttpRequestListener {
	
	private DealController dealController;
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public DealsRequest(DealController _dealController){
		dealController = _dealController;
	}
	public void getAllDeals(){
		dispatcher = new HttpRequestDispatcher(AirCrew.all_deals, method, requestListener, "");
		dispatcher.start();
	}
	
	public void getFilteredDeals(String _category_id, String _country_code, String _city_id){
		dispatcher = new HttpRequestDispatcher(AirCrew.filter_deals + _category_id + "/" + _country_code + "/" + _city_id , method, this, "");
		dispatcher.start();
	}
	
	public void getDealbyCategory(Category _category){
		dispatcher = new HttpRequestDispatcher(AirCrew.deal_by_category + _category.getCategory_id(), method, this, "");
		dispatcher.start();
	}
	
	public void getBookmarks(){
		dispatcher = new HttpRequestDispatcher(AirCrew.bookmarks + AirCrewApp.app.getUserController().getUser().getUserId(), method, this, "");
		dispatcher.start();
	}
	
	
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("business")) {

				JSONArray response = json.getJSONArray("business");
				final Deal[] deals = new Deal[response.length()];
				for(int i=0; i<response.length(); i++){
					JSONObject deal = (JSONObject)response.get(i);
					String id = deal.getString("b_id");
					String name = deal.getString("b_name");
					String description = deal.getString("b_desc");
					String category_id = deal.getString("b_cat");
					String category_name;
					try{
						category_name = deal.getString("b_cat_name");
					}catch(Exception e){
						category_name = "";
					}
					String logo = deal.getString("logo");
					String city = deal.getString("city");
					String x_code = deal.getString("x_code");
					String y_code = deal.getString("y_code");
					String phone = deal.getString("phone");
					String email = deal.getString("email");
					deals[i] = new Deal(id,name,description,category_id,category_name,logo,city,x_code,y_code,phone,email);
				}
				dealController.setDeals(deals);
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

	
}
