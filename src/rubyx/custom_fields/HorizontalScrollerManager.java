package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Manager;

public class HorizontalScrollerManager extends Manager{
	private int height;
	public HorizontalScrollerManager(long style, int height){
		super(Manager.HORIZONTAL_SCROLL | Manager.HORIZONTAL_SCROLLBAR);
		this.height = height;
	}
	
	protected void sublayout(int width, int height) {
		for(int i=0; i<getFieldCount(); i++){
			layoutChild(getField(i), Display.getWidth(), this.height);
//			setPositionChild(getField(i), Display.getWidth()*i + (Display.getWidth()- getField(i).getWidth())/2, (Display.getHeight() - getField(i).getHeight())/2);
			setPositionChild(getField(i), Display.getWidth()*i, 0);
		}
		setExtent(Display.getWidth()*getFieldCount(), this.height);
	}
}
