package rubyx.tabbedUI;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;

public class TabbedPaneManager extends Manager{

	private final Bitmap manager_background = Bitmap.getBitmapResource("images/deals/tabbed_button_background.png");
	private final Bitmap first_focus_image = Bitmap.getBitmapResource("images/deals/tabbed_button_first_focus.png");
	private final Bitmap focus_image = Bitmap.getBitmapResource("images/deals/tabbed_button_focus.png");
	private final Bitmap last_focus_image = Bitmap.getBitmapResource("images/deals/tabbed_button_last_focus.png");
	private final Bitmap[] focus_images = {first_focus_image, focus_image, focus_image, last_focus_image};
	private final Bitmap[] foregrounds = {Bitmap.getBitmapResource("images/deals/email.png"),
			Bitmap.getBitmapResource("images/deals/video.png"),
			Bitmap.getBitmapResource("images/deals/location.png"),
			Bitmap.getBitmapResource("images/deals/bookmark.png")};
	private final int height = 50;
	
	public TabbedPaneManager(long style){
		super(style);
	}
	
	protected void sublayout(int w, int h) {
		int field_width = Display.getWidth()/getFieldCount();
		for(int i=0; i<getFieldCount(); i++){
			((TabbedPaneButton)getField(i)).setFocusBackground(focus_images[i]);
			((TabbedPaneButton)getField(i)).setForeground(foregrounds[i]);
			setPositionChild(getField(i),i*field_width, 0);
			layoutChild(getField(i),field_width, height);
		}
		setExtent(Display.getWidth(), height);
	}
	
	protected void paint(Graphics graphics){
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.drawBitmap(0, 0, manager_background.getWidth(), manager_background.getHeight(), manager_background, 0, 0);
		super.paint(graphics);
	}
}