package app.views.fields.favorites;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import rubyx.custom_fields.BitmapButton;
import rubyx.custom_fields.WebImageField;
import app.models.Images;
import app.views.screens.favorites.ProfileViewScreen;

public class ProfileView extends Manager{
	
	protected static final int avatar_maxwidth = 180;
	protected static final int avatar_maxheight = 180;
	
	protected float resizedWidth;
	protected float resizedHeight;
	
	protected HorizontalFieldManager avatarArea;
	WebImageField webImage;
	
	public ProfileView(long style, ProfileViewScreen profileScreen){
		super(style);

		dimensions(Images.avatar);
		
		this.webImage = profileScreen.profileImage;
		
		Field prevProfile = new BitmapButton(Images.prevProfile);
		Field nextProfile = new BitmapButton(Images.nextProfile);
		prevProfile.setChangeListener(profileScreen.previousProfileListener);
		nextProfile.setChangeListener(profileScreen.nextProfileListener);
		
		add(prevProfile);
		add(webImage);
		add(nextProfile);
	};
	
	protected void sublayout(int _width, int _height) {
		setPositionChild(getField(0), 0, 0);
		setPositionChild(getField(1),(Display.getWidth()- avatar_maxwidth)/2, 0);
		setPositionChild(getField(2), Display.getWidth()-54, 0);
		
		layoutChild(getField(0), 54, 78);
		layoutChild(getField(1), (int)resizedWidth, (int)resizedHeight);
		layoutChild(getField(2), 54, 78);
		
		setExtent(Display.getWidth(), avatar_maxheight);
	}

	public void dimensions(Bitmap _image){
		float aspect_ratio = (float)(_image.getWidth())/(float)(_image.getHeight());
		
		if(aspect_ratio > 1){
			
			resizedWidth = avatar_maxwidth;
			resizedHeight = avatar_maxwidth/aspect_ratio;
			
			if(resizedHeight > avatar_maxheight){
				resizedHeight = avatar_maxheight;
				resizedWidth = aspect_ratio * resizedHeight;
			}			
		} else{
			
			resizedHeight = avatar_maxheight;
			resizedWidth = avatar_maxheight * aspect_ratio;
			
			if(resizedWidth > avatar_maxwidth){
				resizedWidth = avatar_maxwidth;
				resizedHeight = resizedWidth / aspect_ratio;
			}
		}
	}
}

