package rubyx.custom_fields;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import rubyx.custom_fields.CompositeField;

public class RoundedBackgroundManager extends Manager implements CompositeField{

	protected int width;
	protected int height;
	
	protected static final int h_offset = 15;
	protected static final int v_offset = 7;
	protected static final int v_correction = 3;
	protected static final int r_offset = 20;
	protected static final int b_offset =1;
	
	protected int xb = width/2;
	protected static final int y = 2*v_offset;
	protected int field_width = xb - 2*h_offset;
	protected int field_height = height - 2*v_offset;
	
	public static final Font font_composite_label = Font.getDefault().derive(Font.PLAIN, 20, Ui.UNITS_px);
	protected int drawstyle = DRAWSTYLE_SINGLE;

	protected RoundedBackgroundManager(long style, int width, int height){
		super(style | Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR );
		this.width = width;
		this.height = height;
	}

	protected void sublayout(int _width, int _height) {
		setExtent(this.width, this.height);
	}
	
	protected void paint(Graphics g){

		g.setColor(Color.WHITE);
		
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
		g.setFont(font_composite_label);
		g.setColor(Color.BLACK);
		super.paint(g);
	}

	public void setDrawStyle(int drawStyle) {
	
	}
	
	
	
}
