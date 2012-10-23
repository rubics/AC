package app.models;

import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.UserController;

public abstract class ChatHistoryRequest implements HttpRequestListener {
	
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public ChatHistoryRequest(){}
	
	public void getChatMessage(String chatter_id, String chat_id){
		dispatcher = new HttpRequestDispatcher(AirCrew.get_chat + UserController.getInstance().getUser().getUserId() + "/" + chatter_id + "/" + chat_id
				, method, requestListener, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		System.out.println(json_response);
		try{
			JSONObject json = new JSONObject(json_response);
			Vector chat_history;
			String image_name_a = "";
			String image_name_b = "";
			if(json.has("chat")) {
				JSONObject chat_response = json.getJSONObject("chat");
				chat_history = new Vector(5, 5);
				try{					
					int i = 0;
					while(chat_response.has(String.valueOf(i))){
						JSONObject chat_object = chat_response.getJSONObject(String.valueOf(i));
						System.out.println("---->");
						System.out.println(chat_object);
						chat_history.addElement(new ChatHistory(chat_object.getString("id"),
								chat_object.getString("date_time"),
								chat_object.getString("type"),
								chat_object.getString("text"),
								chat_object.getString("token"),
								chat_object.getString("user_id"),
								chat_object.getString("user2_id")));
						i++;
					}
					JSONArray images = chat_response.getJSONArray("images");
					if(images.length() == 1)
						if(images.getJSONObject(0).getString("user_id").equals(UserController.getInstance().getUser().getUserId()))
							image_name_a = images.getJSONObject(0).getString("image_name");
						else
							image_name_b = images.getJSONObject(0).getString("image_name");
					else if (images.length() == 2){	
						if(images.getJSONObject(0).getString("user_id").equals(UserController.getInstance().getUser().getUserId())){
							image_name_a = images.getJSONObject(0).getString("image_name");
							image_name_b = images.getJSONObject(1).getString("image_name");
						} else{
							image_name_a = images.getJSONObject(1).getString("image_name");
							image_name_b = images.getJSONObject(0).getString("image_name");
						}
					}
				} catch (JSONException e){
					System.out.println("--> Inner Catch");
					System.out.println(e.getClass().getName());
					System.out.println(e);
				}
				afterSuccess(chat_history, image_name_a, image_name_b);
			} else if (json.has("error") & !json.isNull("error")){
				System.out.println("--> Outer Catch");
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
	
	public abstract void afterSuccess(Vector chat_history, String image_a, String image_b);
	
	public void httpfailure(String errmsg) {}
}