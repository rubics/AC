package app.views.fields.deals;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.ButtonField;
import rubyx.custom_fields.CustomTextArea;
import rubyx.custom_fields.WebImageField;
import app.models.AirCrew;
import app.models.DealDetails;

public class CompositeDealLabel extends Manager {
	private WebImageField image;
	private CustomTextArea descriptionField;
	private final int descriptionFieldWidth = Display.getWidth();
	private Field buttonField;
	
	private static final Font font = Font.getDefault().derive(Font.PLAIN, 6, Ui.UNITS_pt);
		
	public CompositeDealLabel(DealDetails _dealDetails){
		super(Manager.VERTICAL_SCROLL);

		descriptionField = new CustomTextArea(_dealDetails.getDescription(), descriptionFieldWidth - 100, font);
		descriptionField.color = Color.WHITE;
		add(descriptionField);
		buttonField = new ButtonField("More Details");
//		buttonField.setChangeListener(_listener);
		add(buttonField);
	}
	
	protected void sublayout(int width, int height) {
		setPositionChild(getField(0),40, 0);
		setPositionChild(getField(1),40, descriptionField.getPreferredHeight() + 20);
		layoutChild(getField(0), width, height);
		layoutChild(getField(1), width, height);
		setExtent(width, descriptionField.getPreferredHeight() + 35 + buttonField.getHeight());
	}
	
	protected void paint(Graphics graphics){
//		graphics.drawBitmap(20, 10, image.get_width(), image.get_height(), image.returnBitmap(), 0, 0);
		super.paint(graphics);
	}
	
	protected void drawFocus(Graphics graphics, boolean mode){
		
	}

}
