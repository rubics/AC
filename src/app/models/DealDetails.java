package app.models;

import java.util.Vector;

import net.rim.device.api.util.StringUtilities;

public class DealDetails {

	private String name;
	private String description;
	private String category;
	private String logo;
	private String website;
	private String address;
	private String video;
	private String deal;
	private Vector images;
	
	
	public DealDetails(String _name,
			String _description,
			String _category,
			String _logo,
			String _website,
			String  _address,
			String _video,
			String _deal){
		name = _name;
		description = _description;
		category = _category;
		logo = _logo;
		website = _website;
		address = _address;
		video = _video;
		deal = _deal;
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

	public String addImages(String _image_id, String _image_name, String _icon_name){
		Photos photo = new Photos(_image_id, _image_name, _icon_name);
		images.addElement(photo);
		return (photo.toString());
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

class Photos{
	private String image_id;
	private String image_name;
	private String icon_name;
	
	public Photos(String _image_id, String _image_name, String _icon_name){
		image_id = _image_id;
		image_name = _image_name;
		icon_name = _icon_name;
	}
	
	public String toString(){
		return ("---------  Photo  -------------\n" + image_id + "\n" + image_name + "\n" + icon_name);
	}
}
