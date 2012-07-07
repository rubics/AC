package rubyx.httpconnection;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.io.http.HttpProtocolConstants;
import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.CoverageInfo;
import net.rim.device.api.system.WLANInfo;
import net.rim.device.api.ui.component.CheckboxField;

public class HttpRequestDispatcher extends Thread{
	
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	
	private String url;
	private String method;
	private HttpRequestListener requestListener;
	
	private byte[] post;
	
	public HttpRequestDispatcher (String url, String method, HttpRequestListener _requestListener,String postString){
		this.url = url;
		this.method = method;
		this.requestListener = _requestListener;
		this.post = postString.getBytes();

	}
	
	public void run(){
		
		//System.out.println("#####  " + "inside HTTPREQUESTDISPATCHER.run();"  + "  ####");
		
		
		try {
			
	        String connectionParameters = ";deviceside=true";
	        String hit_url = (method.equalsIgnoreCase(METHOD_POST)) ? url : (url + "?" + new String(post));
	        HttpConnection connection = (HttpConnection)Connector.open(hit_url + connectionParameters);
	        connection.setRequestMethod(method);
		
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setRequestProperty("User-Agent", "Profile/MIDP_2.0 Configuration/CLDC-1.0");
	        
	        if (method.equalsIgnoreCase(METHOD_POST)){
	        	connection.setRequestProperty(HttpProtocolConstants.HEADER_CONTENT_LENGTH, String.valueOf(post.length));
				OutputStream postStream = connection.openOutputStream();
				postStream.write(post,0,post.length);
				postStream.close();
	        }

	        String contenttype = connection.getHeaderField("Content-type");			// this method sends the get request (i guess)
		
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
	        InputStream responsedata = connection.openInputStream();
		
	        byte[] bytearray= new byte[2000];
		
	        int bytenum = responsedata.read(bytearray);

	        while(bytenum>0){
	        	baos.write(bytearray, 0, bytenum);
	        	bytenum = responsedata.read(bytearray);	
	        }
		
	        responsedata.close();
	        connection.close();
		
	        requestListener.httpsuccess(baos.toByteArray(), contenttype);	
		} catch(Exception ie){
			System.out.println(">> Exception @ " + ie.getClass().getName());
			ie.printStackTrace();
		}
	}
	
    private ServiceRecord getWAP2ServiceRecord() {
        ServiceBook sb = ServiceBook.getSB();
        ServiceRecord[] records = sb.getRecords();

        for (int i = 0; i < records.length; i++) {
            String cid = records[i].getCid().toLowerCase();
            String uid = records[i].getUid().toLowerCase();
            if (cid.indexOf("wptcp") != -1 && uid.indexOf("wifi") == -1
                    && uid.indexOf("mms") == -1) {
                return records[i];
            }
        }

        return null;
    }

}

