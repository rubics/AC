package app.models;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.DealController;

public abstract class ImageRequest implements HttpRequestListener{

	private static final String method = "GET";
	private HttpRequestDispatcher dispatcher;
	
	public ImageRequest(){
	}
	
	public void getImage(String url){
		dispatcher = new HttpRequestDispatcher(url, method, this, "");
		dispatcher.start();
	}

	public void httpsuccess(byte[] array, String str) {
		if(str.equalsIgnoreCase("image/jpeg") || str.equalsIgnoreCase("image/png") || str.equalsIgnoreCase("image/gif")){
			 EncodedImage encodedImage = EncodedImage.createEncodedImage(array, 0, array.length);
			 onSuccess(encodedImage.getBitmap());
		}
	}
	
	public void httpfailure(String errmsg) {}
	
	public abstract void onSuccess(Bitmap bitmap);
}
