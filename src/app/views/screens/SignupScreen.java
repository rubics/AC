package app.views.screens;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CompositeFieldManager;
import rubyx.custom_fields.CompositeObjectChoiceField;
import rubyx.custom_fields.CompositePasswordBox;
import rubyx.custom_fields.CompositeTextBox;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import rubyx.tabbedUI.TabbedButtonManager;
import app.AirCrewApp;
import app.models.Images;
import app.views.fields.ScreenTitle;

public class SignupScreen extends MainScreen{
	
	private CompositeTextBox nameField;
	private CompositeTextBox emailIdField;
	private CompositePasswordBox passwordField;
	private CompositePasswordBox confirmPasswordField;
	private Field airlineField;
	private CompositeTextBox designationField;
	private CompositeObjectChoiceField genderField;
	private CompositeTextBox locationField;
	private TabbedButton signUpButton;
	private TabbedButton backButton;
	private Manager mvrm;
	
	private String[] airlines =  {"Indian", "Kingfisher", "Deccan Air"};
	private String[] gender = {"Male", "Female"};
	
	public SignupScreen(long style){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_HORIZONTAL_SCROLLBAR);
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		setTitle(new ScreenTitle("Sign Up"));
		
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
		Manager tabbedButtonManager = new TabbedButtonManager(470, 40);
		signUpButton = new TabbedButton("Sign Up", 6);
		backButton = new TabbedButton("Cancel", 6);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		tabbedButtonManager.add(signUpButton);
		tabbedButtonManager.add(backButton);
		
		mvrm.add(tabbedButtonManager);
		mvrm.add(new SpaceField(10));
		add(mvrm);
		
	}
	
	public boolean isDirty() {
	    return false;
	}
}
