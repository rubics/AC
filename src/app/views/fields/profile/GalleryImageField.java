package app.views.fields.profile;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class GalleryImageField extends Field{
	
	private Bitmap image;
	private static final int availableWidth = 440;
	private static final int availableHeight = 240;
	private static final int width = Display.getWidth();
	private static final int height = availableHeight + 2*10;
	
	private float resizedWidth;
	private float resizedHeight;
	
	public GalleryImageField(long _style, Bitmap _image){
		super(_style);

		float aspect_ratio = (float)(_image.getWidth())/(float)(_image.getHeight());
		
		if(aspect_ratio > 1){
			
			resizedWidth = availableWidth;
			resizedHeight = availableWidth/aspect_ratio;
			
			if(resizedHeight > availableHeight){
				resizedHeight = availableHeight;
				resizedWidth = aspect_ratio * resizedHeight;
			}			
		} else{
			
			resizedHeight = availableHeight;
			resizedWidth = availableHeight * aspect_ratio;
			
			if(resizedWidth > availableWidth){
				resizedWidth = availableWidth;
				resizedHeight = resizedWidth / aspect_ratio;
			}
		}
		
		image = new Bitmap((int)resizedWidth, (int)resizedHeight);
		
		_image.scaleInto(image, Bitmap.FILTER_BILINEAR);
	}
	
	public void layout(int _width, int _height){
		setExtent(width, height);
	}

	protected void paint(Graphics g) {
		g.drawBitmap(20, 10, (int)resizedWidth, (int)resizedHeight, image, 0, 0);
	}
	
	public void drawFocus(Graphics graphics, boolean mode){
		
	}
}
