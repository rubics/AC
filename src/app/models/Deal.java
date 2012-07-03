package app.models;

import net.rim.device.api.system.Bitmap;

public class Deal {
	public String name;
	public String category;
	public String description;
	public Bitmap image;
	
	public Deal(String _name, String _category, String _description, Bitmap _image){
		name = _name;
		category = _category;
		description = _description;
		image = _image;
	}
	
	public String toString(){
		return "Deal Object: " + name + "\t" + description + "\t" + image;
	}
}
