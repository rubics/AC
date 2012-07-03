package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.LabelField;

public class CustomDateField extends Manager implements CompositeField{
	private int width = Display.getWidth();
	private int height = 46 ;

	public boolean hasChanged =  false;
	private boolean focusable = true;
	
	public int color_background = Color.BLACK;
	public int color_foreground = Color.WHITE;//0x606060;
	public int color_font = Color.BLACK;
	
	
	private static final int h_offset = 15;
	private static final int v_offset = 7;
	private static final int v_offset_correction = 0;
	private static final int r_offset = 20;
	private static final int b_offset =1;
	
	private int xb = width/2;
	private static final int y = 2*v_offset + v_offset_correction;
	private int field_width = xb - 2*h_offset;
	private int field_height = height - 2*v_offset;
	
	private static final Font font_composite_label = Font.getDefault().derive(Font.PLAIN, 20, Ui.UNITS_px);
	
	private int drawstyle;
	
	private LabelField labelField;
	private DateField dateField;
	
	public CustomDateField(String _label, long _start_date, long _style){
		super(_style);
		labelField = new LabelField(_label, 0);
		labelField.setFont(font_composite_label);
		dateField = new DateField("", _start_date, _style);
		labelField.setFont(font_composite_label);
		add(labelField);
		add(dateField);
	}
	protected void sublayout(int _width, int _height){
		layoutChild(getField(0), field_width, field_height);
		layoutChild(getField(1), field_width, field_height);
		
		setPositionChild(getField(0), 2*h_offset, y);
		setPositionChild(getField(1),width/2, y);
		setExtent(width, height);
	}
	protected void paint(Graphics g){
		g.setColor(color_foreground);
		
		switch(drawstyle){

		case DRAWSTYLE_TOP:
			g.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			g.fillRect(h_offset, height/2,width-2*h_offset,height/2 - b_offset);
			break;
			
		case DRAWSTYLE_BOTTOM:
			g.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			g.fillRect(h_offset, b_offset, width-2*h_offset, height/2-b_offset);
			
		case DRAWSTYLE_SINGLE:
			g.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			break;
			
		case DRAWSTYLE_MID:
		default:
			g.fillRect(h_offset,b_offset, width - 2*h_offset, height - 2*b_offset);
			break;
		}		
		g.setColor(color_font);
//			g.setColor(Color.GRAY);
//			g.fillRoundRect(5, 5, width-10, height-10, 20, 20);
//			g.setColor(Color.WHITE);
		super.paint(g);
	}
	public void setDrawStyle(int drawStyle) {
		// TODO Auto-generated method stub
		
	}
}
