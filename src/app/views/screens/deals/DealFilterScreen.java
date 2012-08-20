package app.views.screens.deals;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CompositeField;
import rubyx.custom_fields.CompositeObjectChoiceField;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.controllers.user.DealController;
import app.models.AirCrewResources;
import app.models.City;
import app.models.CityRequest;
import app.models.Country;
import app.models.Images;

public class DealFilterScreen extends MainScreen{
	
	private DealController dealController;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	public CompositeObjectChoiceField categoryChoiceField;
	public CompositeObjectChoiceField countryChoiceField;
	public CompositeObjectChoiceField cityChoiceField;
	private TabbedButton filterButton;
	private DealFilterScreen this_screen;
	
	private VerticalFieldManager vrManager;
	
	public DealFilterScreen(DealController _dealController){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		this_screen = this;
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
		add(vrManager);
	}
	
	public void drawScreen(final int _category_index, final int _country_index){
		UiApplication.getUiApplication().invokeAndWait(new Runnable() {
			
			public void run() {
				this_screen.delete(vrManager);
				vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);

				vrManager.add(new SpaceField(8));
				categoryChoiceField = new CompositeObjectChoiceField("Category", dealController.getCategories(), _category_index);
				categoryChoiceField.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
				vrManager.add(categoryChoiceField);
				vrManager.add(new SpaceField(5));
				countryChoiceField = new CompositeObjectChoiceField("Country", AirCrewResources.countries, _country_index);
				countryChoiceField.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
				countryChoiceField.setListener(countryChoiceListener);
				vrManager.add(countryChoiceField);
				vrManager.add(new SpaceField(5));
				cityChoiceField = new CompositeObjectChoiceField("City", dealController.getCity(), 0);
				cityChoiceField.setDrawStyle(CompositeField.DRAWSTYLE_SINGLE);
				vrManager.add(cityChoiceField);
				vrManager.add(new SpaceField(45));
				filterButton = new TabbedButton("Filter", 7, 480, 42);
				filterButton.setRVAlue(15);
				filterButton.setChangeListener(filterButtonListener);
				vrManager.add(filterButton);
				add(vrManager);
				vrManager.invalidate();
//				UiApplication.getUiApplication().getActiveScreen().invalidate();
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
						public void afterSuccess(City[] cities){
							dealController.setCity(cities);
						}
					};
					cityRequest.getCity(country.getCountry_code());
				}
			} catch(Exception e){
				System.out.println();
			}
		}
	};
	
	private FieldChangeListener filterButtonListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			dealController.filterDeals(categoryChoiceField.getSelectedIndex(),
					countryChoiceField.getSelectedIndex(),
					cityChoiceField.getSelectedIndex());
		}
	};
	public boolean isDirty() {
	    return false;
	}
}