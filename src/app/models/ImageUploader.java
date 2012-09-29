package app.models;

import helpers.org.json.me.JSONArray;
import helpers.org.json.me.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import app.AirCrewApp;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.RadioInfo;
import net.rim.device.api.system.WLANInfo;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import rubyx.httpconnection.HttpRequestDispatcher;
import rubyx.httpconnection.HttpRequestListener;

public class ImageUploader implements HttpRequestListener {
	
	private HttpRequestListener requestListener = this;
	private UploadImageRequest dispatcher;
	
	public ImageUploader(){}
	
	public void insertToGallery(String file_path, byte[] imageBytes){
		String file_name = file_path.substring(file_path.lastIndexOf('/')+1, file_path.length());
		System.out.println("File Name: " + file_name);
		System.out.println("Content: " + imageBytes.length);
		System.out.println("User ID: " + AirCrew.insert_to_gallery + AirCrewApp.app.getUserController().getUser().getUserId());
		dispatcher = new UploadImageRequest(AirCrew.insert_to_gallery + AirCrewApp.app.getUserController().getUser().getUserId(), AirCrewApp.app.getUserController().getUser().getUserId(), file_name, imageBytes, requestListener);
		dispatcher.start();
	}
	
	public void httpsuccess(byte[] array, String str) {
		final String json_response = new String(array);
		try{
			JSONObject json = new JSONObject(json_response);

			if(json.has("business")) {

				JSONArray response = json.getJSONArray("business");
				
				for(int i=0; i<response.length(); i++){
					JSONObject deal = (JSONObject)response.get(i);
					String id = deal.getString("b_id");
				}
			} else if (json.has("error") & !json.isNull("error")){
				JSONObject response = json.getJSONObject("error");
				final String code = response.getString("code");
				final String message = response.getString("message");
				
				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					
					public void run() {
						Dialog.alert(message);
					}		
				});
			}
			
		}catch(Exception e){
			System.out.println(">> Exception @ " + e.getClass().getName());
			e.printStackTrace();
		}
	}
	
	public void httpfailure(String errmsg) {}
	
}


class UploadImageRequest extends Thread{
	
	private String url = "";
	private static final String method = "POST";
	private static final String boundary = "flsdkj";
	private String lineend = "\r\n";
	private String twoHyphens = "--";
	private HttpRequestListener requestListener;
	private byte[] imageBytes;
	private String file_name;
	private String user_id;
	
	public UploadImageRequest ( String url,String user_id, String file_name, byte[] imageBytes, HttpRequestListener _requestListener){
		
		this.url = url;
		this.user_id = user_id;
		this.file_name = file_name;
		this.imageBytes = imageBytes;
		this.requestListener = _requestListener;
	}
	
	public void run(){

		try {
			
	    String connectionParameters = updateConnectionSuffix();
		HttpConnection connection = (HttpConnection)Connector.open(url + connectionParameters);
		connection.setRequestMethod(method);
		
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		connection.setRequestProperty("User-Agent", "Profile/MIDP_2.0 Configuration/CLDC-1.0");		
		
		OutputStream pos = connection.openOutputStream();
		
		pos.write(twoHyphens.getBytes());
		pos.write(boundary.getBytes());
		pos.write(lineend.getBytes());
		
		pos.write("Content-Disposition: form-data; name=\"image_name\"".getBytes());
		pos.write(lineend.getBytes());
		pos.write(lineend.getBytes());
		pos.write(file_name.getBytes());
		pos.write(lineend.getBytes());
		
		pos.write(twoHyphens.getBytes());
		pos.write(boundary.getBytes());
		pos.write(lineend.getBytes());
		
		pos.write("Content-Disposition: form-data; name=\"user_id\"".getBytes());
		pos.write(lineend.getBytes());
		pos.write(lineend.getBytes());
		pos.write(user_id.getBytes());
		pos.write(lineend.getBytes());
		
		pos.write(twoHyphens.getBytes());
		pos.write(boundary.getBytes());
		pos.write(lineend.getBytes());
		
		pos.write("Content-Disposition: form-data; name=\"default\"".getBytes());
		pos.write(lineend.getBytes());
		pos.write(lineend.getBytes());
		pos.write("0".getBytes());
		pos.write(lineend.getBytes());
		
		pos.write(twoHyphens.getBytes());
		pos.write(boundary.getBytes());
		pos.write(lineend.getBytes());
		
		pos.write("Content-Disposition: form-data; name=\"image\"; filename=\"".getBytes());
		pos.write(file_name.getBytes());
		pos.write("\"".getBytes());
		pos.write(lineend.getBytes());
		
		pos.write("Content-Type: image/jpeg".getBytes());
		pos.write(lineend.getBytes());
		pos.write(lineend.getBytes());
		
		pos.write(imageBytes, 0, imageBytes.length);
		
		pos.write(lineend.getBytes());
		
		pos.write(twoHyphens.getBytes());
		pos.write(boundary.getBytes());
		pos.write(twoHyphens.getBytes());
		pos.write(lineend.getBytes());
		
		pos.flush();
		pos.close();
		
		String contenttype = connection.getHeaderField("Content-type");		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		InputStream responsedata = connection.openInputStream();
		byte[] bytearray= new byte[2000];
		
		int bytenum = responsedata.read(bytearray);
		int count =0;// just for checking 
		while(bytenum>0){
			baos.write(bytearray, 0, bytenum);
			count++;
			bytenum = responsedata.read(bytearray);
			
		}
		
		String json_response = new String(baos.toByteArray());
		System.out.println("************  HTTP RESPONSE " + json_response);
		
		System.out.println("******** CHECKPOINT : " + ((requestListener == null) ? " NULL " : " NOT NULL "));
		requestListener.httpsuccess(baos.toByteArray(), contenttype);
		responsedata.close();
		connection.close();
		}
		
		catch(Exception exception){	
			System.out.println(" EXCEPTION @ UploadImage.run();");
			System.out.println(exception.getClass().getName());
			exception.printStackTrace();
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
    
    public String updateConnectionSuffix()
    {
    String connSuffix = "";
    if (DeviceInfo.isSimulator()) {
        connSuffix = ";deviceside=true";
    } else
    if ( (WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED) &&
          RadioInfo.areWAFsSupported(RadioInfo.WAF_WLAN)) {
        connSuffix=";interface=wifi";
    } else {
        String uid = null;
        ServiceBook sb = ServiceBook.getSB();
        ServiceRecord[] records = sb.findRecordsByCid("WPTCP");
        for (int i = 0; i < records.length; i++) {
            if (records[i].isValid() && !records[i].isDisabled()) {
                if (records[i].getUid() != null &&
                    records[i].getUid().length() != 0) {
                    if ((records[i].getCid().toLowerCase().indexOf("wptcp") != -1) &&
                        (records[i].getUid().toLowerCase().indexOf("wifi") == -1) &&
                        (records[i].getUid().toLowerCase().indexOf("mms") == -1)   ) {
                        uid = records[i].getUid();
                        break;
                    }
                }
            }
        }
        if (uid != null) {
            // WAP2 Connection
             connSuffix = ";ConnectionUID="+uid;
        } else {
             connSuffix = ";deviceside=true";
        }
    }
    return connSuffix;
    };
}