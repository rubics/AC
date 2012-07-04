package app.controllers.user;

import net.rim.device.api.ui.UiApplication;
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
			String json_response = new String(array);
			
//			AirCrewApp.app.invokeLater(new Runnable() {
//				
//				public void run() {
//					UiApplication.getUiApplication().pushScreen(new DashboardScreen());
////					UiApplication.getUiApplication().popScreen(signinScreen);
//				}
//			});
			
//			try {
//				JSONObject json = new JSONObject(json_response);
//
//				if(json.has("response") & !json.isNull("response")) {
//
//					JSONObject response = json.getJSONObject("response");
//					String message = response.getString("message");
//
//					System.out.println("Message: " + message);			
//					System.out.println();
//
//					UiApplication.getUiApplication().popScreen(signinScreen);
//					UiApplication.getUiApplication().pushScreen(DashboardScreen.createDashboardScreenInstance());
//
//				} else {}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
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