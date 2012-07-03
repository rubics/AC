package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.RichTextField;

public class __CustomRichTextField extends RichTextField{
	
	private final Font font = Font.getDefault().derive(Font.PLAIN, 6, Ui.UNITS_pt);
	private int width;
	
	public __CustomRichTextField(String string, int _width){
		super(string);
		setFont(font);
		width = _width;
	}
	
	protected void layout(int w, int h){
		super.layout(width, h);
	}
	protected void paint(Graphics graphics){
		graphics.setColor(Color.WHITE);
		super.paint(graphics);
	}

	protected void drawFocus(Graphics graphics, boolean mode){
		
	}
}
	