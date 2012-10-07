package app.controllers;

import net.rim.device.api.ui.UiApplication;
import app.AirCrewApp;
import app.views.screens.DashboardScreen;

public class DashboardController {
	
	private AirCrewApp app = (AirCrewApp)UiApplication.getUiApplication();
	private DashboardScreen dashboardScreen;
	private DealController dealController;
	private BookmarkController bookmarkController;
	private FavoritesController favoritesController;
	private ProfileController profileController;
	
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
	
	public BookmarkController getBookmarkController(){
		if (bookmarkController == null){
			bookmarkController = new BookmarkController(this);
		}
		return bookmarkController;
	}
	
	public ProfileController getProfileController(){
		return ProfileController.getInstance();
	}
	
	public FavoritesController getFavoritesController(){
		return FavoritesController.getInstance();
	}
}
