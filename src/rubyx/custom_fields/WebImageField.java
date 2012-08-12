package rubyx.custom_fields;

import app.models.Images;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.UiApplication;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;

public class WebImageField extends Field implements HttpRequestListener{
	
	static final Bitmap default_img = Images.aircrew_small;
	Bitmap image;
	final String url;
	final int width;
	final int height;
	private static final String method = "GET";
	private HttpRequestDispatcher dispatcher;
	
	public WebImageField(Object _src, int _width, int _height){
		super();
		
		width = _width;
		height = _height;
		
		if(Bitmap.class.isInstance(_src)){
			image = new Bitmap(width,height);
			((Bitmap)_src).scaleInto(image, Bitmap.FILTER_BILINEAR);
			url = "";
		} else if (String.class.isInstance(_src)){
			url = (String)_src;
			dispatcher = new HttpRequestDispatcher(url, method, this, "");
			dispatcher.start();
			image = new Bitmap(width,height);
			default_img.scaleInto(image, Bitmap.FILTER_BILINEAR);
		} else {
			image = default_img;
			url = "";
		}
	}

	protected void layout(int _width, int _height) {
		setExtent(width, height);
	}

	protected void paint(Graphics graphics) {
		graphics.drawBitmap(0, 0, width, height, image, 0, 0);
	}
	
	public int get_width(){
		return image.getWidth();
	}
	
	public int get_height(){
		return image.getHeight();
	}
	
	public Bitmap returnBitmap(){
		return image;
	}
	private void updateImage(Bitmap _bitmap){
		image = new Bitmap(width,height);
		System.out.println("Width: " + image.getWidth() + "\tHeight: " + image.getHeight());
		_bitmap.scaleInto(image, Bitmap.FILTER_BILINEAR);
		UiApplication.getUiApplication().getActiveScreen().invalidate();
	}
	
	public void httpsuccess(byte[] array, String str) {
		System.out.println(">> Image Response Received: " + str);
		
		if(str.equalsIgnoreCase("image/jpeg") || str.equalsIgnoreCase("image/png") || str.equalsIgnoreCase("image/gif")){
			System.out.println(">> mime type: image");
			 EncodedImage encodedImage = EncodedImage.createEncodedImage(array, 0, array.length);
			 updateImage(encodedImage.getBitmap());
		}
	}//image/jpeg, text/html

	public void httpfailure(String errmsg) {}
}