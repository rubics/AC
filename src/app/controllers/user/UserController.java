package app.controllers.user;

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

			String json_response = new String(array);
			System.out.println(json_response);
			int temp = json_response.indexOf("a");
			
			if (UiApplication.getUiApplication() != null){
				System.out.println(">> NOT NULL");
				System.out.println();
			}else {
				System.out.println(">> NULL");
				System.out.println();
			}
			
			UiApplication.getUiApplication().invokeAndWait(new Runnable() {
				
				public void run() {
					Dialog.alert("Hello from AirCrew :-)");				
				}
			});
			
			
//			try{
//			UiApplication.getUiApplication().invokeAndWait(new Runnable() {
//				
//				public void run() {
//					UiApplication.getUiApplication().pushScreen(new DashboardScreen());
//					UiApplication.getUiApplication().popScreen(signinScreen);
//				}
//			});} catch(Exception ex){
//				System.out.println("Exception @ UserController.httpsuccess : " + ex.getClass().getName());
//				ex.printStackTrace();
//			}
			
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