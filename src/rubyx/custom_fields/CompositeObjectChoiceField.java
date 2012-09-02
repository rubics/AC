package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;

public class CompositeObjectChoiceField extends Manager implements CompositeField{
	private int width = Display.getWidth();
	private int height = 46;
	
	private static final int h_offset = 15;
	private static final int v_offset = 4;
	private static final int v_offset_correction = 0;
	private static final int r_offset = 20;
	private static final int b_offset =1;
	
	public int color_background = Color.BLACK;
	public int color_foreground = Color.WHITE;
	public int color_font = Color.BLACK;
	
	private int xb = width/2;
	private static final int y = 2*v_offset + v_offset_correction;
//	private int field_width = xb - 2*h_offset;
	private int field_height = height - 2*v_offset;
	
	private static final Font font_composite_label = Font.getDefault().derive(Font.PLAIN, 20, Ui.UNITS_px);
	
	private int drawstyle;
	
	private LabelField labelField;
	private ObjectChoiceField objectChoiceField;
	private Object[] choices;
	
	public CompositeObjectChoiceField(String _label, Object[] _objects, int _startIndex){
		super(0);
		choices = _objects;
		labelField = new LabelField(_label);
		labelField.setFont(font_composite_label);
		add(labelField);
		objectChoiceField = new ObjectChoiceField();
		objectChoiceField.setChoices(_objects);
		if (_startIndex > 0)
			objectChoiceField.setSelectedIndex(_startIndex);
		objectChoiceField.setFont(font_composite_label);
		add(objectChoiceField);
	}

	
	protected void sublayout(int _width, int _height){
		layoutChild(getField(0), _width, field_height);
		layoutChild(getField(1), Display.getWidth() - (getField(0).getWidth() + 4*h_offset), field_height);
		
		setPositionChild(getField(0), 2*h_offset, (height - getField(0).getHeight())/2);
		setPositionChild(getField(1),2*h_offset + getField(0).getWidth(), (height - getField(1).getHeight())/2);
		
		setExtent(width, height);
	}
	protected void paintBackground(Graphics graphics){
		graphics.setColor(color_foreground);
		
		switch(drawstyle){

		case DRAWSTYLE_TOP:
			graphics.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			graphics.fillRect(h_offset, height/2,width-2*h_offset,height/2 - b_offset);
			break;
			
		case DRAWSTYLE_BOTTOM:
			graphics.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			graphics.fillRect(h_offset, b_offset, width-2*h_offset, height/2-b_offset);
			
		case DRAWSTYLE_SINGLE:
			graphics.fillRoundRect(h_offset, v_offset, width-2*h_offset, height-2*v_offset, r_offset	, r_offset);
			break;
			
		case DRAWSTYLE_MID:
		default:
			graphics.fillRect(h_offset,b_offset, width - 2*h_offset, height - 2*b_offset);
			break;
		}		
		graphics.setColor(color_font);
//		super.paint(graphics);
	}
	public void setDrawStyle(int _drawStyle) {
		drawstyle = _drawStyle;
	}
	
	public void setColorScheme(int _color_foreground, int _color_font){
		color_foreground = _color_foreground;
		color_font = _color_font;
	}
	
	public void setListener(FieldChangeListener listener){
		objectChoiceField.setChangeListener(listener);
	}
	
	public int getSelectedIndex(){
		return objectChoiceField.getSelectedIndex();
	}
	
	public Object getSelectedObject(){
		return choices[objectChoiceField.getSelectedIndex()];
	}
}
