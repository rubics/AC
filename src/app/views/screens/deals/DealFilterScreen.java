package app.views.screens.deals;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CompositeButton;
import rubyx.custom_fields.CompositeField;
import rubyx.custom_fields.CompositeObjectChoiceField;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import rubyx.tabbedUI.TabbedButtonManager;
import app.AirCrewApp;
import app.controllers.user.DealController;
import app.models.Images;

public class DealFilterScreen extends MainScreen{
	
	private DealController dealController;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private CompositeObjectChoiceField categoryChoiceField;
	private CompositeObjectChoiceField countryChoiceField;
	private CompositeObjectChoiceField cityChoiceField;
	private TabbedButton filterButton;
	
	private String[] categories = {"Health", "Entertainment", "Travel", "Adventure", "Food and Dinining"};
	private String[] countries = {"Nepal", "India", "China", "USA", "UK"};
	private String[] city = {"Kathmandu","Pokhar", "Dharan", "Biratnager", "Birgunj"};
	
	private VerticalFieldManager vrManager;
	
	public DealFilterScreen(DealController _dealController){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		dealController = _dealController;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("Filter Deals", 40, backButton, homeButton));
		
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		
		vrManager.add(new SpaceField(8));
		categoryChoiceField = new CompositeObjectChoiceField("Category", categories, 0);
		categoryChoiceField.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
		vrManager.add(categoryChoiceField);
		vrManager.add(new SpaceField(5));
		countryChoiceField = new CompositeObjectChoiceField("Country", countries, 0);
		countryChoiceField.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
		vrManager.add(countryChoiceField);
		vrManager.add(new SpaceField(5));
		cityChoiceField = new CompositeObjectChoiceField("City", city, 0);
		cityChoiceField.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
		vrManager.add(cityChoiceField);
		vrManager.add(new SpaceField(45));
		filterButton = new TabbedButton("Filter", 7, 480, 42);
		filterButton.setRVAlue(15);
		vrManager.add(filterButton);
		add(vrManager);
	}
	
	public boolean isDirty() {
	    return false;
	}
}