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
			
	        String connectionParameters = "";
	        
	        if (WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED) { 
	            // Connected to a WiFi access point 
	            connectionParameters = ";interface=wifi";
	            //System.out.println("#### https wifi ####");
	        } else { 
	           int coverageStatus = CoverageInfo.getCoverageStatus(); 
	           ServiceRecord record = getWAP2ServiceRecord(); 
	           if (record != null 
	                  && (coverageStatus & CoverageInfo.COVERAGE_DIRECT) ==  
	                  CoverageInfo.COVERAGE_DIRECT) { 
	              // Have network coverage and a WAP 2.0 service book record 
	              connectionParameters = ";deviceside=true;ConnectionUID="+ record.getUid();
	              //System.out.println("#### https wap2 ####");
	        } else if ((coverageStatus & CoverageInfo.COVERAGE_MDS) ==  
	              CoverageInfo.COVERAGE_MDS) { 
	           // Have an MDS service book and network coverage 
	           connectionParameters = ";deviceside=false";
	           //System.out.println("#### https MDS ####");
	        } else if ((coverageStatus & CoverageInfo.COVERAGE_DIRECT) ==  
	              CoverageInfo.COVERAGE_DIRECT) { 
	           // Have network coverage but no WAP 2.0 service book record 
	           connectionParameters = ";deviceside=true";
	           //System.out.println("#### https direct ####");
	        }
	     }

		HttpConnection connection = (HttpConnection)Connector.open(url + connectionParameters);
		connection.setRequestMethod(method);
		
		connection.setRequestProperty(HttpProtocolConstants.HEADER_CONTENT_LENGTH, String.valueOf(post.length));
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent", "Profile/MIDP_2.0 Configuration/CLDC-1.0");
		
		if(method.equalsIgnoreCase("PUT")){           
                //connection.setRequestProperty("X-HTTP-Method-Override", "PUT");
			connection.setRequestProperty("RequestType", "PUT");
		}
		

		
		OutputStream postStream = connection.openOutputStream();

		
		postStream.write(post,0,post.length);
		postStream.close();
	
		
		String contenttype = connection.getHeaderField("Content-type");			// this method sends the get request (i guess)
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		InputStream responsedata = connection.openInputStream();
		
		byte[] bytearray= new byte[2000];
		
		int bytenum = responsedata.read(bytearray);

		while(bytenum>0){
			baos.write(bytearray, 0, bytenum);
			bytenum = responsedata.read(bytearray);
			
		}
		
		String json_response = new String(baos.toByteArray());
		System.out.println("************  HTTP RESPONSE " + json_response);
		System.out.println("******** CHECKPOINT : " + ((requestListener == null) ? " NULL " : " NOT NULL "));
		responsedata.close();
		connection.close();
		
		requestListener.httpsuccess(baos.toByteArray(), contenttype);	
	       
		}
		
		catch(Exception ie){
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

