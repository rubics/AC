package app.views.screens.chat;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CustomEditField;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.UrlImage;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.controllers.UserController;
import app.models.AirCrew;
import app.models.ChatHistory;
import app.models.ChatHistoryRequest;
import app.models.ChatRequest;
import app.models.Identity;
import app.models.Images;
import app.views.fields.chat.ChatMessageField;

public class ChatScreen  extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrm;
	private ChatBox chatBox;
	private boolean switcher = true;
	private ChatScreen this_screen;
	private Identity chatter;
	private UrlImage userImage = null;
	private UrlImage chatterImage = null;
	
//	private WebImageField userImage;
//	private WebImageField connectionImage;
	
	private ChatHistoryRequest chatHistoryRequest;
	private Thread updateChat;
	private boolean poll_flag = true;
	private String recent_message_id = "0";
	
	public ChatScreen(Identity _chatter){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		this_screen = this;
		chatter = _chatter;
		
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("Chat", 40, backButton, homeButton));
		vrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		add(vrm);
		
		chatBox = new ChatBox(listener);
		setStatus(chatBox);
		
		chatHistoryRequest = new ChatHistoryRequest() {
			public void afterSuccess(Vector chatHistory, String image_a, String image_b) {
				recent_message_id = ((ChatHistory)(chatHistory.lastElement())).id;
				if(userImage == null){
					System.out.println("--> A " + AirCrew.user_images + image_a);
					userImage = new UrlImage(AirCrew.user_images + image_a, ChatMessageField.IMAGE_DIMENSION, ChatMessageField.IMAGE_DIMENSION);
				}
				if(chatterImage == null)
					chatterImage = new UrlImage(AirCrew.user_images + image_b, ChatMessageField.IMAGE_DIMENSION, ChatMessageField.IMAGE_DIMENSION);
				appendChatHistory(chatHistory);
			}
		};
	}
	
	private void appendChatHistory(final Vector chatHistory){
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			public void run() {
				for(Enumeration e = chatHistory.elements(); e.hasMoreElements();){
					ChatHistory chatMessage = (ChatHistory)e.nextElement();
					if(chatMessage.user_id.equals(UserController.getInstance().getUser().getUserId()))
						vrm.add(new ChatMessageField(userImage.getBitmap(), chatMessage.text,true));
					else
						vrm.add(new ChatMessageField(chatterImage.getBitmap(), chatMessage.text,false));
				}
				vrm.invalidate();
				vrm.getField(vrm.getFieldCount()-1).setFocus();
			}
		});
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ChatRequest chatRequest = new ChatRequest() {
				public void afterSuccess() {}
			};
			chatRequest.setChat(chatter.getUser_id(), chatBox.getText());
			chatBox.setText("");
		}
	};
	
	public boolean isDirty() {
	    return false;
	}
	
	public void onUiEngineAttached(boolean flag){
		if(flag){
			updateChat = new Thread(){
				public void run(){
					try{
						while (poll_flag){
							chatHistoryRequest.getChatMessage(chatter.getUser_id(), recent_message_id);
							Thread.sleep(5000);
							System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDD");
						}
					} catch(Exception e){
						System.out.println(e.getClass().getName());
						System.out.println("Exception @ ChatScreen.updateChat.run()");
					}
				}
			};
			updateChat.start();
		} else {
			poll_flag = false;
		}
	}
}

class ChatBox extends Manager{
	CustomEditField chatField;
	Field sendButton;
		
	public ChatBox(FieldChangeListener listener){
		super(0);
		chatField = new CustomEditField("", 350, 44);
		sendButton = new TabbedButton("Send", 6, 120, 40);
		sendButton.setChangeListener(listener);
		add(chatField);
		add(sendButton);
	}
	
	public void sublayout(int a, int b){
		layoutChild(getField(0), 345, 44);
		layoutChild(getField(1), 120, 40);
		setPositionChild(getField(0), 5, 3);
		setPositionChild(getField(1), 355, 5);
		setExtent(Display.getWidth(), 50);				
	}
	
	public void paintBackground(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());	
		super.paint(g);
	}
	
	public String getText(){
		return chatField.getText();
	}
	
	public void setText(String text){
		chatField.setText(text);
	}
}
