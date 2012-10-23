package rubyx.custom_fields;

import app.models.AirCrew;
import app.models.ImageRequest;
import net.rim.device.api.system.Bitmap;
import rubyx.custom_fields.updatable_imagefield.UpdatableImageModel;

public class UrlImage extends UpdatableImageModel{
	
	private Bitmap image = default_image;
	
	public UrlImage(String url, int width, int height){
		super();
		image = new Bitmap(width, height);
		default_image.scaleInto(image, Bitmap.FILTER_BILINEAR);
		ImageRequest image_request = new ImageRequest() {
			public void onSuccess(final Bitmap _bitmap) {
				_bitmap.scaleInto(image, Bitmap.FILTER_BILINEAR);
//				invokeUpdate(image);
			}
		};
		image_request.getImage(url);
	}
	
	public Bitmap getBitmap(){
		return image;
	}
}
