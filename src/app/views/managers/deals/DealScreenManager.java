package app.views.managers.deals;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import rubyx.custom_fields.CustomButton;
import rubyx.tabbedUI.TabbedScreenManager;
import app.controllers.user.DealController;
import app.models.Images;
import app.views.screens.deals.DealFilterScreen;
import app.views.screens.deals.SearchResultScreen;

public class DealScreenManager {
	
	private TabbedScreenManager tabbedScreenManager;	
	private MainScreen[] tabbedScreens = new MainScreen[2];
	private Field[] tabbedButtons = new Field[2];
	
	private Manager tabbedButtonManager;
	private Bitmap[] images = Images.deals_tabbed_button;
	
	public DealScreenManager(DealController _dealController){
		
		tabbedScreens[0]= new SearchResultScreen(_dealController);
		tabbedScreens[1] = new DealFilterScreen(_dealController);
		
		tabbedButtonManager = new HorizontalFieldManager();
		
		for(int i=0; i<2; i++){
			tabbedButtons[i] = new CustomButton(images[i], Display.getWidth()/2, 50);
			tabbedButtonManager.add(tabbedButtons[i]);
		}
		
		tabbedScreenManager = new TabbedScreenManager(tabbedScreens, tabbedButtonManager);
	}
	public void pushScreen(){
		if(tabbedScreenManager != null)
			tabbedScreenManager.pushScreen();
	}
	
	public MainScreen[] getTabbedScreens() {
		return tabbedScreens;
	}
	
	public TabbedScreenManager getTabbedScreenManager(){
		return tabbedScreenManager;
	}
}