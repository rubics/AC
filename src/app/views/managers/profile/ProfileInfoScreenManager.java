package app.views.managers.profile;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import rubyx.custom_fields.CustomButton;
import rubyx.tabbedUI.TabbedScreenManager;
import app.views.screens.DashboardScreen;
import app.views.screens.profile.GalleryScreen;
import app.views.screens.profile.MyRosterScreen;
import app.views.screens.profile.ProfileScreen;
import app.views.screens.profile.ProfileSettingsScreen;

public class ProfileInfoScreenManager {
	private Bitmap[] images = {DashboardScreen.icons[4],
						Bitmap.getBitmapResource("images/profile/myroster.png"),
						Bitmap.getBitmapResource("images/profile/gallery.png"),
						DashboardScreen.icons[8]};
	
	private TabbedScreenManager tabbedScreenManager;	
	private MainScreen[] tabbedScreens = new MainScreen[4];
	private Field[] tabbedButtons = new Field[4];
	private Manager tabbedButtonManager;
	
	public ProfileInfoScreenManager(){
		
		tabbedScreens[0]= new ProfileScreen(this);
		tabbedScreens[1] = new MyRosterScreen(this);
		tabbedScreens[2] = new GalleryScreen(this);
		tabbedScreens[3] = new ProfileSettingsScreen(this);
		
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
