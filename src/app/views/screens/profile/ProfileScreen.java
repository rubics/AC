package app.views.screens.profile;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ObjectChoiceField;
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
import app.models.AirCrewResources;
import app.models.Airline;
import app.models.City;
import app.models.CityRequest;
import app.models.Country;
import app.models.Images;
import app.models.MyProfileRequest;
import app.models.Profile;
import app.models.UpdateProfileRequest;
import app.views.managers.profile.ProfileInfoScreenManager;

public class ProfileScreen extends MainScreen{
	private ProfileInfoScreenManager profileInfo;
	private final ProfileScreen currentScreen;
	
	private CompositeTextBox nameField;
	private CompositeTextBox emailIdField;
	private CompositePasswordBox passwordField;
	private CompositeObjectChoiceField airlineField;
	private CompositeObjectChoiceField designationField;
	private CompositeObjectChoiceField genderField;
	private CompositeObjectChoiceField countryField;
	private CompositeObjectChoiceField cityField;
	private TabbedButton saveButton;
	
	private Manager vrManager;
	CompositeFieldManager manager;
	
	private String[] gender = {"Male", "Female"};
	private TabbedButton backButton;
	private TabbedButton homeButton;
	
	private MyProfileRequest myProfileRequest;
	
	private City[] cities = {new City("Select a City", "Select a City")};
	
	public ProfileScreen(ProfileInfoScreenManager _profileInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		profileInfo = _profileInfo;
		currentScreen = this;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("My Profile", 40, backButton, homeButton));
		
		myProfileRequest = new MyProfileRequest() {
			
			public void afterSuccess(Profile profile) {
				profileInfo.getProfileController().setProfile(profile);
				updateScreen();
			}
		};
		myProfileRequest.getMyProfileInfo();
		
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL|Manager.VERTICAL_SCROLLBAR);
		add(vrManager);
	}
	
	public void updateScreen(){
		final Profile profile = profileInfo.getProfileController().getProfile();
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
				
				currentScreen.delete(vrManager);
				
				vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL|Manager.VERTICAL_SCROLLBAR);
				
				nameField = new CompositeTextBox("Username",profile.getUser_name(),true);
				emailIdField = new CompositeTextBox("email", profile.getUser_email(), true);
				passwordField = new CompositePasswordBox("Password", AirCrewApp.app.getUserController().getUser().getPassword(), true);
				airlineField = new CompositeObjectChoiceField("Airlines",AirCrewResources.airlines,AirCrewResources.getAirlineIndex(profileInfo.getProfileController().getProfile().getAirline_id()));
				designationField = new CompositeObjectChoiceField("Designation", AirCrewResources.designations,AirCrewResources.getDestinationIndex(profileInfo.getProfileController().getProfile().getDesignation()));
				genderField = new CompositeObjectChoiceField("Gender", AirCrewResources.genders,AirCrewResources.getGenderIndex(profileInfo.getProfileController().getProfile().getGender()));
				countryField = new CompositeObjectChoiceField("Country", AirCrewResources.countries, AirCrewResources.getCountryIndex(profileInfo.getProfileController().getProfile().getCountry_code()));
				countryField.setListener(countryChoiceListener);
				cityField = new CompositeObjectChoiceField("City", cities, 0);
				
				manager = new CompositeFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				
				manager.add(nameField);
				manager.add(emailIdField);
				manager.add(passwordField);
				manager.add(airlineField);
				manager.add(designationField);
				manager.add(genderField);
				manager.add(countryField);
				manager.add(cityField);
				
				vrManager.add(manager);
				saveButton = new TabbedButton("Save", 7, 470, 40);
				saveButton.setChangeListener(saveProfileListener);
				saveButton.setRVAlue(12);
				vrManager.add(saveButton);
				vrManager.add(new SpaceField(10));
				add(vrManager);
			}
		});
	}
	
	public void updateCityChoiceField(){
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
				manager.delete(cityField);
				cityField = new CompositeObjectChoiceField("City", cities, 0);
				manager.add(cityField);
				currentScreen.invalidate();
			}
		});
	}
	
	private FieldChangeListener countryChoiceListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			try{
				if(ObjectChoiceField.class.isInstance(field)){
					ObjectChoiceField choiceField = (ObjectChoiceField)(field);
					Country country = AirCrewResources.countries[choiceField.getSelectedIndex()];
			
					CityRequest cityRequest = new CityRequest(){
						public void afterSuccess(City[] _cities){
							cities = _cities;
							updateCityChoiceField();
						}
					};
					cityRequest.getCity(country.getCountry_code());
				}
			} catch(Exception e){
				System.out.println();
			}
		}
	};
	
	private FieldChangeListener saveProfileListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			StringBuffer postString = new StringBuffer();
			postString.append("user_id=");
			postString.append(AirCrewApp.app.getUserController().getUser().getUserId());
			postString.append("&user_name=");
			postString.append(nameField.getEditFieldText());
			postString.append("&designation=");
			postString.append(designationField.getSelectedIndex());
			postString.append("&gender=");
			postString.append(genderField.getSelectedIndex());
			postString.append("&airline=");
			postString.append(((Airline)(airlineField.getSelectedObject())).getId());
			postString.append("&country=");
			postString.append(((Country)(countryField.getSelectedObject())).getCountry_code());
			postString.append("&city=");
			postString.append(((City)(cityField.getSelectedObject())).getCity_id());
			
			UpdateProfileRequest updateProfile = new UpdateProfileRequest();
			updateProfile.updateProfile(postString.toString());
		}
	};
	
	public boolean isDirty() {
	    return false;
	}
}
