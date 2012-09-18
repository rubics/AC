package app.views.screens;

import net.rim.blackberry.api.browser.Browser;
import net.rim.blackberry.api.browser.BrowserSession;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.AirCrew;
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
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("Toolbox", 40, backButton, homeButton));
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				
		Bitmap[] images = AirCrew.toolbox;
		String[] names = AirCrew.toolbox_names;
		for (int i=0; i<images.length; i++){
			Field field =  new ListField(images[i], names[i]);
			field.setChangeListener(toolboxListener);
			vrManager.add(field);
		}
		add(vrManager);
	}
	
	private FieldChangeListener toolboxListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			// TODO Auto-generated method stub
			try{
				BrowserSession browserSession = Browser.getDefaultSession();
				browserSession.displayPage(AirCrew.toolbox_url[field.getIndex()]);
//				AirCrewApp.app.pushScreen(new WebViewScreen(AirCrew.toolbox_url[field.getIndex()], "Toolbox", ""));
//				AirCrewApp.app.pushScreen(new WebViewScreen("http://www.google.com", "Toolbox", ""));
			} catch (Exception e){
				System.out.println(">> Exception @ " + e.getClass().getName());
				System.out.println();
			}
		}
	};
	
	public boolean isDirty() {
	    return false;
	}
	
	
}