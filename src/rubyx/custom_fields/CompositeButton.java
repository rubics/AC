package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import app.models.Images;

public class CompositeButton extends Field implements CompositeField {

	private int width = Display.getWidth();
	private int height = 46 ;
	
	private boolean focusable = true;
	
	public int color_background = Color.BLACK;
	public int color_foreground = Color.WHITE;
	public int color_focus = 0x186DEF;
	public int color_font = Color.BLACK;
	
	
	private static final int h_offset = 15;
	private static final int v_offset = 4;
	private static final int v_offset_correction = 0;
	private static final int r_offset = 20;
	private static final int b_offset =1;
	
	private int xb = width/2;
	private int y = 2*v_offset + v_offset_correction;
	private int field_width = xb - 2*h_offset;
	private int field_height = height - 2*v_offset;
	
	private static final Font font_composite_label = Font.getDefault().derive(Font.PLAIN, 20, Ui.UNITS_px);
	
	private int drawstyle;
	private String label;
		
	private final int xi = width - (8*v_offset + Images.arrowhead.getWidth());
	private final int yi = (height - Images.arrowhead.getHeight())/2;
	
	
	
	public CompositeButton(String label){		
		super(0);
		this.label = label;
		field_height = font_composite_label.getHeight(Ui.UNITS_px);
	}
	
	protected void layout(int _width, int _height){
		
		setExtent(width, height);		
	}
	
	protected void paint(Graphics g){
		
		if(isFocus())
			g.setColor(color_focus);
		else
			g.setColor(color_foreground);
		
		switch(drawstyle){

		case DRAWSTYLE_TOP:
			g.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			g.fillRect(h_offset, height/2,width-2*h_offset,height/2 - b_offset);
			break;
			
		case DRAWSTYLE_BOTTOM:
			g.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			g.fillRect(h_offset, b_offset, width-2*h_offset, height/2-b_offset);
			break;
		case DRAWSTYLE_SINGLE:
			g.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			break;
			
		case DRAWSTYLE_MID:
		default:
			g.fillRect(h_offset,b_offset, width - 2*h_offset, height - 2*b_offset);
			break;
		}
		g.setGlobalAlpha(40);
		g.setColor(Color.WHITE);
		g.fillRect(h_offset, v_offset,width-2*h_offset,height/2 - v_offset);
		g.setGlobalAlpha(255);
		g.drawBitmap(xi, yi, Images.arrowhead.getWidth(), Images.arrowhead.getHeight(), Images.arrowhead, 0, 0);
		g.setColor(color_font);
		g.setFont(font_composite_label);
		g.drawText(label, 2*h_offset, (height - field_height)/2);
	}
	
	protected void drawFocus(Graphics _graphics, boolean on) {

	}
	
	public void setColorScheme(int _color_foreground, int _color_font){
		color_foreground = _color_foreground;
		color_font = _color_font;
	}

	public void setDrawStyle(int _drawstyle){
		drawstyle = _drawstyle;
	}
	
	//============================================================================================//
	
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
	
	public boolean isFocusable(){
		return true;
	}

}
