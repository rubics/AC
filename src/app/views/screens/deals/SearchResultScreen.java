package app.views.screens.deals;

import net.rim.device.api.lbs.MapField;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import rubyx.tabbedUI.TabbedButtonManager;
import app.AirCrewApp;
import app.controllers.user.DealController;
import app.models.Business;
import app.models.Deal;
import app.models.Images;
import app.views.fields.listings.ListingField;
import app.views.managers.deals.AboutDealScreenManager;

public class SearchResultScreen extends MainScreen{
	
	private DealController dealController;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private int manager_index;
	private final MainScreen current_screen;
	public static Bitmap[] profile_pics = Images.profile_pics;
	public static String[] names = {"Luxe Dental Clinic","Happy Habitat Petcare","Jacques La Coupe","Dine In Resturant"};
	public static String[] category = {"Dental Care For Your Lifestyle","Petcare","Beauty Salon","Dining"};
	public static String[] description = {"Welcome to the Luxe Dental Clinic, the centre of luxury for cosmetic dentistry and Smile design in the	heart of Dubai.",
								"We founded Happy Habitat (a professional Pet Sitting Company) out of the desire to provide THE BEST 'at home' pet care solutions to Pet Owners at affordable prices.",
								"In 1996, Avenue Kleber in Paris France, witnessed the launching of the ultimate luxurious La Coupe Beauty Salon.",
								"Jump to casual dining, a casual dining resturant is a resturant that serves moderately priced food in a casual atmosphere."};
	
	private VerticalFieldManager vrm;
		
	private FieldChangeListener tabbedViewListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			switch (field.getIndex()){
			case 0:
				UiApplication.getUiApplication().invokeLater(new Runnable() {					
					public void run() {
						current_screen.delete(vrm);
						updateScreen();						
					}
				});
				break;
			case 1:
			default:
				UiApplication.getUiApplication().invokeLater(new Runnable() {					
					public void run() {
						current_screen.delete(vrm);
						drawMap();						
					}
				});
				break;
			}
		}
	};
	private FieldChangeListener listItemListener = new FieldChangeListener() {		
		public void fieldChanged(Field field, int context) {
			int in = (field.getIndex()) % 3;
			AboutDealScreenManager aboutDeals = new AboutDealScreenManager(new Business(names[in], category[in], description[in], profile_pics[in]));
			aboutDeals.pushScreen();			
		}
	};
		
	public SearchResultScreen(DealController _dealController){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL);
		current_screen = this;
		dealController = _dealController;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("Deals", 40, backButton, homeButton));
		
		vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
		
		add(new SpaceField(5));
		TabbedButtonManager statusButtonGroup = new TabbedButtonManager(480, 42, true, 2);
		Field sa = new TabbedButton("List View",6);
		Field sb = new TabbedButton("Map View",6);
		sa.setChangeListener(tabbedViewListener);
		sb.setChangeListener(tabbedViewListener);
		statusButtonGroup.add(sa);
		statusButtonGroup.add(sb);
		add(statusButtonGroup);
		add(new SpaceField(3));
		updateScreen();
	}
	
	public void updateScreen(){
		vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
		VerticalFieldManager listManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		for(int i=0; i < dealController.getDeals().length; i++){
			Deal deal = dealController.getDeals()[i];
			Field listItem = new ListingField(Images.profile_pics[1], deal.getName(), deal.getCategory_name(),8);
			listItem.setChangeListener(listItemListener);
			listManager.add(listItem);
		}
		vrm.add(listManager);
		add(vrm);
	}
	
	public void drawMap(){
		vrm = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
		VerticalFieldManager listManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR | Manager.USE_ALL_WIDTH);
		MapField mapField = new MapField(Field.FIELD_HCENTER | Field.USE_ALL_WIDTH);
		mapField.setPreferredSize(460, 200);
		mapField.moveTo(4500000, 9000000);
		mapField.setZoom(1);
		listManager.add(mapField);
		vrm.add(listManager);
		add(vrm);
	}
	
	public boolean isDirty() {
	    return false;
	}
}