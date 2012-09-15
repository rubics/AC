package rubyx.custom_fields;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Ui;
import app.models.AirCrew;
import app.models.Deal;
import app.models.Images;
import app.views.fields.listings.ListingField;

public class ListItem extends Field{
	static int width = Display.getWidth();
	static int height = 72;
	static int bitmap_dimension = 60;
	
	static int bitmap_offset_h = 20;
	static int bitmap_offset_v = 5;
	
	static int name_offset_h = 2*bitmap_offset_h + bitmap_dimension;
	static int description_offset_v = height/2;
	
	static Bitmap focus_background = Bitmap.getBitmapResource("images/listItem_background.png");
	static Font name_font;
	static Font category_font;
	WebImageField profilePicture;
	boolean focusable;
	private String title;
	private String description;
	
	public ListItem( String _title, String _description, String _logo, boolean _focusable){
		super();
		title = _title;
		description = _description;
		profilePicture = new WebImageField(AirCrew.image_medium + _logo, bitmap_dimension, bitmap_dimension);
//		ListingField(AirCrew.image_medium + deal.getLogo(), deal.getName(), deal.getCategory_name(),8);
//		Images.profile_pics[3].scaleInto(0, 0, Images.profile_pics[3].getWidth(), Images.profile_pics[3].getHeight(), profilePicture, 0, 0, profilePicture.getWidth(), profilePicture.getHeight(), Bitmap.FILTER_BILINEAR);
		focusable = _focusable;
		
		category_font = Font.getDefault().derive(0, 5, Ui.UNITS_pt);
		name_font = Font.getDefault().derive(0, 7, Ui.UNITS_pt);
	}
	
	protected void paint(Graphics g){
		if(isFocus()){
			g.drawBitmap(0,0, width, height, focus_background, 0, 0);
		}
		g.drawBitmap(bitmap_offset_h, bitmap_offset_v, bitmap_dimension, bitmap_dimension, profilePicture.returnBitmap(), 0, 0);
		g.setColor(Color.WHITE);
		g.setFont(name_font);
		g.drawText(title, name_offset_h , bitmap_offset_v,DrawStyle.ELLIPSIS, Display.getWidth() - name_offset_h - bitmap_offset_h);
		g.setFont(category_font);
		g.drawText(description, name_offset_h, description_offset_v, DrawStyle.ELLIPSIS, Display.getWidth() - name_offset_h - bitmap_offset_h);
		g.setColor(0x555555);
		if(focusable)
			g.fillRect(5, 70, width-10, 2);
	}

	protected void layout(int _width, int _height) {
		setExtent(width, height);
	}
	
	protected void drawFocus(Graphics _graphics, boolean on) {
		
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
}
