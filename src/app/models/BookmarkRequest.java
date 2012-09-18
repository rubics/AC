package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.AirCrewApp;
import app.controllers.BookmarkController;

public class BookmarkRequest implements HttpRequestListener {
	
	private BookmarkController bookmarksController;
	private static final String method = "GET";
	private HttpRequestListener requestListener = this;
	private HttpRequestDispatcher dispatcher;
	
	public BookmarkRequest(BookmarkController _bookmarksController){
		bookmarksController = _bookmarksController;
	}
	
	public void getBookmarks(){
		AirCrewApp app = (AirCrewApp)(UiApplication.getUiApplication());
		dispatcher = new HttpRequestDispatcher(AirCrew.bookmarks + AirCrewApp.app.getUserController().getUser().getUserId(),method, this, "");
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("bookmark")) {
// {"bookmark":[{"bookmark_id":"9","b_id":"4","u_id":"364","b_name":null,"b_desc":"","logo":null}]}
				JSONArray response = json.getJSONArray("bookmark");
				final Deal[] bookmarks = new Deal[response.length()];
				for(int i=0; i<response.length(); i++){
					JSONObject bookmark = (JSONObject)response.get(i);
					String bookmark_id = bookmark.getString("bookmark_id");
					String b_id = bookmark.getString("b_id");
					String u_id = bookmark.getString("u_id");
					String b_name = bookmark.getString("b_name");
					String b_desc = bookmark.getString("b_desc");
					String logo = bookmark.getString("logo");
					bookmarks[i] = new Deal(b_id, b_name, b_desc,"", "",logo, "", "", "", "", "");
				}
				bookmarksController.setBookmarks(bookmarks);
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
