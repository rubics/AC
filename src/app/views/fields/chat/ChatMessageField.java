package app.views.fields.chat;

import java.util.Calendar;
import java.util.Date;

import app.models.Images;

import net.rim.device.api.i18n.DateFormat;
import net.rim.device.api.i18n.FieldPosition;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.NullField;
import rubyx.custom_fields.CustomTextArea;

public class ChatMessageField extends Manager{
	
	public static final int H_OFFSET_A = 30;
	public static final int H_OFFSET_B = 60;
	public static final int V_OFFSET = 16;
	public static final int B_OFFSET = 30;
	public static final int IMAGE_DIMENSION = 50;
	public static final int R_VALUE = 20;
	public static final int R_OFFSET = 5;
	
	public static final int X_OFFSET = H_OFFSET_A + IMAGE_DIMENSION + B_OFFSET;
	public static final int Y_OFFSET = V_OFFSET + 25;
	
	public int x_offset_field = 0;
	
//	public static final int X_OFFSET_R = H_OFFSET_B + IMAGE_DIMENSION + B_OFFSET;
	
	public static final String TYPE_TEXT = "type_text";
	public static final String TYPE_IMAGE = "type_image";
	
	private static final Font font = Font.getDefault().derive(Font.PLAIN, 5, Ui.UNITS_pt);
	
	private Date date;
	
	Bitmap image;
	Object message;
	String message_type;
	
	private int width;
	private int height;
	
	private Field field;
	private static final int field_width = Display.getWidth() - (X_OFFSET + H_OFFSET_B);
	private boolean draw_direction;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MMM-yy, h-mm");
	
	
	public ChatMessageField(Bitmap _image, Object _message, boolean _draw_direction){
		super(0);
		image = new Bitmap(IMAGE_DIMENSION, IMAGE_DIMENSION);
		_image.scaleInto(image, Bitmap.FILTER_BILINEAR);
		
		message = _message;
		draw_direction = _draw_direction;
		date = new Date(System.currentTimeMillis());
		
		if(String.class.isInstance(message)){
			message_type = "text";
			field = new CustomTextArea((String)message, field_width, font);
			height = Y_OFFSET + field.getPreferredHeight() + V_OFFSET;
			x_offset_field = 0;
		}else if (Bitmap.class.isInstance(_message)){			
			message_type = "image";
			Bitmap temp = Images.resize((Bitmap)message, field_width, 120);
			field = new BitmapField(temp);
			height = Y_OFFSET + 120 + V_OFFSET;
			x_offset_field = (field_width - temp.getWidth())/2;
		}else{
			message_type = "undefined";
			field = new NullField();
			height = Y_OFFSET + V_OFFSET;
		}		
		width = Display.getWidth();
		add(field);
		add(new NullField());
	}

	protected void sublayout(int _width, int _height) {
		layoutChild(getField(0), field_width, _height);
		if(draw_direction)
			setPositionChild(getField(0), X_OFFSET + x_offset_field, Y_OFFSET);
		else
			setPositionChild(getField(0), H_OFFSET_B + x_offset_field,Y_OFFSET);
		setExtent(width, height);
	}

	protected void paint(Graphics g) {
		if(draw_direction){
			g.setFont(font);
			g.drawBitmap(H_OFFSET_A, V_OFFSET, IMAGE_DIMENSION, IMAGE_DIMENSION, image, 0, 0);
			g.setColor(Color.WHITE);
			g.drawText(dateFormat.format(date), X_OFFSET + x_offset_field - 2*R_OFFSET, 10);
			g.fillRoundRect(X_OFFSET + x_offset_field - 2*R_OFFSET, Y_OFFSET - R_OFFSET, field.getWidth() + 4*R_OFFSET, field.getHeight() + 2*R_OFFSET, R_VALUE, R_VALUE);
		}else{
			g.setFont(font);
			g.drawBitmap(width - (H_OFFSET_A + IMAGE_DIMENSION), V_OFFSET, IMAGE_DIMENSION, IMAGE_DIMENSION, image, 0, 0);
			g.setColor(Color.WHITE);
			g.drawText(dateFormat.format(date), H_OFFSET_B + x_offset_field - 2*R_OFFSET, 10);
			g.fillRoundRect(H_OFFSET_B + x_offset_field - 2*R_OFFSET, Y_OFFSET - R_OFFSET, field.getWidth() + 4*R_OFFSET, field.getHeight() + 2*R_OFFSET, R_VALUE, R_VALUE);
		}
//		g.setColor(Color.GRAY);
//		g.fillRect(12, getHeight()-4, getWidth() -24, 2);
		g.setColor(0x333333);		
		super.paint(g);
	}
	
	protected void drawFocus(Graphics graphics, boolean mode){
		
	}

}
