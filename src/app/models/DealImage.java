package app.models;

import net.rim.device.api.system.Bitmap;
import rubyx.custom_fields.WebImageField;
import rubyx.custom_fields.updatable_imagefield.UpdatableImageModel;

public class DealImage extends UpdatableImageModel{
	
	private String image_id;
	private String icon_name;
	private Bitmap image = default_image;
	
	public DealImage(String _image_id, String _icon_name) {
		super();
		image_id = _image_id;
		icon_name = _icon_name;
		ImageRequest image_request = new ImageRequest() {
			public void onSuccess(final Bitmap _bitmap) {
				image = _bitmap;
				invokeUpdate(image);
			}
		};
		image_request.getImage(AirCrew.image_medium + icon_name);
	}

	public Bitmap getImage(){
		return image;
	}
}