package app.views.screens;




import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.SpaceField;
import rubyx.layout_managers.TableLayoutManager;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.models.Images;
import app.views.fields.DashboardItem;
import app.views.fields.ScreenTitle;
import app.views.managers.chat.ChatScreenManager;
import app.views.managers.deals.DealsScreenManager;
import app.views.managers.profile.ProfileInfoScreenManager;
import app.views.screens.chat.ChatScreen;
import app.views.screens.favorites.FavoritesScreen;

public class DashboardScreen extends MainScreen{
	
	private static DashboardScreen dashboardScreen;
	
	AirCrewApp airCrew = (AirCrewApp)(UiApplication.getUiApplication());
	
	public static Bitmap[] icons = {Bitmap.getBitmapResource("images/dashboard/deals.png"),
				Bitmap.getBitmapResource("images/dashboard/chat.png"),
				Bitmap.getBitmapResource("images/dashboard/bookmarks.png"),
				Bitmap.getBitmapResource("images/dashboard/favourites.png"),
				Bitmap.getBitmapResource("images/dashboard/myprofile.png"),
				Bitmap.getBitmapResource("images/dashboard/visitors.png"),
				Bitmap.getBitmapResource("images/dashboard/toolbox.png"),
				Bitmap.getBitmapResource("images/dashboard/hotdeals.png"),
				Bitmap.getBitmapResource("images/dashboard/settings.png")};
	
	Bitmap[] titles = {Bitmap.getBitmapResource("images/dashboard/deals_title.png"),
			Bitmap.getBitmapResource("images/dashboard/chat_title.png"),
			Bitmap.getBitmapResource("images/dashboard/bookmarks_title.png"),
			Bitmap.getBitmapResource("images/dashboard/favourites_title.png"),
			Bitmap.getBitmapResource("images/dashboard/myprofile_title.png"),
			Bitmap.getBitmapResource("images/dashboard/visitors_title.png"),
			Bitmap.getBitmapResource("images/dashboard/toolbox_title.png"),
			Bitmap.getBitmapResource("images/dashboard/hotdeals_title.png"),
			Bitmap.getBitmapResource("images/dashboard/settings_title.png")};
	
	
	final int[] column_styles = {TableLayoutManager.FIXED_WIDTH,TableLayoutManager.FIXED_WIDTH,TableLayoutManager.FIXED_WIDTH}; 
	final int[] column_widths = {Display.getWidth()/3,Display.getWidth()/3,Display.getWidth()/3};
	final int horizontal_padding = 1;
	
	FieldChangeListener dashboardItemListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			int index = ((DashboardItem)field).index;
			
			switch (index){
			case 1:
				DealsScreenManager dealsScreenManger = new DealsScreenManager();
				dealsScreenManger.pushScreen();
				break;
			case 2:
				ChatScreenManager chatScreenManager = new ChatScreenManager();
				chatScreenManager.pushScreen();
				break;
			case 3:
				airCrew.pushScreen(new BookmarksScreen());
				break;
			case 4:
				airCrew.pushScreen(new FavoritesScreen());
				break;
			case 5:
				ProfileInfoScreenManager profileInfo = new ProfileInfoScreenManager();
				profileInfo.pushScreen();
				break;			
			case 7:
				airCrew.pushScreen(new ToolboxScreen());
				break;
			default:
				break;
			}
		}
	};
	
	public static DashboardScreen createDashboardScreenInstance(){
		if(dashboardScreen == null)
			return new DashboardScreen();
		else
			return dashboardScreen;
	}
	
	private DashboardScreen(){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		setTitle(new ScreenTitle("Dashboard"));
		VerticalFieldManager mvrm = new VerticalFieldManager(Manager.VERTICAL_SCROLL|Manager.VERTICAL_SCROLLBAR);
		TableLayoutManager layoutManager = new TableLayoutManager(column_styles, column_widths, horizontal_padding, 0) ;
		
		for(int i=0; i<9; i++){
			System.out.println(">> " + i);
			Field dashboardItem = new DashboardItem(icons[i], titles[i], i+1);
			dashboardItem.setChangeListener(dashboardItemListener);
			layoutManager.add(dashboardItem);
		}
		
		mvrm.add(layoutManager);
		mvrm.add(new SpaceField(5));
		Field logoutButton = new TabbedButton("Log Out", 7, Display.getWidth()-10, 46);
		logoutButton.setChangeListener(new FieldChangeListener() {
			
			public void fieldChanged(Field field, int context) {
				UiApplication.getUiApplication().popScreen(UiApplication.getUiApplication().getActiveScreen());
				UiApplication.getUiApplication().pushScreen(new SigninScreen());
			}
		});
		mvrm.add(logoutButton);
		mvrm.add(new SpaceField(5));
		add(mvrm);
	}
	
	public boolean isDirty() {
	    return false;
	}
}
