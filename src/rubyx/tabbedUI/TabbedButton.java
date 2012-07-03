package rubyx.tabbedUI;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Ui;

public class TabbedButton extends Field {
	
	public static final int DRAWSTYLE_FIRST = 2;
	public static final int DRAWSTYLE_MIDDLE = 3;
	public static final int DRAWSTYLE_LAST = 5;
	public static final int DRAWSTYLE_SINGLE = 7;
	private int FOREGROUND_COLOR = 0xF57000;
	private int FOCUS_COLOR = 0x186DEF;
	private int SELECTION_COLOR = 0x858585;
	private int FONT_COLOR = Color.WHITE;
	private static final int FONTSIZE_BIG = 10;
	private static final int FONTSIZE_MEDIUM = 8;
	private static final int FONTSIZE_SMALL = 6;
	
	private static final int H_OFFSET = 15;
	private static final int B_OFFSET = 1;
	private static final int V_OFFSET = 3;
	private int R_VALUE = 20;
	private int H_FIRST = H_OFFSET + R_VALUE;
	
	String label;
	boolean focusable=true;
	private boolean isSelected = false;
	private int drawStyle = DRAWSTYLE_SINGLE;
	private int width;
	private int height;
	private boolean dimensions = false;
	
	public TabbedButton(String _label, int _fontSize){
		super(Field.FIELD_HCENTER);
		label = _label;
		setFont(Font.getDefault().derive(Font.PLAIN, _fontSize, Ui.UNITS_pt));
	}
	
	public TabbedButton(String _label, int _fontSize, int _width, int _height){
		this(_label, _fontSize);
		width = _width;
		height = _height;
		dimensions = true;	
	}
	
	protected void layout(int _width, int _height) {
		if (dimensions)
			setExtent(width, height);
		else
			setExtent(_width, _height);	
	}

	protected void paint(Graphics graphics) {
		
		
			if(isFocus())
				graphics.setColor(FOCUS_COLOR);
			else if(isSelected)
				graphics.setColor(SELECTION_COLOR);
			else
				graphics.setColor(FOREGROUND_COLOR);
		
		switch (drawStyle){
			case DRAWSTYLE_FIRST:
				graphics.fillRoundRect(H_OFFSET, V_OFFSET, getWidth()-(H_OFFSET+B_OFFSET), getHeight()-2*V_OFFSET, R_VALUE, R_VALUE);
				graphics.fillRect(getWidth()-H_FIRST, V_OFFSET, H_FIRST, getHeight()-2*V_OFFSET);
				graphics.setGlobalAlpha(40);
				graphics.setColor(Color.WHITE);
				graphics.fillRect(H_OFFSET, V_OFFSET, getWidth()-(H_OFFSET+B_OFFSET), (getHeight()-2*V_OFFSET)/2);
				break;
			case DRAWSTYLE_LAST:
				graphics.fillRoundRect(B_OFFSET, V_OFFSET, getWidth()-(H_OFFSET+B_OFFSET), getHeight()-2*V_OFFSET, R_VALUE, R_VALUE);
				graphics.fillRect(0, V_OFFSET, H_FIRST, getHeight()-2*V_OFFSET);
				graphics.setGlobalAlpha(40);
				graphics.setColor(Color.WHITE);
				graphics.fillRect(B_OFFSET, V_OFFSET, getWidth()-(H_OFFSET+B_OFFSET), (getHeight()-2*V_OFFSET)/2);
				
				break;
			case DRAWSTYLE_MIDDLE:
				graphics.fillRect(B_OFFSET, V_OFFSET, getWidth()-2*B_OFFSET, getHeight()-2*V_OFFSET);
				graphics.setGlobalAlpha(40);
				graphics.setColor(Color.WHITE);
				graphics.fillRect(B_OFFSET, V_OFFSET, getWidth()-2*B_OFFSET, (getHeight()-2*V_OFFSET)/2);
				break;
			case DRAWSTYLE_SINGLE:
			default:
				graphics.fillRoundRect(H_OFFSET, V_OFFSET, getWidth()-2*H_OFFSET, getHeight()-2*V_OFFSET,R_VALUE,R_VALUE);
				graphics.setGlobalAlpha(40);
				graphics.setColor(Color.WHITE);
				graphics.fillRect(H_OFFSET, V_OFFSET, getWidth()-2*H_OFFSET, getHeight()/2-V_OFFSET);
		}
		graphics.setGlobalAlpha(255);
		graphics.setColor(FONT_COLOR);
		graphics.drawText(label, (getWidth()-getFont().getAdvance(label))/2, (getHeight()-getFont().getHeight())/2);
	}
	
	protected void paintBackground(Graphics graphics){

	}
	
	public void setRVAlue(int _rValue){
		R_VALUE = _rValue;
		H_FIRST = H_OFFSET + R_VALUE;;
	}
	
	protected void drawFocus(Graphics graphics, boolean mode){
		
	}
	
	public void setDrawStyle(int _drawstyle){
		drawStyle = _drawstyle;
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
		if(TabbedButtonManager.class.isInstance(getManager()))
			((TabbedButtonManager)getManager()).setSelection(getIndex());
		super.fieldChangeNotify(context);
	}
	
	public void setSelection(boolean selection){
		isSelected = selection;
	}
	
	public void setColorScheme(int foreground, int focus, int selection, int font){
		FOREGROUND_COLOR = foreground;
		FOCUS_COLOR = focus;
		SELECTION_COLOR = selection;
		FONT_COLOR = font;
		invalidate();
	}
}
