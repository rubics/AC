package app.views.fields.listings;

import app.models.Images;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Ui;

public class ListField extends Field{
	
	private Bitmap bitmap;
	private String title;

	private static final int width  = Display.getWidth();
	private static final int height= Display.getHeight()/5;
	
	private static final int horizontal_offset = 30;
	private static final int vertical_offset = 10;
	private static final int spacing = 8;
	
	private static final Font title_font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
		
	int xa,ya,yb;
		
	public ListField(Bitmap _bitmap, String _title){
		super(0);
				
		bitmap = _bitmap;		
		title = _title;
		
		xa = width - horizontal_offset - Images.arrowhead.getWidth();
		ya = (height - Images.arrowhead.getHeight())/2;
		yb = (height - Images.saway.getHeight())/2;
	}
	
	protected void layout(int _width, int _height){
		setExtent(width, height);
	}

	protected void paint(Graphics graphics){

		graphics.setColor(0x666666);
		graphics.drawRoundRect(2, 2, width-4, height-4, 20, 20);
		
		if(isFocus()){
			graphics.setColor(0x186DEF);
			graphics.fillRoundRect(3, 3, width-6, height-6, 20, 20);
			graphics.setGlobalAlpha(20);
			graphics.setColor(Color.WHITE);
			graphics.fillRect(3, 3, width-6, height/2-3);
			graphics.setGlobalAlpha(255);
		}
		
		graphics.drawBitmap(horizontal_offset, vertical_offset, bitmap.getWidth(), bitmap.getHeight(), bitmap, 0, 0);
		graphics.drawBitmap(xa, ya, Images.arrowhead.getWidth(), Images.arrowhead.getHeight(), Images.arrowhead, 0, 0);
		graphics.setColor(Color.WHITE);
		graphics.setFont(title_font);
		graphics.drawText(title, bitmap.getWidth() + horizontal_offset + vertical_offset + spacing, (getHeight() - title_font.getHeight())/2, DrawStyle.ELLIPSIS, this.width-horizontal_offset-2*vertical_offset);		
	}
	
	protected void drawFocus(Graphics graphics, boolean mode){
		
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