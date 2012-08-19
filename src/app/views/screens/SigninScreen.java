package app.views.screens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.CompositeFieldManager;
import rubyx.custom_fields.CompositePasswordBox;
import rubyx.custom_fields.CompositeTextBox;
import rubyx.custom_fields.SpaceField;
import app.AirCrewApp;
import app.controllers.user.UserController;


public class SigninScreen extends MainScreen{
	
	private UserController userController;
	private CompositeTextBox usernameField;
	private CompositePasswordBox passwordField;
	private CheckboxField rememberMe;
	private ButtonField login;
	private ButtonField register;	
	private int composite_field_width = Display.getWidth()-40;
	Bitmap login_background = Bitmap.getBitmapResource("images/login_background.png");
	Bitmap aircrew_logo = Bitmap.getBitmapResource("images/aircrew_logo.png");
	final MainScreen this_screen;
	
	public SigninScreen(UserController _userController){
		
		super(Manager.USE_ALL_HEIGHT);
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(login_background));

		this_screen = this;
		userController = _userController;
		usernameField = new CompositeTextBox("Email", "pratuat@gmail.com", true, composite_field_width);
		passwordField = new CompositePasswordBox("Password", "letmein", true, composite_field_width);
		usernameField.setColorScheme(0x606060, Color.WHITE);
		passwordField.setColorScheme(0x606060, Color.WHITE);
		
		login = new ButtonField("Login");
		register = new ButtonField("Register");
		
		CompositeFieldManager v_managerA = new CompositeFieldManager(Manager.FIELD_HCENTER);
				
		v_managerA.add(usernameField);
		v_managerA.add(passwordField);
		
		add(new SpaceField(120));
		
		add(v_managerA);		

		rememberMe = new CheckboxField("Remember me", false){
			protected void paint(Graphics graphics){
				graphics.setColor(Color.WHITE);
				graphics.setFont(graphics.getFont().derive(Font.ITALIC, 2, Ui.UNITS_pt));
				super.paint(graphics);
			}
		};
		
		LoginControls loginControls = new LoginControls(0);
		loginControls.add(rememberMe);
		loginControls.add(login);
		loginControls.add(register);
		add(loginControls);
		
		login.setChangeListener(loginListener);
		register.setChangeListener(registerListener);
	}
	
	
	public String getPassword() {
		return passwordField.getText();
	}


	public boolean isDirty() {
	    return false;
	}
	
	//---------------------------------------------------------------------
	
	private FieldChangeListener loginListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			userController.signinRequest.sign_in(usernameField.getText(), passwordField.getText());
		}
	};
	
	private FieldChangeListener registerListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			userController.pushSignupScreen();
		}
	};
}

class LoginControls extends Manager{
	
	int width = Display.getWidth();
	int height = 120;

	protected LoginControls(long style){
		super(style);
	}
	
	protected void sublayout(int _width, int _height) {
		setPositionChild(getField(0), 40, 10);
		setPositionChild(getField(1), 35, 50);
		setPositionChild(getField(2), 310, 50);
		
		layoutChild(getField(0), width, height/2);
		layoutChild(getField(1), width/2, height/2);
		layoutChild(getField(2), width/2, height/2);
		
		setExtent(width,height);
	}
	
}
