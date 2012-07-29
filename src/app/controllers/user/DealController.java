package app.controllers.user;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import app.models.CategoriesRequest;
import app.models.Category;
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
	private Category[] categories;
	private Deal[] deals;
	
	public DealController(DashboardController _dashboardController){
		dashboardController = _dashboardController;
		dealController = this;
		dealScreenManger = new DealScreenManager(this);
		
		DealsRequest dealRequest = new DealsRequest(this);
		dealRequest.getAllDeals();
		
		CategoriesRequest categoriesRequest = new CategoriesRequest(this);
		categoriesRequest.getCategories();
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
		DealFilterScreen _dealFilterScreen = (DealFilterScreen)dealScreenManger.getTabbedScreens()[1];
		_dealFilterScreen.drawScreen();
	}

	public void updateDealScreens(){
		((SearchResultScreen)dealScreenManger.getTabbedScreens()[0]).updateScreen();
	}
	
	public FieldChangeListener dealDetails = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			final int index = field.getIndex();
			if(deals[index].getDealDetails() == null){
				DealDetailsRequest dealDetailsRequest = new DealDetailsRequest(dealController){
					public void afterSuccess(){
						UiApplication.getUiApplication().invokeAndWait(new Runnable() {
							public void run() {
								DealDetailsScreenManager aboutDeals = new DealDetailsScreenManager(deals[index]);
								aboutDeals.pushScreen();
							}
						});
					}
				};
				dealDetailsRequest.getDetials(deals[index]);
			} else {
				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					public void run() {
						DealDetailsScreenManager aboutDeals = new DealDetailsScreenManager(deals[index]);
						aboutDeals.pushScreen();
					}
				});
			}
		}
	};
}
