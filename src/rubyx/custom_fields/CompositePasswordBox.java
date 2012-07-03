package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;


public class CompositePasswordBox extends Manager implements CompositeField{
	
	private final LabelField labelField;
	private PasswordEditField editField;
	private HorizontalFieldManager hrManager;
	
	private int width = Display.getWidth();
	private int height = 46 ;
	
	public int color_background = Color.BLACK;
	public int color_foreground = Color.WHITE;
	public int color_font = Color.BLACK;
	
	private final String value;
	
	private int drawstyle;	
	
	public boolean hasChanged =  false;
	
	
	private static final int h_offset = 15;
	private static final int v_offset =7;
	private static final int r_offset = 20;
	private static final int b_offset =1;

	private final FieldChangeListener editFieldListener = new FieldChangeListener(){
		public void fieldChanged(Field field, int context){
			
			if(value.equals(editField.getText()))
				hasChanged = false;
			else
				hasChanged = true;		
		}
	};
	
	
	public CompositePasswordBox(String label, String value, boolean editable, int width){
		this(label, value,editable);
		this.width = width;
	}
	public CompositePasswordBox(String label, String value, boolean editable){
		super(0);
		
		labelField = new LabelField(label);
		labelField.setFont(getFont().derive(getFont().getStyle(), 20, Ui.UNITS_px));
		
		editField = new PasswordEditField();		
		editField.setEditable(editable);
		editField.setFont(getFont().derive(getFont().getStyle(), 20, Ui.UNITS_px));
		
		hrManager  =  new HorizontalFieldManager(Manager.HORIZONTAL_SCROLL);
		hrManager.add(editField);
		if(value.equals("") | value.equals("null")){
			this.value = "";
			editField.setText("");
		}
		else{
			this.value = value; 
			editField.setText(value);			
		}
		
		editField.setChangeListener(editFieldListener);
		
		add(labelField);
		add(hrManager);
		
	}
	
//	public void setChangeListener(FieldChangeListener listener){
//		editField.setChangeListener(listener);
//	}
	
	public void setText(String value){
		if(value.equals("") | value.equals("null"))
			editField.setText("-");
		else
			editField.setText(value);
	}
	
	public String getText(){
		return editField.getText();
	}
	
	public void setDrawStyle(int _drawstyle){
		drawstyle = _drawstyle;
	}
	
	public void setEditable(boolean editable){
		editField.setEditable(editable);
	}
	
	
	protected void sublayout(int _width, int _height){
		
		
		layoutChild(getField(0), width/2-2*h_offset, height- 2*v_offset);
		layoutChild(getField(1), width/2-2*h_offset, height- 2*v_offset);
		
		setPositionChild(getField(0), 2*h_offset, (height-getField(0).getHeight())/2);
		setPositionChild(getField(1),width/2, (height-getField(0).getHeight())/2);
		
		setExtent(width, height);
		
	}
	
	protected void paintBackground(Graphics g){
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
	}
	
	protected void drawFocus(Graphics _graphics, boolean on) {

	}
	
	public void setColorScheme(int _color_foreground, int _color_font){
		color_foreground = _color_foreground;
		color_font = _color_font;
	}
	
	public String toString(){
		return "card["+labelField.getText()+"]=" + editField.getText();
	}
	
	public String toJSON(){
		return("\"" + labelField.getText() + "\":\"" + editField.getText()+"\"," );
	}
	
	public String getLabelText(){			//   for CardInfoEditScreen, key strings are used as labels unlike in CardInfoScreen
		return labelField.getText();
	}
	
	public String getEditFieldText(){
		return editField.getText();
	}

}


