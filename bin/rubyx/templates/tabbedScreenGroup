package app.views.managers.deals;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import rubyx.tabbedUI.TabbedPaneButton;
import rubyx.tabbedUI.TabbedPaneManager;
import rubyx.tabbedUI.TabbedScreenManager;
import app.models.Deal;
import app.models.DealDetails;
import app.views.screens.deals.DealBookmarkScreen;
import app.views.screens.deals.EmailScreen;
import app.views.screens.deals.LocationScreen;
import app.views.screens.deals.VideoScreen;

public class DealDetailsScreenManager {
	
//	public DealDetails dealDetails;
	public Deal deal;
	private TabbedScreenManager tabbedScreenManager;
	private MainScreen[] tabbedScreens = new MainScreen[4];
	private Manager tabbedPaneManager = new TabbedPaneManager(Manager.USE_ALL_WIDTH);
	private Field emailButton = new TabbedPaneButton("Email");
	private Field videoButton =	new TabbedPaneButton("Video");
	private Field locationButton = new TabbedPaneButton("Location");
	private Field bookmarkButton = new TabbedPaneButton("Bookmark");
	
	public DealDetailsScreenManager(Deal deal) {
//		dealDetails = _dealDetails;
		this.deal = deal;
		tabbedScreens[0] = new EmailScreen(this);
		tabbedScreens[1] = new VideoScreen(this);
		tabbedScreens[2] = new LocationScreen(this);
		tabbedScreens[3] = new DealBookmarkScreen(this);
		
		tabbedPaneManager.add(emailButton);
		tabbedPaneManager.add(videoButton);
		tabbedPaneManager.add(locationButton);
		tabbedPaneManager.add(bookmarkButton);
				
		tabbedScreenManager = new TabbedScreenManager(tabbedScreens, tabbedPaneManager);
	}
	
	public void pushScreen(){
		if(tabbedScreenManager != null)
			tabbedScreenManager.pushScreen();
	}
}
