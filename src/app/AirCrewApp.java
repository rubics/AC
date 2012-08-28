package app;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import app.controllers.DashboardController;
import app.controllers.UserController;
import app.models.AirCrewResources;
import app.views.screens.SplashScreen;

public class AirCrewApp extends UiApplication {
	
	public static AirCrewApp app;
	private UserController userController;
	public DashboardController dashboardController;
	public AirCrewResources resources;
	
	private AirCrewApp(){
		
		app = this;
		
		resources = new AirCrewResources();
		
		userController = new UserController();
		userController.pushSignInScreen();
		
		SplashScreen splash = new SplashScreen((float) 0.5);
		pushScreen(splash);
	}
	
	public static void main(String [] args){
		AirCrewApp app = new AirCrewApp();
		app.enterEventDispatcher();
	}
	
	public void pushDashboardScreen(){  // to be called in UIEvent thread with invokeLater()
		UiApplication.getUiApplication().invokeAndWait(new Runnable() {			
			public void run() {
				dashboardController = new DashboardController();
				dashboardController.pushScreen();
			}
		});
	}
	
	public UserController getUserController(){
		return userController;
	}
	
	public static final FieldChangeListener backButtonListener = new FieldChangeListener() {	
		public void fieldChanged(Field field, int context) {
			app.popScreen(field.getScreen());
		}
	};
}
