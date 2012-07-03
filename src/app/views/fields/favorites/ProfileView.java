package app.views.fields.favorites;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import app.models.Images;
import app.models.Profile;

public class ProfileView extends Manager{
	
	protected static final int avatar_maxwidth = 180;
	protected static final int avatar_maxheight = 180;
	
	protected float resizedWidth;
	protected float resizedHeight;
	
	protected HorizontalFieldManager avatarArea;
	Bitmap resizedImage;
	
	public ProfileView(long style, Bitmap src_image){
		super(style);

		dimensions(Images.avatar);
//		resizedImage = new Bitmap((int)resizedWidth, (int)resizedHeight);
		resizedImage = new Bitmap(avatar_maxwidth, avatar_maxheight);
		src_image.scaleInto(resizedImage, Bitmap.FILTER_BILINEAR);
		
		add(new BitmapField(Images.prevProfile));
		add(new BitmapField(resizedImage));
		add(new BitmapField(Images.nextProfile));
	};
	
	protected void sublayout(int _width, int _height) {
		setPositionChild(getField(0), 0, 0);
		setPositionChild(getField(1),(Display.getWidth()- resizedImage.getWidth())/2, 0);
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

