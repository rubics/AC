package rubyx.tabbedUI;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Ui;

public class TabbedPaneButton extends Field{
	
	private Bitmap focus_background;
	private Bitmap foreground;
	private final int width = Display.getWidth()/4;
	private final int height = 50;
	
	private static final Font font = Font.getDefault().derive(Font.PLAIN, 6, Ui.UNITS_pt);
	
	private String label;
	
	public TabbedPaneButton(String _label){
		label = _label;		
	}
	
	public void setFocusBackground(Bitmap _focus_background){
		focus_background = _focus_background;
	}
	
	public void setForeground(Bitmap _foreground){
		foreground = _foreground;
	}
	
	protected void layout(int _width, int _height) {
		setExtent(width,height);		
	}

	protected void paint(Graphics graphics) {
		if(isFocus()){
			graphics.drawBitmap(0, 0, focus_background.getWidth(), focus_background.getHeight(), focus_background, 0, 0);
		}
		graphics.drawBitmap(0, 0, foreground.getWidth(), foreground.getHeight(), foreground, 0, 0);
//		graphics.setColor(0xE5E5E5);		
//		graphics.setFont(font);
//		graphics.drawText(label, (getWidth()-getFont().getAdvance(label))/2, (getHeight()-getFont().getHeight())/2);
		
	}
	
	protected void drawFocus(Graphics graphics, boolean on){
		
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
