package app.controllers.user;

import helpers.org.json.me.JSONException;
import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import app.AirCrewApp;
import app.models.SigninRequest;
import app.models.User;
import app.views.screens.DashboardScreen;
import app.views.screens.SigninScreen;

public class UserController {

	private User user;
	
	private SigninScreen signinScreen;

	public SigninRequest signinRequest = new SigninRequest() {

		public void httpsuccess(byte[] array, String str){

			final String json_response = new String(array);
			
			try{
				JSONObject json = new JSONObject(json_response);

				if(json.has("response") & !json.isNull("response")) {

					JSONObject response = json.getJSONObject("response");
					String user_name = response.getString("username");
					String user_id = response.getString("userId");
					String session_id = response.getString("sessionId");
					String message = response.getString("message");
					
					user = new User(user_name, user_id, session_id);
					
					UiApplication.getUiApplication().invokeAndWait(new Runnable() {
						
						public void run() {
							UiApplication.getUiApplication().pushScreen(new DashboardScreen());
							UiApplication.getUiApplication().popScreen(signinScreen);
						}		
					});

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
	};

	//------------------------------------------------------------------------------//

	public UserController(){}

	public void pushSignInScreen(){
		signinScreen = new SigninScreen();
		AirCrewApp.app.pushScreen(signinScreen);
	}
}