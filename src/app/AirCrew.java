package app;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import app.views.screens.SigninScreen;
import app.views.screens.SplashScreen;

public class AirCrew extends UiApplication {
	
	private static AirCrew app;
	
	public static AirCrew createAirCrewInstance(){
		if(app == null)
			return new AirCrew();
		else
			return app;
	}

	protected AirCrew(){
		SigninScreen signinScreen = new SigninScreen();
		pushScreen(signinScreen);
		
		SplashScreen splash = new SplashScreen((float) 0.5);
		pushScreen(splash);
	}
	
	public static void main(String [] args){
		AirCrew app = AirCrew.createAirCrewInstance();
		app.enterEventDispatcher();
	}
	
	public static final FieldChangeListener backButtonListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().popScreen(field.getScreen());
		}
	};
}
