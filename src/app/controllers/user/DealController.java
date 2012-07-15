package app.controllers.user;

import app.models.Deal;
import app.models.DealRequest;
import app.views.managers.deals.DealScreenManager;
import app.views.screens.deals.SearchResultScreen;

public class DealController {
	
	private DashboardController dashboardController;
	private DealScreenManager dealScreenManger;
	private Deal[] deals;
	
	public DealController(DashboardController _dashboardController){
		dashboardController = _dashboardController;
		// populate deals , defer UI fieldbuild to httpsuccess of dealrequest
//		dealScreenManger = new DealScreenManager(this);
		
		DealRequest dealRequest = new DealRequest(this);
		dealRequest.getAllDeals();
	}
	
	public void pushScreen(){
		dealScreenManger = new DealScreenManager(this);
		dealScreenManger.pushScreen();
	}
	
	public void setDeals(Deal[] _deals){
		deals = _deals;
		pushScreen();
//		updateDealScreens();
	}
	public Deal[] getDeals(){
		return deals;
	}
	
	public void updateDealScreens(){
//		((SearchResultScreen)dealScreenManger.getTabbedScreens()[0]).updateScreen();
	}
}
