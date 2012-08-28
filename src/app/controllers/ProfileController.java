package app.controllers;

import app.AirCrewApp;
import app.models.GalleryImage;
import app.models.GalleryRequest;
import app.models.Profile;
import app.views.managers.profile.ProfileInfoScreenManager;
import app.views.screens.profile.GalleryScreen;

public class ProfileController {

	private ProfileInfoScreenManager profileInfoScreenManager;
	private ProfileController profileController;
	private GalleryImage[] galleryImages;
	private Profile profile;
		
	public ProfileController(){
		profileController = this;
		profileInfoScreenManager = new ProfileInfoScreenManager(this);
		
		GalleryRequest galleryRequest = new GalleryRequest() {	
			public void onSuccess(GalleryImage[] _galleryImages) {
				galleryImages = _galleryImages;
				drawGalleryScreen();
			}
		};
		galleryRequest.getUserImages(AirCrewApp.app.getUserController().getUser().getUserId());
	}
	
	public void drawGalleryScreen(){
		GalleryScreen galleryScreen = (GalleryScreen)profileInfoScreenManager.getTabbedScreens()[2];
		galleryScreen.showGallery(galleryImages);	
	}
	
	public void pushScreen(){
		profileInfoScreenManager.pushScreen(2);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
