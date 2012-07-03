package app.models;

import net.rim.device.api.system.Bitmap;

public class Images {
	public static final Bitmap arrowhead = Bitmap.getBitmapResource("images/listing/arrowhead.png");
	public static final Bitmap soffline = Bitmap.getBitmapResource("images/listing/soffline.png");
	public static final Bitmap saway = Bitmap.getBitmapResource("images/listing/saway.png");
	
	public static final Bitmap[] toolbox = {Bitmap.getBitmapResource("images/listing/iconCurrency.png"),
		Bitmap.getBitmapResource("images/listing/iconWeather.png"),
		Bitmap.getBitmapResource("images/listing/iconSatelite.png"),
		Bitmap.getBitmapResource("images/listing/iconTaf.png"),
		Bitmap.getBitmapResource("images/listing/iconCrew.png"),
		Bitmap.getBitmapResource("images/listing/iconRecommendation.png")};
	public static final String[] toolbox_names ={"Currency Converter", "Weather Forecast", "Satellite Map", "METAR & TAF", "Crew Discussion", "Recommend 4Aircrew"};
	
	public static final Bitmap nextProfile = Bitmap.getBitmapResource("images/favorites/nextProfile.png");
	public static final Bitmap prevProfile = Bitmap.getBitmapResource("images/favorites/prevProfile.png");
	public static final Bitmap avatar = Bitmap.getBitmapResource("images/favorites/avatar.png");
	public static final Bitmap[] avatar_a = {Bitmap.getBitmapResource("images/profile/gallery_1.png"),
		Bitmap.getBitmapResource("images/profile/gallery_2.png"),
		Bitmap.getBitmapResource("images/profile/gallery_3.png"),
		Bitmap.getBitmapResource("images/profile/gallery_4.png"),
		Bitmap.getBitmapResource("images/profile/gallery_5.png")};
	
	public static final Bitmap[] chatScreenIcons = {Bitmap.getBitmapResource("images/chat/filter.png"),
		Bitmap.getBitmapResource("images/chat/favorites.png"),
		Bitmap.getBitmapResource("images/chat/nearme.png"),
		Bitmap.getBitmapResource("images/chat/history.png")};
	
	public static final Bitmap screen_background = Bitmap.getBitmapResource("images/screen_background.png");
	
	public static final Bitmap[] deals_tabbed_button = {Bitmap.getBitmapResource("images/deals/nearme.png"),
		Bitmap.getBitmapResource("images/deals/filter.png")};
	
	public static final Bitmap[] profile_pics = {Bitmap.getBitmapResource("images/profile_1.png"),
		Bitmap.getBitmapResource("images/profile_2.png"),
		Bitmap.getBitmapResource("images/profile_3.png"),
		Bitmap.getBitmapResource("images/profile_4.png")
	};
	
	public static Bitmap resize(Bitmap _image, int available_width, int available_height){
		
		float aspect_ratio = (float)(_image.getWidth())/(float)(_image.getHeight());
		
		float resizedWidth = 0;
		float resizedHeight = 0;
		
		if(aspect_ratio > 1){
			
			resizedWidth = available_width;
			resizedHeight = resizedWidth/aspect_ratio;
			
			if(resizedHeight > available_height){
				resizedHeight = available_height;
				resizedWidth = aspect_ratio * resizedHeight;
			}			
		} else{
			
			resizedHeight = available_height;
			resizedWidth = available_height * aspect_ratio;
			
			if(resizedWidth > available_width){
				resizedWidth = available_width;
				resizedHeight = resizedWidth /  aspect_ratio;
			}
		}
		
		Bitmap image = new Bitmap((int)resizedWidth, (int)resizedHeight);
		
		_image.scaleInto(image, Bitmap.FILTER_BILINEAR);
		return image;
	}
}
