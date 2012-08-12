package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.user.DealController;

public class CategoriesRequest implements HttpRequestListener{
	
	private Deal deal;
	private DealController dealController;
	private static final String method = "GET";
	private HttpRequestDispatcher dispatcher;
	
	public CategoriesRequest(DealController _dealController){
		dealController = _dealController;
	}
	
	public void getCategories(){
		dispatcher = new HttpRequestDispatcher(AirCrew.categories, method, this, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("category")){

				JSONArray categories_arr = json.getJSONArray("category");
				
				Category[] categories = new Category[categories_arr.length()+1];
				categories[0] = new Category("0", "All Categories");
				for(int i=1; i<categories_arr.length()+1; i++){
					String cat_id = ((JSONObject)(categories_arr.get(i-1))).getString("b_cat_id");
					String cat_name = ((JSONObject)(categories_arr.get(i-1))).getString("b_cat_name");
					categories[i] = new Category(cat_id, cat_name);
					System.out.println(categories[i]);
					System.out.println();
				}
				
				dealController.setCategories(categories);
	
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
