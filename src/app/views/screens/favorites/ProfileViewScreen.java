package app.views.screens.favorites;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.NullField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrew;
import app.models.Images;
import app.views.fields.favorites.ProfileDetails;
import app.views.fields.favorites.ProfileView;

public class ProfileViewScreen extends MainScreen{
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrm;
	
	private ProfileView profileView;
	
	Bitmap p_image;
	
	public ProfileViewScreen(Bitmap img){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		p_image = img;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrew.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		vrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		
		setTitle(new ScreenBannar("Kristine", 40, backButton, homeButton));
		
		profileView = new ProfileView(0,p_image);
		vrm.add(profileView);
		vrm.add(new ProfileDetails());
//		String str = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,";
//		TextField field = new TextField(0){
//			protected void paint(Graphics g){
//				g.setColor(Color.WHITE);
//				super.paint(g);
//			}
//		};
//		field.setText(str);
//		vrm.add(field);
		vrm.add(new NullField());
		
		
		add(vrm);
	}
	
	public boolean isDirty() {
	    return false;
	}
}