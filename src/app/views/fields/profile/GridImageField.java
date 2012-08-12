package app.views.fields.profile;

import rubyx.custom_fields.updatable_imagefield.UpdatableImageField;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;

public class GridImageField extends Field implements UpdatableImageField{
	
	private static final int width = 95;
	private static final int height = 95;
	
	public static final int offset = 5;
	
	public static final int t_width = width + 2*offset;
	public static final int t_height = height + 2*offset;
	
	private Bitmap image; 
	
	public GridImageField(Bitmap _image){	
		super();		
		image = new Bitmap(width, height);		
		_image.scaleInto(0, 0, _image.getWidth(),_image.getHeight(), image, 0, 0, width, height, Bitmap.FILTER_BILINEAR);		
	}
	
	public void updateImage(Bitmap bitmap) {
		image = new Bitmap(width, height);		
		bitmap.scaleInto(image, Bitmap.FILTER_BILINEAR);
		invalidate();
	}
	
	protected void layout(int _width,int _height){
		setExtent(t_width, t_height);
	}
	
	protected void paint(Graphics g){
		
		if(isFocus()){
			g.setColor(0x186DEF);
			g.fillRect(0, 0,t_width,t_height);
		}
		g.drawBitmap(offset, offset, width, height, image, 0, 0);
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
