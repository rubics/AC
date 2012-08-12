package app.models;

import rubyx.custom_fields.WebImageField;

public class GalleryImage {
	
	private String id;
	private String image_name;
	private String def;
	private String order;
	private String user_id;
	private WebImageField image;
	
	public GalleryImage(String id, String imageName, String defaultFlag,
			String order, String userId) {
		super();
		this.id = id;
		image_name = imageName;
		def = defaultFlag;
		this.order = order;
		user_id = userId;
		image = new WebImageField(AirCrew.gallery + image_name, 95, 95);
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
}