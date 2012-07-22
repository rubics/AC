package app.controllers.user;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import app.models.Business;
import app.models.Deal;
import app.models.DealDetailsRequest;
import app.models.DealsRequest;
import app.views.managers.deals.DealDetailsScreenManager;
import app.views.managers.deals.DealScreenManager;
import app.views.screens.deals.SearchResultScreen;

public class DealController {
	
	private DashboardController dashboardController;
	private DealController dealController;
	private DealScreenManager dealScreenManger;
	private Deal[] deals;
	
	public DealController(DashboardController _dashboardController){
		dashboardController = _dashboardController;
		dealController = this;
		dealScreenManger = new DealScreenManager(this);
		
		DealsRequest dealRequest = new DealsRequest(this);
		dealRequest.getAllDeals();
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
