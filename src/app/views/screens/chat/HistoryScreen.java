package app.views.screens.chat;

import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;
import app.AirCrew;
import app.models.Images;

public class HistoryScreen extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	
	public HistoryScreen(){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrew.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("History", 40, backButton, homeButton));
	}
	
	public boolean isDirty() {
	    return false;
	}
}