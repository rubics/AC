package app.views.fields;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;

public class DashboardItem extends Field{
	
	final static Bitmap grid_item_background = Bitmap.getBitmapResource("images/dashboard/grid_item_background.png");
	final static Bitmap grid_item_background_focus = Bitmap.getBitmapResource("images/dashboard/grid_item_background_focus.png");
	
	final static int width = Display.getWidth()/3;
	final static int height = (Display.getHeight()-50)/3;
	
	final static int horizontal_padding = (width - grid_item_background.getWidth())/2;
	final static int vertical_padding = (height - grid_item_background.getHeight())/2;
	
	Bitmap icon;
	Bitmap title;
	
	int icon_padding_h;
	int icon_padding_v;
	int title_padding_h;
	int title_padding_v;
	
	public int index;
	
	public DashboardItem(Bitmap _icon, Bitmap _title, int _index){
		icon = _icon;
		title = _title;
		index = _index;
		
		icon_padding_h = (width - icon.getWidth())/2;
		icon_padding_v = (height - (icon.getHeight() + title.getHeight()))/2;
		
		title_padding_h = (width - title.getWidth())/2;
		title_padding_v = icon_padding_v + icon.getHeight();
	}

	protected void layout(int _width, int _height) {
		setExtent(width, height);
	}

	protected void paint(Graphics _graphics) {
		if(isFocus())
			_graphics.drawBitmap(horizontal_padding, vertical_padding, grid_item_background_focus.getWidth(), grid_item_background_focus.getHeight(), grid_item_background_focus, 0, 0);
		else
			_graphics.drawBitmap(horizontal_padding, vertical_padding, grid_item_background.getWidth(), grid_item_background.getHeight(), grid_item_background, 0, 0);
		_graphics.drawBitmap(icon_padding_h, icon_padding_v, icon.getWidth(), icon.getHeight(), icon, 0, 0);
		_graphics.drawBitmap(title_padding_h, title_padding_v, title.getWidth(), title.getHeight(), title, 0, 0);
	}
	
	protected void drawFocus(Graphics _graphics, boolean on) {

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
