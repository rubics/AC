package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import app.controllers.DealController;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;

public abstract class DealDetailsRequest implements HttpRequestListener{

	private String deal_id;
	protected DealDetails dealDetails = null;
	private static final String method = "GET";
	private HttpRequestDispatcher dispatcher;
	
	public DealDetailsRequest(){
	}
	
	public void getDetials(String _deal_id){
		deal_id = _deal_id;
		dispatcher = new HttpRequestDispatcher(AirCrew.deal_detials + "/".concat(deal_id) , method, this, "");
		dispatcher.start();
	}
	public void httpfailure(String errmsg) {
	}

	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("business")){

				JSONObject response = json.getJSONObject("business");
				JSONObject details = response.getJSONObject("0");
				
				String name = details.getString("b_name");
				String description = details.getString("b_desc");
				String category = details.getString("b_cat_name");
				String logo = details.getString("logo");
				String website = details.getString("web");
				String address = details.getString("address");
				String video = details.getString("video");
				String _deal = details.getString("deal");
				
				dealDetails = new DealDetails(name, description, category, logo, website, address, video, _deal);
								
				JSONArray images = response.getJSONArray("more_images");
				for( int i=0; i<images.length(); i++){
					String _image_id = images.getJSONObject(i).getString("img_id");
					String _image_name = images.getJSONObject(i).getString("image_name");
					String _icon_name = images.getJSONObject(i).getString("icon_name");
					System.out.println(dealDetails.addImages(_image_id, _image_name, _icon_name));
				}
				
				System.out.println(dealDetails);
				
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
	
}
