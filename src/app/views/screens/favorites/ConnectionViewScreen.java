package app.views.screens.favorites;


import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.NullField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CustomButton;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.custom_fields.WebImageField;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.AirCrew;
import app.models.Identity;
import app.models.Images;
import app.views.fields.favorites.ProfileDetails;
import app.views.fields.favorites.ProfileView;
import app.views.screens.chat.ChatScreen;

public class ConnectionViewScreen extends MainScreen{
	private ScreenBannar screenBannar;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrm;
	public WebImageField profileImage;
	private ProfileView profileView;
	private TabbedButton blockUser;
	private TabbedButton addToFavorites;
	public Identity[] identities;
	public int current_index = 0;
	private ConnectionViewScreen this_screen;
	
	public ConnectionViewScreen(Identity[] identities, int identity_index){
		
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		this_screen = this;
		this.identities = identities;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(screenBannar = new ScreenBannar(identities[identity_index].getUser_name(), 40, backButton, homeButton));
		current_index = identity_index;

		vrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		add(vrm);
		
		// tabbed button
		CustomButton chatButton = new CustomButton(Images.chatScreenIcons[0], 160, 50);
		chatButton.setChangeListener(chatButtonListener);
		CustomButton infoButton = new CustomButton(Images.chatScreenIcons[1], 160, 50);
		CustomButton galleryButton = new CustomButton(Images.chatScreenIcons[2], 160, 50);
		galleryButton.setChangeListener(galleryButtonListener);
		
		HorizontalFieldManager tabbedButtonManager = new HorizontalFieldManager();
		tabbedButtonManager.add(chatButton);
		tabbedButtonManager.add(infoButton);
		tabbedButtonManager.add(galleryButton);
		
		setStatus(tabbedButtonManager);
		
		drawConnection();
	}
	
	private void drawConnection(){
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			public void run() {
				delete(vrm);
				screenBannar.setLabel(identities[current_index].getUser_name());
				vrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				profileImage = new WebImageField(AirCrew.user_images + identities[current_index].getImage_name(), 180, 180);
				profileView = new ProfileView(0,this_screen);
				vrm.add(profileView);
				vrm.add(new ProfileDetails(identities[current_index]));
				vrm.add(new NullField());
				
				HorizontalFieldManager hrManager = new HorizontalFieldManager();
				blockUser = new TabbedButton("Block User", 6, 240, 40);
				addToFavorites = new TabbedButton("Remove From Favorites", 6, 240, 40);
				hrManager.add(blockUser);
				hrManager.add(addToFavorites);
				vrm.add(hrManager);
				vrm.add(new SpaceField(10));
				add(vrm);
			}
		});
	}
	
	public FieldChangeListener galleryButtonListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ConnectionGalleryScreen connectionGallery = new ConnectionGalleryScreen(identities[current_index]);
			UiApplication.getUiApplication().pushScreen(connectionGallery);
		}
	};
	
	public FieldChangeListener chatButtonListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ChatScreen chatScreen = new ChatScreen(identities[current_index]);
			UiApplication.getUiApplication().pushScreen(chatScreen);
		}
	};
	
	public FieldChangeListener nextProfileListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			int x = (current_index+1 <= identities.length-1) ? ++current_index : current_index;
			drawConnection();
//			UiApplication.getUiApplication().pushScreen(new ProfileViewScreen(connection));
		}
	};
	
	public FieldChangeListener previousProfileListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			int x = (current_index-1 >= 0) ? --current_index : current_index;
			drawConnection();
//			UiApplication.getUiApplication().pushScreen(new ProfileViewScreen(connection));
		}
	};
	
	public boolean isDirty() {
	    return false;
	}
}