package app.views.fields.deals;

import rubyx.custom_fields.updatable_imagefield.UpdatableImageField;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;

public class DealImageField extends Field implements UpdatableImageField{
	
	private static final int image_width = 120;
	private static final int image_height = 100;
	
	public static final int offset = 5;
	
	private int width = Display.getWidth();
	private int height;
	
	private Bitmap image; 
	
	public DealImageField(Bitmap _image, int height){
		super();
		this.height = height;
		image = new Bitmap(image_width, image_height);		
		_image.scaleInto(image, Bitmap.FILTER_BILINEAR);		
	}
	
	public void updateImage(Bitmap bitmap) {
		image = new Bitmap(image_width, image_height);		
		bitmap.scaleInto(image, Bitmap.FILTER_BILINEAR);
		invalidate();
	}
	
	protected void layout(int _width,int _height){
		setExtent(width, height);
	}
	
	protected void paint(Graphics g){
		int xa = (width - (image_width + 2*offset))/2;
		int ya = (height - (image_height + 2*offset))/2;
		if(isFocus()){
			g.setColor(0x186DEF);
			g.fillRect( xa, ya,image_width + 2*offset,image_height + 2*offset);
		}
		g.drawBitmap(xa+offset, ya+offset, image_width, image_height, image, 0, 0);
	}
	
	public void drawFocus(Graphics graphics, boolean mode){
		
	}
	
	public boolean isFocusable(){
		return true;
	}
	
	protected boolean navigationClick(int status, int time){
		fieldChangeNotify(0);
		return true;
	}
	
	protected boolean keyChar(char character, int status, int time){
		if(character == Keypad.KEY_ENTER){
			fieldChangeNotify(0);
			return true;
		}
		return super.keyChar(character, status, time);
	}
	
	protected boolean trackwheelClick(int arg0, int arg1) {
		return super.trackwheelClick(arg0, arg1);		
	}
	
	protected void fieldChangeNotify(int context) {
		super.fieldChangeNotify(context);
	}
}
