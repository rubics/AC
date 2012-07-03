package rubyx.custom_fields;



import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;

public class BitmapButton extends Field {
	
	Bitmap icon;
	boolean focusable=true;
	
	public BitmapButton(Bitmap icon){
		
		super();
		this.icon = icon;
	}
	
	protected void layout(int width, int height) {
		// TODO Auto-generated method stub
		setExtent(icon.getWidth(),icon.getHeight());
		
	}

	protected void paint(Graphics graphics) {
		// TODO Auto-generated method stub
		
		graphics.setColor(Color.BLACK);
		
		graphics.fillRect(0, 0, icon.getWidth(), icon.getHeight());
		
		if(isFocus())
			graphics.setColor(0x186DEF);
		else
			graphics.setColor(Color.BLACK);
		
		
		graphics.fillRoundRect(0, 0, icon.getWidth(), icon.getHeight(),7,7);
//		graphics.setGlobalAlpha(70);
//		graphics.setColor(Color.WHITE);
//		graphics.fillRect(0, 0, icon.getWidth(), icon.getHeight()/2);		
//		graphics.setColor(Color.BLACK);
		graphics.setGlobalAlpha(255);
		graphics.drawBitmap(0, 0, icon.getWidth(), icon.getHeight(), icon, 0, 0);
		
	}
	
	protected void paintBackground(Graphics graphics){
		graphics.setBackgroundColor(Color.BLACK	);
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
