package app.views.screens;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.custom_fields.SpaceField;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.ClearChatHistoryRequest;
import app.models.ClearVisitorHistoryRequest;
import app.models.Images;
import app.models.ResetPasswordRequest;
import app.models.SignoutRequest;

public class SettingsScreen extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;
	private SettingsScreen currentScreen;
	
	private TabbedButton logout;
	private TabbedButton forgotPassword;
	private TabbedButton clearHistory;
	private TabbedButton visitorHistory;
	
	public SettingsScreen(){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		currentScreen = this;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
//		homeButton = new TabbedButton("Home", 6, 100, 36);
//		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("BookMarks", 40, backButton, null));
		
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		
		vrManager.add(new SpaceField(15));
		logout = new TabbedButton("Logout", 7, 480, 50);
		logout.setChangeListener(logoutListener);
		vrManager.add(logout);
		vrManager.add(new SpaceField(10));
		forgotPassword = new TabbedButton("Forgot Password", 7, 480, 50);
		forgotPassword.setChangeListener(forgotPasswordListener);
		vrManager.add(forgotPassword);
		vrManager.add(new SpaceField(10));
		clearHistory = new TabbedButton("Clear Chat History", 7, 480, 50);
		clearHistory.setChangeListener(clearChatHistoryListener);
		vrManager.add(clearHistory);
		vrManager.add(new SpaceField(10));
		visitorHistory = new TabbedButton("Clear Visitor History", 7, 480, 50);
		visitorHistory.setChangeListener(clearVisitorHistoryListener);
		vrManager.add(visitorHistory);
		
		add(vrManager);
	}
	
	FieldChangeListener logoutListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			SignoutRequest signoutRequest = new SignoutRequest() {
				public void afterSuccess(final String message){
					UiApplication.getUiApplication().invokeAndWait(new Runnable() {
						public void run() {
							UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
							UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
							AirCrewApp.app.getUserController().pushSignInScreen(false);
							Dialog.inform(message);
						}
					});
				}
			};
			signoutRequest.sign_out();
		}
	};
	
	FieldChangeListener forgotPasswordListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().pushScreen(new EmailPromptPopup());
		}
	};
	
	FieldChangeListener clearChatHistoryListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ClearChatHistoryRequest clearChatRequest = new ClearChatHistoryRequest() {
				public void afterSuccess(final String message) {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Dialog.inform(message);
						}
					});
				}
			};
			clearChatRequest.clearChatHistory();
		}
	};
	
	FieldChangeListener clearVisitorHistoryListener = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ClearVisitorHistoryRequest clearVisitorRequest = new ClearVisitorHistoryRequest() {
				public void afterSuccess(final String message) {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Dialog.inform(message);
						}
					});
				}
			};
			clearVisitorRequest.clearVisitorHistory();
		}
	};
	
	public boolean isDirty(){
		return false;
	}
}

class EmailPromptPopup extends PopupScreen{
	
	private Field labelField;
	private EmailPromptPopup this_screen;
	private TextField emailField;
	
	public EmailPromptPopup(){
		super(new VerticalFieldManager());
		
		this_screen = this;
		labelField = new LabelField("Enter your Email Id to receive new password.", Field.USE_ALL_WIDTH); 
		add(labelField);
		emailField = new TextField(Field.USE_ALL_WIDTH);
		add(emailField);
		HorizontalFieldManager hrManager = new HorizontalFieldManager();
		Field cancel = new ButtonField("Cancel");
		hrManager.add(cancel);
		Field reset = new ButtonField("Reset");
		reset.setChangeListener(resetButtonListener);
		hrManager.add(reset);
		add(hrManager);
	}
	
	FieldChangeListener resetButtonListener = new FieldChangeListener() {		
		public void fieldChanged(Field field, int context) {		
			ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest() {			
				public void afterSuccess(final String message) {
					UiApplication.getUiApplication().invokeLater(new Runnable() {
						public void run() {
							Dialog.inform(message);
						}
					});
				}
			};
			resetPasswordRequest.resetPassword(emailField.getText());
			UiApplication.getUiApplication().popScreen(this_screen);
		}
	};
}
