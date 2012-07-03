package app.views.fields.deals;

import rubyx.custom_fields.CustomTextArea;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.ButtonField;

public class CompositeDealLabel extends Manager {
	private Bitmap image;
	private final int imageFieldWidth = 160;
	private CustomTextArea descriptionField;
	private final int descriptionFieldWidth = 300;
	private Field buttonField;
	
	private static final Font font = Font.getDefault().derive(Font.PLAIN, 6, Ui.UNITS_pt);
		
	public CompositeDealLabel(Bitmap _image, String _description, FieldChangeListener _listener){
		super(Manager.VERTICAL_SCROLL);
		image = new Bitmap(140,140); 
		_image.scaleInto(image, Bitmap.FILTER_BILINEAR, Bitmap.SCALE_TO_FIT);
		descriptionField = new CustomTextArea(_description, descriptionFieldWidth - 10, font);
		descriptionField.color = Color.WHITE;
		add(descriptionField);
		buttonField = new ButtonField("More Details");
		buttonField.setChangeListener(_listener);
		add(buttonField);
	}
	
	protected void sublayout(int width, int height) {
		setPositionChild(getField(0), imageFieldWidth + 20, 0);
		setPositionChild(getField(1), imageFieldWidth + 20, descriptionField.getPreferredHeight() + 20);
		layoutChild(getField(0), imageFieldWidth, imageFieldWidth);
		layoutChild(getField(1), descriptionFieldWidth, height);
		setExtent(width, descriptionField.getPreferredHeight() + 35 + buttonField.getHeight());
	}
	
	protected void paint(Graphics graphics){
		graphics.drawBitmap(20, 10, image.getWidth(), image.getHeight(), image, 0, 0);
		super.paint(graphics);
	}
	
	protected void drawFocus(Graphics graphics, boolean mode){
		
	}

}
