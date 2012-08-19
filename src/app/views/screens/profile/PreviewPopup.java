package app.views.screens.profile;

import rubyx.custom_fields.PopupKeyListener;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import rubyx.tabbedUI.TabbedButtonManager;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class PreviewPopup extends PopupScreen{
	
	Screen this_screen;
	
	public PreviewPopup(Bitmap image){

		super(new HorizontalFieldManager(Field.FIELD_HCENTER));
		Manager manager = new VerticalFieldManager();
		this_screen = this;
		addKeyListener(new PopupKeyListener(this));
		Field imageField = new ResizedBitmapField(image);
		imageField.setChangeListener(new FieldChangeListener() {
			
			public void fieldChanged(Field field, int context) {				
				UiApplication.getUiApplication().popScreen(this_screen);			
			}
		});
		manager.add(imageField);
		manager.add(new SpaceField(10));
		Field a = new TabbedButton("Set as Default", 6);
		Field b = new TabbedButton("Delete", 6);
		Manager m = new TabbedButtonManager(360, 40);
		m.add(a);
		m.add(b);
		manager.add(m);
		add(manager);
		
//		add(m);
	}
	
	class ResizedBitmapField extends Field{
		
		Bitmap image;
		Bitmap _image;
		
		int availableWidth;
		int availableHeight;
		
		float resizedWidth;
		float resizedHeight;
		
		public ResizedBitmapField(Bitmap _image){//, int _width, int _height){
			this._image = _image;		
		}
		
		protected void layout(int _width, int _height){
		
			availableWidth = _width;
			availableHeight = 220;
			
			float aspect_ratio = (float)(_image.getWidth())/(float)(_image.getHeight());
			
			if(aspect_ratio > 1){
				
				resizedWidth = _width;
				resizedHeight = _width/aspect_ratio;
				
				if(resizedHeight > _height){
					resizedHeight = _height;
					resizedWidth = aspect_ratio * resizedHeight;
				}			
			} else{
				
				resizedHeight = _height;
				resizedWidth = _height * aspect_ratio;
				
				if(resizedWidth > _width){
					resizedWidth = _width;
					resizedHeight = resizedWidth /  aspect_ratio;
				}
			}
			
			image = new Bitmap((int)resizedWidth, (int)resizedHeight);
			
			_image.scaleInto(image, Bitmap.FILTER_BILINEAR);
			
			setExtent(availableWidth, availableHeight);
		}
		
		protected void paint(Graphics graphics){
			
			graphics.setColor(0x181818);
			
			graphics.fillRect(0, 0, availableWidth, availableHeight);
					
			int x = (int)(availableWidth - resizedWidth)/2;
			int y = (int)(availableHeight - resizedHeight)/2;
			
			graphics.drawBitmap(x, y, (int)resizedWidth, (int)resizedHeight, image, 0, 0);		
		}
		
		protected void paintBackground(Graphics graphics){

		}
		
		public boolean isFocusable(){
			return true;
		}
		
		protected boolean navigationClick(int status, int time){
			fieldChangeNotify(0);
			return true;
		}
		
		protected boolean keyChar(char character, int status, int time){
			if(character == Keypad.KEY_ENTER){
				fieldChangeNotify(0);
				return true;
			}else if(character == Keypad.KEY_ESCAPE){
				UiApplication.getUiApplication().popScreen(this_screen);
				return true;
			}else
				return super.keyChar(character, status, time);
		}
		
		protected boolean trackwheelClick(int arg0, int arg1) {
			return super.trackwheelClick(arg0, arg1);	
		}
		
		protected void fieldChangeNotify(int context) {
			super.fieldChangeNotify(context);
		}
	}
}