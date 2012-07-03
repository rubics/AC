package rubyx.custom_fields;

import net.rim.device.api.system.Display;

public class CheckboxManager extends RoundedBackgroundManager{

	public CheckboxManager(long style, int width, int height) {
		super(style, width, height);
	}
	
	protected void sublayout(int _width, int _height){
		setPositionChild(getField(0),2*h_offset, v_offset + v_correction);
		setPositionChild(getField(1),2*h_offset, height/2);
		setPositionChild(getField(2), width/2, height/2);
		
		layoutChild(getField(0), width-2*h_offset  , height/2 - v_offset);
		layoutChild(getField(1), width/2-h_offset  , height/2 - v_offset);
		layoutChild(getField(2), width/2-h_offset  , height/2 - v_offset);
		setExtent(width, height);
	}
	
}
