package app.models;

import java.util.Vector;

import net.rim.device.api.system.Display;

import rubyx.custom_fields.WebImageField;
import rubyx.custom_fields.updatable_imagefield.UpdatableImageModel;

public class DealDetails extends UpdatableImageModel{

	private String name;
	private String description;
	private String category;
	private String logo;
	private String website;
	private String address;
	private String video;
	private String deal;
	private String x_code;
	private String y_code;
	private Vector images;
	
	
	public String getX_code() {
		return x_code;
	}

	public String getY_code() {
		return y_code;
	}

	public DealDetails(String _name,
			String _description,
			String _category,
			String _logo,
			String _website,
			String  _address,
			String _video,
			String _deal,
			String _x_code,
			String _y_code){
		name = _name;
		description = _description;
		category = _category;
		logo = _logo;
		website = _website;
		address = _address;
		video = _video;
		deal = _deal;
		x_code = _x_code;
		y_code = _y_code;
		images = new Vector(3,3);
	}
	
	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public String getLogo() {
		return logo;
	}

	public void addImages(String _image_id, String _icon_name){
		DealImage deal_image = new DealImage(_image_id, _icon_name);
		images.addElement(deal_image);
	}
	
	public String getDescription() {
		return description;
	}

	public String getWebsite() {
		return website;
	}

	public String getAddress() {
		return address;
	}

	public String getVideo() {
		return video;
	}

	public String getDeal() {
		return deal;
	}

	public Vector getImages() {
		return images;
	}

	public String toString(){
		return ("--------  DealDetails  ----------\n" + name + "\n" + description +"\n" + category + "\n" + deal);
	}
}