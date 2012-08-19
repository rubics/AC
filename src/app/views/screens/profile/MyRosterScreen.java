package app.views.screens.profile;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CheckboxManager;
import rubyx.custom_fields.CompositeButton;
import rubyx.custom_fields.CompositeField;
import rubyx.custom_fields.CompositeFieldManager;
import rubyx.custom_fields.CompositeObjectChoiceField;
import rubyx.custom_fields.CompositeSpinBox;
import rubyx.custom_fields.CustomDateField;
import rubyx.custom_fields.RoundedBackgroundManager;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.AirCrewResources;
import app.models.Images;
import app.views.managers.profile.ProfileInfoScreenManager;

public class MyRosterScreen extends MainScreen{
	private ProfileInfoScreenManager profileInfo;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	
	private CompositeObjectChoiceField locationField;
	private CustomDateField dateFrom;
	private CustomDateField dateTo;
	private CompositeButton detailsField;
	private CheckboxField allProfile;
	private CheckboxField myFavorites;
	private TabbedButton saveButton;
	private VerticalFieldManager mvrm;
	
	public MyRosterScreen(ProfileInfoScreenManager _profileInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		profileInfo = _profileInfo;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("My Roster", 40, backButton, homeButton));
		mvrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL|Manager.VERTICAL_SCROLLBAR);
		locationField = new CompositeObjectChoiceField("Location",AirCrewResources.countries,0);
		dateFrom = new CustomDateField("Date From", System.currentTimeMillis(), DateField.DATE);
		dateTo = new CustomDateField("Date To", System.currentTimeMillis(), DateField.DATE);
		detailsField = new CompositeButton("Details");
		
		CompositeFieldManager manager = new CompositeFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		
		manager.add(locationField);
		manager.add(dateFrom);
		manager.add(dateTo);
		manager.add(detailsField);
		mvrm.add(manager);
		
		CheckboxManager checkboxManager = new CheckboxManager(Manager.VERTICAL_SCROLL|Manager.VERTICAL_SCROLLBAR, Display.getWidth(), 90);
		
		allProfile = new CheckboxField("All Profiles", false, Field.FIELD_HCENTER){
			protected void paint(Graphics g){
				g.setColor(Color.BLACK);
				super.paint(g);
			}
		};
		allProfile.setFont(RoundedBackgroundManager.font_composite_label);
		myFavorites = new CheckboxField("My Favorites", false, Field.FIELD_HCENTER){
			protected void paint(Graphics g){
				g.setColor(Color.BLACK);
				super.paint(g);
			}
		};
		myFavorites.setFont(RoundedBackgroundManager.font_composite_label);
		Field labelField = new LabelField("Who can view my roster ?");
		labelField.setFont(RoundedBackgroundManager.font_composite_label);
		checkboxManager.add(labelField);
		checkboxManager.add(allProfile);
		checkboxManager.add(myFavorites);
		mvrm.add(checkboxManager);
		
		saveButton = new TabbedButton("Save", 7, 470, 40);
		saveButton.setRVAlue(12);
		mvrm.add(saveButton);
		mvrm.add(new SpaceField(10));
		
		add(mvrm);
	}
	
	public boolean isDirty() {
	    return false;
	}
}
