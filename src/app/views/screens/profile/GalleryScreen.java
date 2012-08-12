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
import rubyx.custom_fields.updatable_imagefield.UpdatableImageField;
import rubyx.layout_managers.TableLayoutManager;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.GalleryImage;
import app.models.Images;
import app.views.fields.profile.GridImageField;
import app.views.fields.profile.GridViewController;
import app.views.managers.profile.ProfileInfoScreenManager;

public class GalleryScreen extends MainScreen{
	
	private ProfileInfoScreenManager profileInfo;
	private final GalleryScreen current_screen = this;
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;
	private GridViewController gridImageController;
	private Field addButton;
	private GalleryImage[] galleryImages;
	
//	public static Bitmap[] images;
	
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
		homeButton = new TabbedButton("Add", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("Gallery", 40, backButton, homeButton));
		
		vrManager = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
		add(vrManager);
	
//		showGallery();
//		showImage();
	}
	
	public void showGallery(final GalleryImage[] galleryImages){
		this.galleryImages = galleryImages;
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			public void run() {
				current_screen.delete(vrManager);
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
				
				for (int i = 0;i < galleryImages.length; i++){
					GridImageField field = new GridImageField(galleryImages[i].getImage());
					galleryImages[i].addUpdatableFields(field);
					field.setChangeListener(gridFieldListener);
					gridImageController.add(field);
				}
				
				vrManager.add(gridImageController);
				add(vrManager);
			}
		});
	}
	
	private FieldChangeListener gridFieldListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			int index = field.getIndex();
			UiApplication.getUiApplication().pushScreen(new PreviewPopup(galleryImages[index].getImage()));
		}
	};

	public boolean isDirty() {
	    return false;
	}
}
