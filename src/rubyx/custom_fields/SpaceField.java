package rubyx.custom_fields;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class SpaceField extends Field {
	
	int height;
	int color;
	
	public SpaceField(int _height){
		height = _height;
	}
	
	protected void layout(int width, int height){
		setExtent(width, this.height);		
	}
	
	protected void paint(Graphics graphics){

	}	
}
