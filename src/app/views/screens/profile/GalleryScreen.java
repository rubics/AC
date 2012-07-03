package app.views.screens.profile;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.layout_managers.TableLayoutManager;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.Images;
import app.views.fields.profile.GridImageField;
import app.views.fields.profile.GridViewController;
import app.views.managers.profile.ProfileInfoScreenManager;

public class GalleryScreen extends MainScreen{
	
	private ProfileInfoScreenManager profileInfo;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;
	private GridViewController gridImageController;
	private Field addButton;
	
	private FieldChangeListener gridFieldListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			int index = field.getIndex();
			UiApplication.getUiApplication().pushScreen(new PreviewPopup(images[index]));
		}
	};
	
	public static Bitmap[] images = {Bitmap.getBitmapResource("images/profile/gallery_1.png"),
			Bitmap.getBitmapResource("images/profile/gallery_2.png"),
			Bitmap.getBitmapResource("images/profile/gallery_3.png"),
			Bitmap.getBitmapResource("images/profile/gallery_4.png"),
			Bitmap.getBitmapResource("images/profile/gallery_2.png"),
			Bitmap.getBitmapResource("images/profile/gallery_4.png"),
			Bitmap.getBitmapResource("images/profile/gallery_1.png"),
			Bitmap.getBitmapResource("images/profile/gallery_5.png")}; 
	public GalleryScreen(ProfileInfoScreenManager _profileInfo){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		profileInfo = _profileInfo;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("Gallery", 40, backButton, homeButton));
	
		showGallery();
//		showImage();
	}
	
	private void showGallery(){
		vrManager = null;
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		int noOfColumn = Display.getWidth()/GridImageField.t_width;
		
		
		int[] columnStyles = new int[noOfColumn];
		int[] columnWidths = new int[noOfColumn];
		
		int temp = Display.getWidth()/noOfColumn;
		
		for(int i = 0; i < noOfColumn; i++){
			columnStyles[i] = TableLayoutManager.FIXED_WIDTH;
			columnWidths[i] = temp;
		}
		
		gridImageController = new GridViewController(columnStyles,columnWidths,0, Manager.FIELD_HCENTER|Manager.USE_ALL_WIDTH);
		
		for (int i = 0;i < images.length; i++){
			Field field = new GridImageField(images[i]);
			field.setChangeListener(gridFieldListener);
			gridImageController.add(field);
		}
		
		vrManager.add(gridImageController);
		vrManager.add(new SpaceField(10));
		addButton = new TabbedButton("Add", 8, 400, 40);
		vrManager.add(addButton);
		add(vrManager);
	}
	
//	private void showImage(){
//		vrManager = null;
//		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
//		vrManager.add(new GalleryImageField(0, images[0]));
//		
//		Field a = new TabbedButton("Set as Default", 6);
//		Field b = new TabbedButton("Delete", 6);
//		Manager m = new TabbedButtonManager(Display.getWidth(), 40);
//		m.add(a);
//		m.add(b);
//		vrManager.add(m);
//		add(vrManager);
//	}
	public boolean isDirty() {
	    return false;
	}
}
