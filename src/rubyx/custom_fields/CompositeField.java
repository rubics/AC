package rubyx.custom_fields;

import net.rim.device.api.ui.Color;

public interface CompositeField   {
	
	public static final int DRAWSTYLE_SINGLE = 1 ;
	public static final int DRAWSTYLE_TOP =  2;
	public static final int DRAWSTYLE_MID = 3 ;
	public static final int DRAWSTYLE_BOTTOM =  4;
	
	public abstract void setDrawStyle(int drawStyle);
}
