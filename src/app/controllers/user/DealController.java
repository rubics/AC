package app.controllers.user;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import app.models.AirCrewResources;
import app.models.CategoriesRequest;
import app.models.Category;
import app.models.City;
import app.models.CountriesRequest;
import app.models.Country;
import app.models.Deal;
import app.models.DealDetailsRequest;
import app.models.DealsRequest;
import app.views.managers.deals.DealDetailsScreenManager;
import app.views.managers.deals.DealScreenManager;
import app.views.screens.deals.DealFilterScreen;
import app.views.screens.deals.SearchResultScreen;

public class DealController {
	
	private DashboardController dashboardController;
	private DealController dealController;
	private DealScreenManager dealScreenManger;
	private Category[] categories = null;
	private City[] cities = {new City("Select a City", "Select a City")};
	private Deal[] deals;
	
	public DealController(DashboardController _dashboardController){
		dashboardController = _dashboardController;
		dealController = this;
		dealScreenManger = new DealScreenManager(this);
		
//		DealsRequest dealRequest = new DealsRequest(this);
//		dealRequest.getAllDeals();
		
		// populate deal categories
		CategoriesRequest categoriesRequest = new CategoriesRequest(this);
		categoriesRequest.getCategories();
	}
	
	public void filterDeals(int _category_index, int _country_index, int _city_index){
		DealsRequest dealRequest = new DealsRequest(this);
		dealRequest.getFilteredDeals(categories[_category_index].getCategory_id(),
				AirCrewResources.countries[_country_index].getCountry_code(),
				cities[_city_index].getCity_id());
	}
	
	public void pushScreen(){
		dealScreenManger.pushScreen();
	}
	
	public void setDeals(Deal[] _deals){
		deals = _deals;
		updateDealScreens();
	}
	
	public Deal[] getDeals(){
		return deals;
	}
	
	public Category[] getCategories() {
		return categories;
	}

	public void setCategories(Category[] categories) {
		this.categories = categories;
		((SearchResultScreen)dealScreenManger.getTabbedScreens()[0]).drawCategories();
		dealScreenManger.getTabbedScreenManager().switchScreen(0);
		updateDealFilterScreen(0,0);
	}
	
	public City[] getCity() {
		return cities;
	}

	public void setCity(City[] cities) {
		this.cities = cities;
		DealFilterScreen _dealFilterScreen = (DealFilterScreen)dealScreenManger.getTabbedScreens()[1];
		updateDealFilterScreen(_dealFilterScreen.categoryChoiceField.getSelectedIndex(), _dealFilterScreen.countryChoiceField.getSelectedIndex());
	}

	public void updateDealFilterScreen(int _category_index, int _country_index){		// draws UI for DealFilterScreen on fetcing categories, countries
		if(categories != null && AirCrewResources.countries != null){
			DealFilterScreen _dealFilterScreen = (DealFilterScreen)dealScreenManger.getTabbedScreens()[1];
			_dealFilterScreen.drawScreen(_category_index, _country_index);
		}
	}
	public void updateDealScreens(){
		((SearchResultScreen)dealScreenManger.getTabbedScreens()[0]).updateScreen();
		dealScreenManger.getTabbedScreenManager().switchScreen(0);
	}
	
	public FieldChangeListener dealDetailsListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			final int index = field.getIndex();
			if(deals[index].getDealDetails() == null){
				DealDetailsRequest dealDetailsRequest = new DealDetailsRequest(){
					public void afterSuccess(){
						deals[index].setDealDetails(dealDetails);
						UiApplication.getUiApplication().invokeAndWait(new Runnable() {
							public void run() {
								DealDetailsScreenManager aboutDeals = new DealDetailsScreenManager(deals[index].getDealDetails());
								aboutDeals.pushScreen();
							}
						});
					}
				};
				dealDetailsRequest.getDetials(deals[index].getId());
			} else {
				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					public void run() {
						DealDetailsScreenManager aboutDeals = new DealDetailsScreenManager(deals[index].getDealDetails());
						aboutDeals.pushScreen();
					}
				});
			}
		}
	};
	
	public FieldChangeListener categorizedDeals = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			final int index = field.getIndex();
			DealsRequest dealRequest = new DealsRequest(dealController);
			dealRequest.getDealbyCategory(categories[index]);
		}
	};
}
