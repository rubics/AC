package app.controllers.user;

import net.rim.device.api.ui.UiApplication;
import app.AirCrewApp;
import app.views.screens.DashboardScreen;

public class DashboardController {
	
	private AirCrewApp app = (AirCrewApp)UiApplication.getUiApplication();
	private DashboardScreen dashboardScreen;
	private DealController dealController;
	
	public DashboardController(){		
		dashboardScreen = new DashboardScreen(this);
	}
	
	public void pushScreen(){
		app.pushScreen(dashboardScreen);
	}
	
	public DealController getDealController(){
		if(dealController == null)
			dealController = new DealController(this);
		return dealController;
	}
}
