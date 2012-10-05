package rubyx.custom_fields;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;

// this class is extended by EventCategory , CustomListFiled not directly implemented in this project
public class ScreenBannar extends Manager {


	final int height;
	final int width = Display.getWidth();

	private String labelname;

	Field aField;
	Field bField;

	final int offset = 5;

	public ScreenBannar(String labelname, int height) {
		this(labelname, height, null, null);
	}

	public ScreenBannar(String labelname,int height, Field a_field, Field b_field) {

		super(0);
		this.labelname = labelname;
		this.height = height;

		if (a_field != null && a_field.getHeight() <= this.height) {
			aField = a_field;
			add(aField);
		}
		if (b_field != null && b_field.getHeight() <= this.height) {
			bField = b_field;
			add(bField);
		}

	}

	protected void sublayout(int width, int height) {

		Field field;

		if (aField != null) {
			field = getField(0);
			layoutChild(field, width, height);
			setPositionChild(field, offset,
					(this.height - field.getHeight()) / 2);
		}

		if (bField != null) {
			int index = (aField == null) ? 0 : 1;
			field = getField(index);
			layoutChild(field, width, height);
			setPositionChild(field, this.width - (field.getWidth() + offset),
					(this.height - field.getHeight()) / 2);
		}

		setExtent(this.width, this.height);
	}

	protected void paintBackground(Graphics graphics) {
		// TODO Auto-generated method stub

		graphics.setGlobalAlpha(255);
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, this.width, this.height);
		graphics.setColor(Color.WHITE);
		graphics.setGlobalAlpha(35);
		graphics.fillRoundRect(0, 0, this.width, (this.height) / 2, 5, 5);
		graphics.setGlobalAlpha(255);
		graphics.drawText(labelname, (this.width - getFont().getAdvance(
				labelname)) / 2, (this.height - getFont().getHeight()) / 2,
				DrawStyle.ELLIPSIS, this.width - 45); // 45 space to draw
	}
	
	public void setLabel(String label){
		labelname = label;
	}
	
	protected void drawFocus(Graphics graphics, boolean mode){
		
	}

	protected void onFocus(int direction) {
		super.onFocus(direction);
		invalidate();
	}

	protected void onUnfocus() {
		super.onUnfocus();
		invalidate();
	}
}
