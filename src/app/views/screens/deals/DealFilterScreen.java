package app.views.screens.deals;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CompositeButton;
import rubyx.custom_fields.CompositeField;
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
		
		TabbedButtonManager statusButtonGroup = new TabbedButtonManager(480, 42, true, 2);
		statusButtonGroup.add(new TabbedButton("Online",6));
		statusButtonGroup.add(new TabbedButton("Offline",6));
		statusButtonGroup.add(new TabbedButton("List All",6));
		statusButtonGroup.setColorScheme(0xeeeeee, 0x186DEF, 0xF57000, Color.BLACK);
		
		vrManager.add(statusButtonGroup);
		vrManager.add(new SpaceField(3));
		
		TabbedButtonManager genderButtonGroup = new TabbedButtonManager(480, 42, true, 2);
		genderButtonGroup.add(new TabbedButton("Male",6));
		genderButtonGroup.add(new TabbedButton("Female",6));
		genderButtonGroup.add(new TabbedButton("List All",6));
		genderButtonGroup.setColorScheme(0xeeeeee, 0x186DEF, 0xF57000, Color.BLACK);
		
		vrManager.add(genderButtonGroup);
		vrManager.add(new SpaceField(3));
		
		TabbedButtonManager crewButtonGroup = new TabbedButtonManager(480, 42, true, 2);
		crewButtonGroup.add(new TabbedButton("Cabin Crew",6));
		crewButtonGroup.add(new TabbedButton("Flight Crew",6));
		crewButtonGroup.add(new TabbedButton("List All",6));
		crewButtonGroup.setColorScheme(0xeeeeee, 0x186DEF, 0xF57000, Color.BLACK);
		
		vrManager.add(crewButtonGroup);
		vrManager.add(new SpaceField(3));
	
		CompositeButton airline = new CompositeButton("Airline");
		airline.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
		vrManager.add(airline);
		vrManager.add(new SpaceField(3));
		
		CompositeButton country = new CompositeButton("Country");
		country.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
		vrManager.add(country);
		vrManager.add(new SpaceField(3));
		
		CompositeButton city = new CompositeButton("City");
		city.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
		vrManager.add(city);
		vrManager.add(new SpaceField(3));
	
		TabbedButton filterButton = new TabbedButton("Filter", 7, 480, 40);
		filterButton.setRVAlue(12);
		vrManager.add(filterButton);
		vrManager.add(new SpaceField(3));
		
		add(vrManager);
	}
	
	public boolean isDirty() {
	    return false;
	}
}