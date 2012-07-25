package app.views.fields.listings;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Ui;
import rubyx.custom_fields.WebImageField;
import app.models.Images;

public class ListingField extends Field{
	
	private WebImageField bitmap;
	private String title;
	private String description;
	private int online;
	
	public static final int STATUS_ONLINE = 2;
	public static final int STATUS_OFFLINE = 5;
	public static final int STATUS_NONE = 8;
	
	private static final int width  = Display.getWidth();
	private static final int height= Display.getHeight()/5;
	
	private static final int horizontal_offset = 30;
	private static final int vertical_offset = 10;
	private static final int spacing = 8;
	
	private static final Font title_font = Font.getDefault().derive(Font.PLAIN, 7, Ui.UNITS_pt);
	private static final Font description_font = Font.getDefault().derive(Font.PLAIN, 6, Ui.UNITS_pt);
	
	int xa,ya,yb;
		
	public ListingField(Object _bitmap, String _title, String _description, int _online){
		super(0);
				
		bitmap = new WebImageField(_bitmap, height - 2*vertical_offset, height - 2*vertical_offset);
			
		title = _title;
		description = _description;
		online = _online;
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
		
		graphics.drawBitmap(horizontal_offset, vertical_offset, bitmap.get_width(), bitmap.get_height(), bitmap.returnBitmap(), 0, 0);
		graphics.drawBitmap(xa, ya, Images.arrowhead.getWidth(), Images.arrowhead.getHeight(), Images.arrowhead, 0, 0);
		if(online == STATUS_ONLINE)
			graphics.drawBitmap(xa-Images.saway.getWidth(), yb, Images.saway.getWidth(), Images.saway.getHeight(), Images.saway, 0, 0);
		else if (online == STATUS_OFFLINE)
			graphics.drawBitmap(xa-Images.soffline.getWidth(), yb, Images.soffline.getWidth(), Images.soffline.getHeight(), Images.soffline, 0, 0);
		graphics.setColor(Color.WHITE);
		graphics.setFont(title_font);
		graphics.drawText(title, bitmap.get_width() + horizontal_offset + vertical_offset + spacing, vertical_offset, DrawStyle.ELLIPSIS, this.width-horizontal_offset-2*vertical_offset);		
		graphics.setFont(description_font);
		graphics.setColor(0xCCCCCC);
		graphics.drawText(description, bitmap.get_width() + + horizontal_offset + vertical_offset + spacing, this.height/2, DrawStyle.ELLIPSIS,this.width-horizontal_offset-2*vertical_offset);
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
