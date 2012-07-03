package rubyx.tabbedUI;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class TabbedButtonManager extends HorizontalFieldManager {
	private int width;
	private int height;
	
	private boolean selectable = false;
	private int selectedIndex = 0;
	
	public TabbedButtonManager(int _width, int _height){
		super(Field.FIELD_HCENTER);
		width = _width;
		height = _height;
	}
	
	public TabbedButtonManager(int _width, int _height, boolean _selectable, int _selectedIndex){
		this(_width, _height);
		selectable = _selectable;
		selectedIndex = _selectedIndex;
	}
	
	protected void sublayout(int _width, int _height){
		int fieldcount = getFieldCount();
		if(fieldcount == 0)
			return;
		else if(fieldcount==1){
			if(TabbedButton.class.isInstance(getField(0)))
				((TabbedButton)getField(0)).setDrawStyle(TabbedButton.DRAWSTYLE_SINGLE);
		} else {
			if(TabbedButton.class.isInstance(getField(0)))
				((TabbedButton)getField(0)).setDrawStyle(TabbedButton.DRAWSTYLE_FIRST);
			if(TabbedButton.class.isInstance(getField(fieldcount-1)))
				((TabbedButton)getField(fieldcount-1)).setDrawStyle(TabbedButton.DRAWSTYLE_LAST);
			for (int i=1; i<fieldcount-1; i++){
				if(TabbedButton.class.isInstance(getField(i)))
					((TabbedButton)getField(i)).setDrawStyle(TabbedButton.DRAWSTYLE_MIDDLE);
			}
		}

		int w = width/fieldcount;
		for(int i=0; i<fieldcount; i++){
			Field field = getField(i);
			setPositionChild(field, i*w, 0);
			layoutChild(field, w, height);
		}
		setSelection(selectedIndex);
		setExtent(width, height);
	}
	
	public void setSelection(int index){
		if(selectable){
			selectedIndex = (index < getFieldCount()) ? index : getFieldCount()-1;
			for(int i=0; i<getFieldCount(); i++){
				((TabbedButton)getField(i)).setSelection(false);
			}
			((TabbedButton)getField(selectedIndex)).setSelection(true);
			invalidate();
		}
	}
	
	public int getSelction(){
		return selectedIndex;
	}
	
	public void setColorScheme(int foreground, int focus, int selection, int font){
		for(int i=0; i<getFieldCount(); i++){
			((TabbedButton)getField(i)).setColorScheme(foreground, focus, selection, font);
		}
	}
}
