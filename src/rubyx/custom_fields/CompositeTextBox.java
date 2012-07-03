package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;


public class CompositeTextBox extends Manager implements CompositeField{
	
	private int width = Display.getWidth();
	private int height = 46 ;
	
	private final String value;
	
	public boolean hasChanged =  false;
	private boolean focusable = true;
	
	public int color_background = Color.BLACK;
	public int color_foreground = Color.WHITE;//0x606060;
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
	private final LabelField labelField;
	public EditField editField;
	private HorizontalFieldManager hrManager;
	
	private final FieldChangeListener editFieldListener = new FieldChangeListener(){
		public void fieldChanged(Field field, int context){
			
			if(value.equals(editField.getText()))
				hasChanged = false;
			else
				hasChanged = true;	
		}
	};
	
	
	public CompositeTextBox(String label, String value, boolean editable,int width){
		
		this(label, value, editable);
		this.width = width;
	}
	
	
	public CompositeTextBox(String label, String value, boolean editable, boolean focusable){
		this(label, value, editable);
		this.focusable = focusable;
	}
	
	public CompositeTextBox(String label, String value, boolean editable){
		
		super(0);
		
		field_height = font_composite_label.getHeight(Ui.UNITS_px);

		labelField = new LabelField(label);
		labelField.setFont(font_composite_label);
		
		editField = new EditField(TextField.NO_NEWLINE | TextField.JUMP_FOCUS_AT_END );
		editField.setNonSpellCheckable(true);
		editField.setEditable(editable);
		editField.setFont(font_composite_label);
		
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
	
	protected void sublayout(int _width, int _height){
		
		
		layoutChild(getField(0), field_width, field_height);
		layoutChild(getField(1), field_width, field_height);
		
		setPositionChild(getField(0), 2*h_offset, (height - field_height)/2);
		setPositionChild(getField(1),width/2, (height - field_height)/2);
		
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
	
	public boolean isFocusable(){
		return focusable;
	}
	
	public void setText(String value){
		if(value.equals("") | value.equals("null"))
			editField.setText("");
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

}


