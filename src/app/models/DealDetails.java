package app.models;

import java.util.Vector;

import net.rim.device.api.util.StringUtilities;

public class DealDetails {

	private String description;
	private String website;
	private String address;
	private String video;
	private String deal;
	private Vector images;
	
	
	public DealDetails(String _description,
			String _website,
			String  _address,
			String _video,
			String _deal){
		description = _description;
		website = _website;
		address = _address;
		video = _video;
		deal = _deal;
		images = new Vector(3,3);
	}
	
	public void addImages(String _image_id, String _image_name, String _icon_name){
		images.addElement(new Photos(_image_id, _image_name, _icon_name));
	}
	
	public String toString(){
		return ("------------------" + description +"\n" + website + "\n" + deal);
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
}
