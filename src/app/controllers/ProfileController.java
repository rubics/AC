package app.controllers;

import app.AirCrewApp;
import app.models.GalleryImage;
import app.models.GalleryRequest;
import app.models.Profile;
import app.models.Roster;
import app.views.managers.profile.ProfileInfoScreenManager;
import app.views.screens.profile.GalleryScreen;

public class ProfileController {

	private ProfileInfoScreenManager profileInfoScreenManager;
	private static ProfileController profileController;
	private GalleryImage[] galleryImages;
	private Profile profile;
	private Roster roster;
	
	public static ProfileController getInstance(){
		if (profileController == null)
			profileController = new ProfileController();
		return profileController;
	}
	
	private ProfileController(){
		profileController = this;
		profileInfoScreenManager = new ProfileInfoScreenManager(this);
		
		updateGallery();
	}
	
	public void updateGallery(){
		GalleryRequest galleryRequest = new GalleryRequest() {	
			public void onSuccess(GalleryImage[] _galleryImages) {
				galleryImages = _galleryImages;
				drawGalleryScreen();
			}
			
			public void onDefaultImage(String image){}
		};
		galleryRequest.getUserImages(AirCrewApp.app.getUserController().getUser().getUserId());
	}
	
	public void drawGalleryScreen(){
		GalleryScreen galleryScreen = (GalleryScreen)profileInfoScreenManager.getTabbedScreens()[2];
		galleryScreen.showGallery(galleryImages);	
	}
	
	public void pushScreen(){
		profileInfoScreenManager.pushScreen(0);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Roster getRoster() {
		return roster;
	}
	
	public void setRoster(Roster roster){
		this.roster = roster;
	}
}
