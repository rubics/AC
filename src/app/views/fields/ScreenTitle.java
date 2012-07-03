package app.views.fields;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Ui;

public class ScreenTitle extends Field{
	
	private final Font font = Font.getDefault().derive(Font.PLAIN, 8, Ui.UNITS_pt);
	
	private final Bitmap titleBar = Bitmap.getBitmapResource("images/title_bar.png");
	private String title;
	
	public ScreenTitle(String _title){
		title = _title;
	}

	protected void layout(int width, int height) {
		setExtent(titleBar.getWidth(), titleBar.getHeight());		
	}

	protected void paint(Graphics graphics) {
		graphics.drawBitmap(0, 0, titleBar.getWidth(), titleBar.getHeight(), titleBar, 0, 0);
		graphics.drawText(title, (getWidth() - getFont().getAdvance(title))/2, (getHeight()-getFont().getHeight())/2);
	}
	
	public boolean isFocusable(){
		return false;
	}
}
