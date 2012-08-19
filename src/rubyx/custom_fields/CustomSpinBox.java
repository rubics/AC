package rubyx.custom_fields;

import rubyx.custom_fields.spinbox.SpinBoxPopup;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;

public class CustomSpinBox extends Field{

	Object[] choices;
	SpinBoxPopup spinBoxPopup;
	String placeholder;
	String value = "";
	
	private static final Font font_composite_label = Font.getDefault().derive(Font.PLAIN, 24, Ui.UNITS_px);
	        
	public CustomSpinBox(Object[] choices, String placeholder){
		super(Field.FIELD_RIGHT);
		this.choices = choices;
		spinBoxPopup = new SpinBoxPopup(choices, new SpinBoxPopup.SpinBoxListener() {
			public void selectionChanged(Object selected) {
				value = selected.toString();
				invalidate();
			}
		});
		this.placeholder = placeholder;
		this.value = placeholder;
		setFont(font_composite_label);
	}
	protected void layout(int width, int height) {
		setExtent(width, getFont().getHeight());
	}

	protected void paint(Graphics graphics) {
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		if(isFocus())
			graphics.setColor(0x186DEF);
		else
			graphics.setColor(Color.BLACK);
		graphics.drawText(value, getWidth()-font_composite_label.getAdvance(value), (getHeight() - font_composite_label.getHeight())/2, Graphics.ELLIPSIS, getWidth());
	}
	//----------------------------------------------------------------------//
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
		UiApplication.getUiApplication().pushScreen(spinBoxPopup);
	}
	
	public boolean isFocusable(){
		return true;
	}

}
