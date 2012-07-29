package app.views.fields.deals;

import net.rim.device.api.system.Bitmap;
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
import app.models.Deal;
import app.models.Images;

public class CompositeDealLabel extends Manager {
	private WebImageField image;
	private final int imageFieldWidth = 160;
	private CustomTextArea descriptionField;
	private final int descriptionFieldWidth = 300;
	private Field buttonField;
	
	private static final Font font = Font.getDefault().derive(Font.PLAIN, 6, Ui.UNITS_pt);
		
	public CompositeDealLabel(Deal _deal){
		super(Manager.VERTICAL_SCROLL);
		image = new WebImageField(AirCrew.image_medium + _deal.getLogo(),140,140); 
//		Images.profile_pics[3].scaleInto(image, Bitmap.FILTER_BILINEAR, Bitmap.SCALE_TO_FIT);
		descriptionField = new CustomTextArea(_deal.getDealDetails().getDescription(), descriptionFieldWidth - 10, font);
		descriptionField.color = Color.WHITE;
		add(descriptionField);
		buttonField = new ButtonField("More Details");
//		buttonField.setChangeListener(_listener);
		add(buttonField);
//		add(image);
	}
	
	protected void sublayout(int width, int height) {
		setPositionChild(getField(0), imageFieldWidth + 20, 0);
		setPositionChild(getField(1), imageFieldWidth + 20, descriptionField.getPreferredHeight() + 20);
//		setPositionChild(getField(2), 20, 10);
		layoutChild(getField(0), imageFieldWidth, imageFieldWidth);
		layoutChild(getField(1), descriptionFieldWidth, height);
//		layoutChild(getField(2), )
		setExtent(width, descriptionField.getPreferredHeight() + 35 + buttonField.getHeight());
	}
	
	protected void paint(Graphics graphics){
		graphics.drawBitmap(20, 10, image.get_width(), image.get_height(), image.returnBitmap(), 0, 0);
		super.paint(graphics);
	}
	
	protected void drawFocus(Graphics graphics, boolean mode){
		
	}

}
