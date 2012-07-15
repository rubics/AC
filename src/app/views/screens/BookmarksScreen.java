package app.views.screens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.Business;
import app.models.Images;
import app.views.fields.listings.ListingField;
import app.views.managers.deals.AboutDealScreenManager;
import app.views.screens.deals.SearchResultScreen;

public class BookmarksScreen extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;
	
	private FieldChangeListener listItemListener = new FieldChangeListener() {		
		public void fieldChanged(Field field, int context) {
			int in = (field.getIndex()) % 3;
			AboutDealScreenManager aboutDeals = new AboutDealScreenManager(new Business(SearchResultScreen.names[in], SearchResultScreen.category[in], SearchResultScreen.description[in], SearchResultScreen.profile_pics[in]));
			aboutDeals.pushScreen();			
		}
	};

	public BookmarksScreen(){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("BookMarks", 40, backButton, homeButton));
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				
		Bitmap[] images = SearchResultScreen.profile_pics;
		String[] names = SearchResultScreen.names;
		String[] category = SearchResultScreen.category;
		for (int i=0; i<images.length; i++){
			Field field = new ListingField(images[i], names[i], category[i], ListingField.STATUS_NONE);
			field.setChangeListener(listItemListener);
			vrManager.add(field);
		}
		add(vrManager);
	}
	public boolean isDirty() {
	    return false;
	}
}
