package app.models;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.EncodedImage;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;
import app.controllers.user.DealController;

public class ImageRequest implements HttpRequestListener{

	private Deal deal;
	private DealController dealController;
	private static final String method = "GET";
	private HttpRequestDispatcher dispatcher;
	
	public ImageRequest(Deal _deal){
		deal = _deal;
	}
	
	public void getImage(){
		dispatcher = new HttpRequestDispatcher(AirCrew.image_medium + deal.getLogo(), method, this, "");
		dispatcher.start();
	}

	public void httpsuccess(byte[] array, String str) {
		if(str.equalsIgnoreCase("image/jpeg")){
			 EncodedImage encodedImage = EncodedImage.createEncodedImage(array, 0, array.length);
			 deal.setImage(encodedImage.getBitmap());
		}
	}//image/jpeg, text/html
	
	public void httpfailure(String errmsg) {}
}
