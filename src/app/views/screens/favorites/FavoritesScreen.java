package app.views.screens.favorites;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.controllers.FavoritesController;
import app.models.Images;
import app.views.fields.listings.ListingField;
import app.views.screens.profile.GalleryScreen;

public class FavoritesScreen extends MainScreen{
	
	private FavoritesController favoritesController;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;
	private FavoritesScreen this_screen;
	
	public FavoritesScreen(FavoritesController favoritesController){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		this.favoritesController = favoritesController;
		this_screen = this;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("Favorites", 40, backButton, homeButton));
		
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		add(vrManager);
	}
	
	public FieldChangeListener listener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().pushScreen(new ProfileViewScreen(GalleryScreen.images[field.getIndex()]));
		}
	};
	
	public void updateFavoritesScreen(){
		UiApplication.getUiApplication().invokeAndWait(new Runnable() {
			
			public void run() {
				this_screen.delete(vrManager);
				vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				
				for(int i=0; i<favoritesController.getConnections().length; i++){
//					ListingField field = new ListingField(_bitmap, _title, _description, _online)
				}
				add(vrManager);
			}
		});
	}
	public boolean isDirty() {
	    return false;
	}
}
