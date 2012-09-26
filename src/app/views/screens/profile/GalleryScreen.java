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
import rubyx.image_selector.UploadImagePopup;
import rubyx.image_selector.gallery.ImagePopup;
import rubyx.layout_managers.TableLayoutManager;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.DeleteImageRequest;
import app.models.GalleryImage;
import app.models.ImageUploader;
import app.models.Images;
import app.models.SetMainImageRequest;
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
	private int image_index = 0;
	private CustomPopup customPopup;
	
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
		homeButton.setChangeListener(addImageListener);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("Gallery", 40, backButton, homeButton));
		
		vrManager = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
		add(vrManager);
	}
	
	FieldChangeListener addImageListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().pushScreen(CustomPopup.getInstance());
		}
	};
	
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
			image_index = field.getIndex();
			PreviewPopup preview_popup = new PreviewPopup(galleryImages[image_index].getImage());
			preview_popup.setListeners(deleteImageListener, setMainImageListener);
			UiApplication.getUiApplication().pushScreen(preview_popup);
		}
	};
	
	private FieldChangeListener setMainImageListener = new FieldChangeListener() {	
		public void fieldChanged(Field field, int context) {
			SetMainImageRequest setMainImage = new SetMainImageRequest();
			setMainImage.setMainImage(AirCrewApp.app.getUserController().getUser().getUserId(),
			galleryImages[image_index].getId());
			UiApplication.getUiApplication().popScreen(field.getScreen());
		}
	};
	
	private FieldChangeListener deleteImageListener = new FieldChangeListener() {	
		public void fieldChanged(Field field, int context) {
			DeleteImageRequest deleteImage = new DeleteImageRequest();
			deleteImage.deleteImage(galleryImages[image_index].getId());
			UiApplication.getUiApplication().popScreen(field.getScreen());
		}
	};

	public boolean isDirty() {
	    return false;
	}
}

class CustomPopup extends UploadImagePopup{
	
	private static CustomPopup customPopup = null;
	
	public static CustomPopup getInstance(){
		if(customPopup == null)
			customPopup = new CustomPopup();
		return customPopup;
	} 
	
	private CustomPopup(){
		super();
		VerticalFieldManager vrManager = new VerticalFieldManager(Manager.FIELD_VCENTER);
		
		takePicture = new TabbedButton("Take Picture", 6, 240, 50);
		selectImage = new TabbedButton("Photo Albums", 6, 240, 50);
		cancelButton = new TabbedButton("Cancel", 6, 240, 50);

		vrManager.add(takePicture);
		vrManager.add(new SpaceField(10));
		vrManager.add(selectImage);
		vrManager.add(new SpaceField(10));
		vrManager.add(cancelButton);
		
		takePicture.setChangeListener(invokeCamera);
		selectImage.setChangeListener(invokeGallery);
		cancelButton.setChangeListener(cancelSelection);
		
		add(vrManager);
	}

	public void onImageReturn(final String file_name, final byte[] imageBytes) {
		UiApplication.getUiApplication().invokeAndWait(new Runnable() {
			public void run(){
				Bitmap image = Bitmap.createBitmapFromBytes(imageBytes, 0, imageBytes.length, 1);
				ImagePopup imagePopup = new ImagePopup(image);
				imagePopup.uploadButton.setChangeListener(new FieldChangeListener() {
					public void fieldChanged(Field field, int context) {
						ImageUploader imageUploader = new ImageUploader();
						imageUploader.insertToGallery(file_name, imageBytes);
					}
				});
				UiApplication.getUiApplication().pushScreen(imagePopup);
			}
		});
	}
}