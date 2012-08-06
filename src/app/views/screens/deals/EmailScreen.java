package app.views.screens.deals;

import rubyx.custom_fields.ListItem;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.NullField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import app.models.AirCrew;
import app.models.Images;
import app.views.fields.ScreenTitle;
import app.views.fields.deals.CompositeDealLabel;
import app.views.fields.listings.ListingField;
import app.views.managers.deals.DealDetailsScreenManager;

public class EmailScreen extends MainScreen{
	private DealDetailsScreenManager dealsInfo;
	
	private Manager manager;
	
	public EmailScreen(DealDetailsScreenManager _dealsInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL);
		dealsInfo = _dealsInfo;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		setTitle(new ScreenTitle("Email"));
		manager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		add(manager);
		manager.add(new NullField());
		System.out.println(dealsInfo.dealDetails.getName());
		System.out.println(dealsInfo.dealDetails.getDescription());
		manager.add(new ListItem(dealsInfo.dealDetails.getName(), dealsInfo.dealDetails.getDescription(), dealsInfo.dealDetails.getLogo(), false));
//		ListingField(AirCrew.image_medium + deal.getLogo(), deal.getName(), deal.getCategory_name(),8);
		manager.add(new CompositeDealLabel(dealsInfo.dealDetails));
	}
	
	public boolean isDirty() {
	    return false;
	}
}
