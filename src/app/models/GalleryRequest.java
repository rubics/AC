package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.user.DealController;

public abstract class GalleryRequest implements HttpRequestListener{
	private DealController dealController;
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public void getUserImages(String _user_id){
		dispatcher = new HttpRequestDispatcher(AirCrew.gallery + _user_id, method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("gallery")) {

				JSONArray response = json.getJSONArray("gallery");
				GalleryImage[] galleryImages = new GalleryImage[response.length()];
				
				for(int i=0; i<response.length(); i++){
					JSONObject object = (JSONObject)response.get(i);
					String id = object.getString("id");
					String image_name = object.getString("image_name");
					String def = object.getString("default");
					String order = object.getString("order");
					String user_id = object.getString("user_id");
					
					galleryImages[i] = new GalleryImage(id, image_name, def, order, user_id);
					System.out.println("Image Name: " + image_name);
				}
				
				onSuccess(galleryImages);
				
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
	
	public abstract void onSuccess(GalleryImage[] galleryImages);
}
