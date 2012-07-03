package app.views.fields.profile;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import rubyx.layout_managers.TableLayoutManager;

public class GridViewController extends TableLayoutManager {
	
		
	public GridViewController(int[] columnStyles, long style){
		super(columnStyles, style);
	}
	public GridViewController(int[] columnStyles, int[] columnWidths, int horizontalpadding, long style ){
		super(columnStyles, columnWidths, horizontalpadding, style);
	}
	
	public void add(Field field){
		super.add(field);		
	}
	
	public void drawFocus(Graphics graphics, boolean mode){
		
	}
}
