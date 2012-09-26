package rubyx.image_selector;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

import net.rim.blackberry.api.invoke.CameraArguments;
import net.rim.blackberry.api.invoke.Invoke;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import rubyx.image_selector.camera.CameraListener;
import rubyx.image_selector.gallery.GalleryView;

public abstract class UploadImagePopup extends PopupScreen{
	
	protected Field takePicture;
	protected Field selectImage;
	protected Field cancelButton;
	private CameraListener cameraListener;
	private UploadImagePopup this_screen;
	public String selected_image_path = "";
	
	protected UploadImagePopup(){
		super(new VerticalFieldManager());
		this_screen = this;
	}
	
	protected abstract void onImageReturn(String filename, byte[] imageBytes);
	
	protected FieldChangeListener invokeCamera = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			try{
				cameraListener = new CameraListener(this_screen){
					public void onImageReturn(String image_path){
						selected_image_path = image_path;
						System.out.println(">> " + selected_image_path);
					}
				};
				UiApplication.getUiApplication().addFileSystemJournalListener(cameraListener);
				Invoke.invokeApplication(Invoke.APP_TYPE_CAMERA, new CameraArguments());}
			catch(final Exception e){
				System.out.println("Exception @ UploadImagePopup.invokeCamera()");
				System.out.println(">> " + e.getClass().getName());
			}
		}
	};
	
	protected FieldChangeListener invokeGallery = new FieldChangeListener(){
		public void fieldChanged(Field field, int context){
			GalleryView galleryView = new GalleryView(){
				public void onImageReturn(String selected_image){
					selected_image_path = selected_image;
				}
			};
			UiApplication.getUiApplication().pushScreen(galleryView);
		}
	};
	
	protected FieldChangeListener cancelSelection = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
		}
	};
	
	private byte[] getBitmapBytes(String last_image){
		
		try{
			FileConnection file_con = (FileConnection)Connector.open(last_image);
			
			if(file_con.exists()){
				InputStream ips = ( file_con.openInputStream());
				ByteArrayOutputStream ops = new ByteArrayOutputStream();
				
				byte[] array = new byte[4000];
				
				int length = ips.read(array);
				
				while(length > 0){					
					ops.write(array);
					length = ips.read(array);					
				}
				
				byte[] imageBytes = ops.toByteArray();
				
				return imageBytes;
//				Bitmap last_image_bitmap = Bitmap.createBitmapFromBytes(previewedImageBytes, 0, previewedImageBytes.length, 1);
//				return last_image_bitmap;
			}
		} catch(Exception e){
			System.out.println("Exception @ CameraListener.getBitmap()");
			System.out.println(">> " + e.getClass().getName());
			return null;
		}
		return null;
	}
	
	public void onExposed(){
		UiApplication.getUiApplication().invokeAndWait(new Runnable() {
			public void run() {
				cameraListener = null;
				UiApplication.getUiApplication().popScreen(this_screen);
			}
		});
		if(!selected_image_path.equals("")){ // check if any file path exists
			onImageReturn(selected_image_path, getBitmapBytes(selected_image_path));
			selected_image_path = "";
		}
	}
	
	public boolean isDirty(){return false;}
}