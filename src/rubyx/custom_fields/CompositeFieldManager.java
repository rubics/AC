package rubyx.custom_fields;

import net.rim.device.api.ui.container.VerticalFieldManager;

public class CompositeFieldManager extends VerticalFieldManager{
	
	public CompositeFieldManager(long style){
		super(style);
	}
	
	public CompositeFieldManager(){
		this(0);
	}
	
	protected void sublayout(int width , int height){
		
		super.sublayout(width, height); // lays the child as done by default
		
		// set the display character of the field
		int fieldcount = getFieldCount();
		if(fieldcount == 0)
			return;
		else if(fieldcount==1){

			if(CompositeField.class.isInstance(getField(0)))
				((CompositeField)getField(0)).setDrawStyle(CompositePasswordBox.DRAWSTYLE_SINGLE);
			return;
		} else {
			if(CompositeField.class.isInstance(getField(0)))
				((CompositeField)getField(0)).setDrawStyle(CompositePasswordBox.DRAWSTYLE_TOP);
			if(CompositeField.class.isInstance(getField(fieldcount-1)))
				((CompositeField)getField(fieldcount-1)).setDrawStyle(CompositePasswordBox.DRAWSTYLE_BOTTOM);
			return;
			
		}
	}	
}