package app.controllers;

import helpers.org.json.me.JSONObject;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import app.AirCrewApp;
import app.models.SigninRequest;
import app.models.SignoutRequest;
import app.models.SignupRequest;
import app.models.User;
import app.views.screens.DashboardScreen;
import app.views.screens.SigninScreen;
import app.views.screens.SignupScreen;

public class UserController {

	private User user;
	private SigninScreen signinScreen;
	private SignupScreen signupScreen;
	
	public UserController(){}

	public void pushSignInScreen(boolean remember_me){
		
		signinScreen = new SigninScreen(this);
		AirCrewApp.app.pushScreen(signinScreen);
	}
	
	public void pushSignupScreen(){
		signupScreen = new SignupScreen(this);
		AirCrewApp.app.pushScreen(signupScreen);		
	}
	
	public User getUser(){
		return user;
	}
	
	
	public void setUser(User user){
		this.user = user;
	}
	
	public void registerUser(User user, boolean remember_me){
		this.user = user;
		if(remember_me){
			AirCrewApp.app.persistenceController.persistUser(user);
		} else {
			AirCrewApp.app.persistenceController.removeUser();
		}
	}
	
	//------------------------------------------------------------------------------//
	
	public SigninRequest signinRequest = new SigninRequest() {

		public void httpsuccess(byte[] array, String str){

			System.out.println("-------------	RESPONSE	------------");
			final String json_response = new String(array);
			System.out.println("-------------------------");
			System.out.println(json_response);
			try{
				JSONObject json = new JSONObject(json_response);

				if(json.has("response") & !json.isNull("response")) {

					JSONObject response = json.getJSONObject("response");
					String user_name = response.getString("username");
					String user_id = response.getString("userId");
					String session_id = response.getString("sessionId");
					String message = response.getString("message");
										
					user = new User(user_name, user_id, session_id, signinScreen.getPassword());
					
					AirCrewApp.app.pushDashboardScreen(); // push Dashboard Screen
					
					UiApplication.getUiApplication().invokeAndWait(new Runnable() {
						public void run() {
							AirCrewApp.app.popScreen(signinScreen);
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
	
	public SignupRequest signupRequest = new SignupRequest() {
		
		public void httpsuccess(byte[] array, String str) {
			
			final String json_response = new String(array);
			
			try{
				JSONObject json = new JSONObject(json_response);

				if(json.has("response") & !json.isNull("response")) {

					JSONObject response = json.getJSONObject("response");
					String user_name = response.getString("username");
					String user_id = response.getString("userId");
					final String message = response.getString("message");
					
					UiApplication.getUiApplication().invokeAndWait(new Runnable() {
						
						public void run() {
							UiApplication.getUiApplication().popScreen(signupScreen);
							Dialog.inform(message);
						}		
					});

				} else if (json.has("error") & !json.isNull("error")){
					JSONObject response = json.getJSONObject("error");
					final String code = response.getString("code");
					final String message = response.getString("message");
					
					UiApplication.getUiApplication().invokeAndWait(new Runnable() {
						
						public void run() {
							UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
							pushSignInScreen(false);
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
	
	public SignoutRequest signoutRequest = new SignoutRequest() {
		
		public void httpsuccess(byte[] array, String str) {
			
			final String json_response = new String(array);
			
			try{
				JSONObject json = new JSONObject(json_response);

				if(json.has("response") & !json.isNull("response")) {

					JSONObject response = json.getJSONObject("response");
					final String message = response.getString("message");
					AirCrewApp.app.persistenceController.removeUser();
					UiApplication.getUiApplication().invokeAndWait(new Runnable() {
						
						public void run() {
							UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
							pushSignInScreen(false);
//							Dialog.inform(message);
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
}