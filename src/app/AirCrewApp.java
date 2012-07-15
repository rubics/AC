package app;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import app.controllers.user.DashboardController;
import app.controllers.user.UserController;
import app.views.screens.SplashScreen;

public class AirCrewApp extends UiApplication {
	
	public static AirCrewApp app;
	private UserController userController;
	public DashboardController dashboardController;
	
	private AirCrewApp(){
		
		app = this;
		userController = new UserController();
		userController.pushSignInScreen();
		
		SplashScreen splash = new SplashScreen((float) 0.5);
		pushScreen(splash);
	}
	
	public static void main(String [] args){
		AirCrewApp app = new AirCrewApp();
		app.enterEventDispatcher();
	}
	
	public static final FieldChangeListener backButtonListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().popScreen(field.getScreen());
		}
	};
	
	public UserController getUserController(){
		return userController;
	}
	
}
