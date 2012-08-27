package app.views.screens.profile;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CheckboxManager;
import rubyx.custom_fields.CompositeButton;
import rubyx.custom_fields.CompositeFieldManager;
import rubyx.custom_fields.CompositeObjectChoiceField;
import rubyx.custom_fields.CustomDateField;
import rubyx.custom_fields.RoundedBackgroundManager;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.AirCrewResources;
import app.models.City;
import app.models.CityRequest;
import app.models.Country;
import app.models.Images;
import app.models.UpdateRosterRequest;
import app.views.managers.profile.ProfileInfoScreenManager;

public class MyRosterScreen extends MainScreen{
	private ProfileInfoScreenManager profileInfo;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private MainScreen currentScreen;
	private CompositeObjectChoiceField countryField;
	private CompositeObjectChoiceField cityField;
	private CustomDateField dateFrom;
	private CustomDateField dateTo;
	private CompositeButton detailsField;
	private CheckboxField allProfile;
	private CheckboxField myFavorites;
	private int checkboxPreference = 0;
	private TabbedButton saveButton;
	
	CompositeFieldManager manager;
	private VerticalFieldManager mvrm;
	
	private City[] cities = {new City("Select a City", "Select a City")};
	
	public MyRosterScreen(ProfileInfoScreenManager _profileInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		currentScreen = this;
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
		countryField = new CompositeObjectChoiceField("Country",AirCrewResources.countries,0);
		countryField.setListener(countryChoiceListener);
		cityField = new CompositeObjectChoiceField("Country",cities,0);
		dateFrom = new CustomDateField("Date From", System.currentTimeMillis(), DateField.DATE);
		dateTo = new CustomDateField("Date To", System.currentTimeMillis(), DateField.DATE);
		detailsField = new CompositeButton("Details");
		
		manager = new CompositeFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		
		manager.add(countryField);
		manager.add(cityField);
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
		allProfile.setChecked(true);
		allProfile.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (allProfile.getChecked()){
					myFavorites.setChecked(false);
					checkboxPreference = 0;
				}
			}
		});
		
		allProfile.setFont(RoundedBackgroundManager.font_composite_label);
		myFavorites = new CheckboxField("My Favorites", false, Field.FIELD_HCENTER){
			protected void paint(Graphics g){
				g.setColor(Color.BLACK);
				super.paint(g);
			}
		};
		myFavorites.setChangeListener(new FieldChangeListener() {
			public void fieldChanged(Field field, int context) {
				if (myFavorites.getChecked()){
					allProfile.setChecked(false);
					checkboxPreference = 1;
				}
			}
		});
		myFavorites.setFont(RoundedBackgroundManager.font_composite_label);
		Field labelField = new LabelField("Who can view my roster ?");
		labelField.setFont(RoundedBackgroundManager.font_composite_label);
		checkboxManager.add(labelField);
		checkboxManager.add(allProfile);
		checkboxManager.add(myFavorites);
		mvrm.add(checkboxManager);
		
		saveButton = new TabbedButton("Save", 7, 470, 40);
		saveButton.setChangeListener(saveRosterListener);
		saveButton.setRVAlue(12);
		mvrm.add(saveButton);
		mvrm.add(new SpaceField(10));
		
		add(mvrm);
	}
	
	public void updateCityChoiceField(){
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
				manager.delete(cityField);
				cityField = new CompositeObjectChoiceField("City", cities, 0);
				manager.insert(cityField, 1);
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
	
	private FieldChangeListener saveRosterListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			StringBuffer postString = new StringBuffer();
			postString.append("u_id=");
			postString.append(AirCrewApp.app.getUserController().getUser().getUserId());
			postString.append("&details=");
			postString.append("");
			postString.append("&country=");
			postString.append(((Country)(countryField.getSelectedObject())).getCountry_code());
			postString.append("&city=");
			postString.append(((City)(cityField.getSelectedObject())).getCity_id());
			postString.append("&from_date=");
			postString.append("&todate=");
			postString.append("&view_all=");
			postString.append(String.valueOf(checkboxPreference));

			UpdateRosterRequest updateProfile = new UpdateRosterRequest();
			updateProfile.updateRoster(postString.toString());
		}
	};
	
	public boolean isDirty() {
	    return false;
	}
}
