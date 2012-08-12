package app.models;

import net.rim.device.api.system.Bitmap;
import rubyx.custom_fields.WebImageField;
import rubyx.custom_fields.updatable_imagefield.UpdatableImageModel;

public class GalleryImage extends UpdatableImageModel{
	
	private String id;
	private String image_name;
	private String def;
	private String order;
	private String user_id;
	private Bitmap image = default_image;
	
	public GalleryImage(String id, String imageName, String defaultFlag,
			String order, String userId) {
		super();
		this.id = id;
		image_name = imageName;
		def = defaultFlag;
		this.order = order;
		user_id = userId;
		ImageRequest image_request = new ImageRequest() {
			public void onSuccess(final Bitmap _bitmap) {
				image = _bitmap;
				invokeUpdate(image);
			}
		};
		System.out.println(">> Gallery Images: " + AirCrew.user_images + image_name);
		image_request.getImage(AirCrew.user_images + image_name);
	}

	public String getId() {
		return id;
	}

	public String getImage_name() {
		return image_name;
	}

	public String getDefault_flag() {
		return def;
	}

	public String getOrder() {
		return order;
	}

	public String getUser_id() {
		return user_id;
	}
	
	public Bitmap getImage(){
		return image;
	}

}