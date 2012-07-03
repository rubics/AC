package app.views.screens.profile;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CompositeFieldManager;
import rubyx.custom_fields.CompositeObjectChoiceField;
import rubyx.custom_fields.CompositePasswordBox;
import rubyx.custom_fields.CompositeTextBox;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.Images;
import app.views.managers.profile.ProfileInfoScreenManager;

public class ProfileScreen extends MainScreen{
	private ProfileInfoScreenManager profileInfo;
	private CompositeTextBox nameField;
	private CompositeTextBox emailIdField;
	private CompositePasswordBox passwordField;
	private CompositePasswordBox confirmPasswordField;
	private Field airlineField;
	private CompositeTextBox designationField;
	private CompositeObjectChoiceField genderField;
	private CompositeTextBox locationField;
	private TabbedButton saveButton;
	private Manager mvrm;
	
	private String[] airlines =  {"Indian", "Kingfisher", "Deccan Air"};
	private String[] gender = {"Male", "Female"};
	private TabbedButton backButton;
	private TabbedButton homeButton;
	
	public ProfileScreen(ProfileInfoScreenManager _profileInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		profileInfo = _profileInfo;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("My Profile", 40, backButton, homeButton));
		
		mvrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL|Manager.VERTICAL_SCROLLBAR);
		nameField = new CompositeTextBox("Username","",true);
		emailIdField = new CompositeTextBox("email", "", true);
		passwordField = new CompositePasswordBox("Password", "", true);
		confirmPasswordField = new CompositePasswordBox("Confirm Password", "", true);
		airlineField = new CompositeObjectChoiceField("Airlines", airlines,0);
		designationField = new CompositeTextBox("Designation", "", true);
		genderField = new CompositeObjectChoiceField("Gender", gender,0);
		locationField = new CompositeTextBox("Location", "", true);
		
		CompositeFieldManager manager = new CompositeFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		
		manager.add(nameField);
		manager.add(emailIdField);
		manager.add(passwordField);
		manager.add(confirmPasswordField);
		manager.add(airlineField);
		manager.add(designationField);
		manager.add(genderField);
		manager.add(locationField);
		
		mvrm.add(manager);
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
