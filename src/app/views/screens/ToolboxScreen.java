package app.views.screens;

import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import app.AirCrew;
import app.models.Images;
import app.views.fields.listings.ListField;

public class ToolboxScreen extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;

	public ToolboxScreen(){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrew.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("Toolbox", 40, backButton, homeButton));
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				
		Bitmap[] images = Images.toolbox;
		String[] names = Images.toolbox_names;
		for (int i=0; i<images.length; i++){
			vrManager.add( new ListField(images[i], names[i]));
		}
		add(vrManager);
	}
	public boolean isDirty() {
	    return false;
	}
}