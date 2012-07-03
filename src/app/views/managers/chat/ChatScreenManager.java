package app.views.managers.chat;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import rubyx.custom_fields.CustomButton;
import rubyx.tabbedUI.TabbedScreenManager;
import app.models.Images;
import app.views.screens.chat.ChatRoomScreen;
import app.views.screens.chat.FilterScreen;
import app.views.screens.chat.HistoryScreen;
import app.views.screens.chat.NearMeScreen;

public class ChatScreenManager {
	private Bitmap[] images = Images.chatScreenIcons;
	
	private TabbedScreenManager tabbedScreenManager;	
	private MainScreen[] tabbedScreens = new MainScreen[4];
	private Field[] tabbedButtons = new Field[4];
	private Manager tabbedButtonManager;
	
	public ChatScreenManager(){
		
		tabbedScreens[0]= new ChatRoomScreen();
		tabbedScreens[1] = new FilterScreen();
		tabbedScreens[2] = new NearMeScreen();
		tabbedScreens[3] = new HistoryScreen();
		
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