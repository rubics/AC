package rubyx.screens;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.ui.container.MainScreen;
import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;

public class WebViewScreen extends MainScreen{
	
	BrowserField browserField;
	
	InputStream is;
	ByteArrayOutputStream baos;
	TabbedButton backButton;
	
	public WebViewScreen (String html_file, String screen_title){
		
		super();
		setTitle(screen_title);
		
		browserField = new BrowserField();
		add(browserField);
		
		try {
			
			Class classs = Class.forName("com.sprout.blackberry.BizcardArmy.webview.WebViewScreen");
			
			is = classs.getResourceAsStream(html_file);
			baos = new ByteArrayOutputStream();
			
			byte[] byteArray = new byte[1000];
			
			int length = is.read(byteArray);
			
			while(length > 0){
				baos.write(byteArray, 0, byteArray.length);
				length = is.read(byteArray);
			}
			
			browserField.displayContent(new String(baos.toByteArray()), "cod:///BizcardArmy/res");
			
		} catch (Exception e) {
			System.out.println("Exception @ WebViewScreen");
			System.out.println(e.getClass().getName());
			e.printStackTrace();
		}finally{
			try {
				is.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public WebViewScreen(String url,String screen_title, String baseUrl){
		
		super();
//		setTitle(screen_title);
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		setTitle(new ScreenBannar(screen_title, 40, backButton, null));
		
		browserField = new BrowserField();
		add(browserField);
		
		browserField.requestContent(url);
	}
}
