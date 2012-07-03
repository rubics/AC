package rubyx.httpconnection;

import java.io.InputStream;



public interface HttpRequestListener {
	
	public void httpsuccess(byte[] array, String str);
	public void httpfailure(String errmsg);

}
