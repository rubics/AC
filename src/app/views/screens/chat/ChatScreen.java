package app.views.screens.chat;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CustomEditField;
import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrew;
import app.models.Images;
import app.views.fields.chat.ChatMessageField;

public class ChatScreen  extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	
	VerticalFieldManager vrm;
	
	ChatBox chatBox;
	private boolean switcher = true;
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			vrm.add(new ChatMessageField(Images.avatar_a[(switcher) ? 0 : 1], chatBox.getText(),switcher));
			vrm.invalidate();
			chatBox.setText("");
			switcher = !switcher;
		}
	};
	
	public ChatScreen(){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrew.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		
		setTitle(new ScreenBannar("Chat", 40, backButton, homeButton));
		
		vrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		vrm.add(new ChatMessageField(Images.avatar_a[0], "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut suscipit pulvinar justo vel ullamcorper.",true));
		vrm.add(new ChatMessageField(Images.avatar_a[1], Images.profile_pics[2],false));
		vrm.add(new ChatMessageField(Images.avatar_a[1], "Aenean nulla mi, scelerisque et rutrum vel, molestie eu elit. Nulla a ipsum ligula",false));
		vrm.add(new ChatMessageField(Images.avatar_a[0], Images.profile_pics[3],true));
		add(vrm);
		
		chatBox = new ChatBox(listener);
		setStatus(chatBox);
	}
	
	public boolean isDirty() {
	    return false;
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
