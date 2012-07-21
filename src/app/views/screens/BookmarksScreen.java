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
import app.models.DealDetails;
import app.models.Images;
import app.views.fields.listings.ListingField;
import app.views.managers.deals.DealDetailsScreenManager;

public class BookmarksScreen extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;
	
	Bitmap[] images = Images.profile_pics;
	public static String[] names = {"Luxe Dental Clinic","Happy Habitat Petcare","Jacques La Coupe","Dine In Resturant"};
	public static String[] category = {"Dental Care For Your Lifestyle","Petcare","Beauty Salon","Dining"};
	public static String[] description = {"Welcome to the Luxe Dental Clinic, the centre of luxury for cosmetic dentistry and Smile design in the	heart of Dubai.",
								"We founded Happy Habitat (a professional Pet Sitting Company) out of the desire to provide THE BEST 'at home' pet care solutions to Pet Owners at affordable prices.",
								"In 1996, Avenue Kleber in Paris France, witnessed the launching of the ultimate luxurious La Coupe Beauty Salon.",
								"Jump to casual dining, a casual dining resturant is a resturant that serves moderately priced food in a casual atmosphere."};

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
	
	private FieldChangeListener listItemListener = new FieldChangeListener() {		
		public void fieldChanged(Field field, int context) {
			int in = (field.getIndex()) % 3;
//			DealDetailsScreenManager aboutDeals = new DealDetailsScreenManager(new Deal(/* Populate list of deals using bookmark api*/));
//			aboutDeals.pushScreen();			
		}
	};
}
