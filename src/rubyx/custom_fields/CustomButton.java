package rubyx.custom_fields;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;

public class CustomButton extends Field {
	
	private Bitmap icon;
	private int width;
	private int height;
	boolean focusable=true;
	
	private int xa;
	private int ya;
	
	
	public CustomButton(Bitmap _icon, int _width, int _height){
		
		super();
		icon = _icon;
		width = _width;
		height = _height;
		
		xa = (width - icon.getWidth())/2;
		ya = (height - icon.getHeight())/2;
	}
	
	protected void layout(int _width, int _height) {
		setExtent(width,height);
	}

	protected void paint(Graphics graphics) {
		graphics.setColor(0x444444);
		
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		if(isFocus())
			graphics.setColor(0x186DEF);
		else
			graphics.setColor(Color.BLACK);
		
		
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.setGlobalAlpha(30);
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, getWidth(), getHeight()/2);
		graphics.setGlobalAlpha(255);
		graphics.drawBitmap(xa, ya, icon.getWidth(), icon.getHeight(), icon, 0, 0);
		
	}
	
	protected void paintBackground(Graphics graphics){
		graphics.setBackgroundColor(Color.BLACK);
		graphics.setGlobalAlpha(255);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, icon.getWidth(), icon.getHeight());
	}
	
	public boolean isFocusable(){
		return focusable;
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
	
	public void setEnabled(boolean flag){
		//super.setEnabled(flag);
		focusable  = flag;
	}
}
