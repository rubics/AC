package app.views.managers.visitors;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import rubyx.custom_fields.CustomButton;
import rubyx.tabbedUI.TabbedPaneButton;
import rubyx.tabbedUI.TabbedPaneManager;
import rubyx.tabbedUI.TabbedScreenManager;
import app.models.Deal;
import app.models.Images;
import app.models.Visitor;
import app.views.screens.chat.AvailableChatsScreen;
import app.views.screens.chat.FilterScreen;
import app.views.screens.chat.HistoryScreen;
import app.views.screens.chat.NearMeScreen;

public class VisitorScreenGroup {

	public Visitor visitor;
	private Bitmap[] images = Images.chatScreenIcons;
	private TabbedScreenManager tabbedScreenManager;	
	private MainScreen[] tabbedScreens = new MainScreen[3];
	private Field[] tabbedButtons = new Field[3];
	private Manager tabbedButtonManager;
	
	public VisitorScreenGroup(){
		
		tabbedScreens[0]= new AvailableChatsScreen();
		tabbedScreens[1] = new FilterScreen(null);
		tabbedScreens[2] = new NearMeScreen();
		
		tabbedButtonManager = new HorizontalFieldManager();
		
		for(int i=0; i<images.length; i++){
			tabbedButtons[i] = new CustomButton(images[i], 120, 50);
			tabbedButtonManager.add(tabbedButtons[i]);
		}
		
		tabbedScreenManager = new TabbedScreenManager(tabbedScreens, tabbedButtonManager);
	}
	public void pushScreen(){
		if(tabbedScreenManager != null)
			tabbedScreenManager.pushScreen();
	}
}
