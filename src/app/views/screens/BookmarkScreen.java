package app.views.screens;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import rubyx.custom_fields.ScreenBannar;
import rubyx.tabbedUI.TabbedButton;
import app.AirCrewApp;
import app.controllers.BookmarkController;
import app.models.AirCrew;
import app.models.Bookmark;
import app.models.Deal;
import app.models.DealDetailsRequest;
import app.models.Images;
import app.views.fields.listings.ListingField;
import app.views.managers.deals.DealDetailsScreenManager;

public class BookmarkScreen extends MainScreen{
	
	private TabbedButton backButton;
	private TabbedButton homeButton;
	private VerticalFieldManager vrManager;
	private BookmarkController bookmarkController;
	private BookmarkScreen currentScreen;
	
	Bitmap[] images = Images.profile_pics;
	
	public BookmarkScreen(BookmarkController _bookmarkController){
		super(Manager.USE_ALL_HEIGHT | Manager.NO_VERTICAL_SCROLL | Manager.NO_VERTICAL_SCROLLBAR);
		bookmarkController = _bookmarkController;
		currentScreen = this;
		Manager mainManager = getMainManager();
		mainManager.setBackground(BackgroundFactory.createBitmapBackground(Images.screen_background));
		backButton = new TabbedButton("Back", 6, 100, 36);
		backButton.setRVAlue(10);
		backButton.setChangeListener(AirCrewApp.backButtonListener);
		homeButton = new TabbedButton("Home", 6, 100, 36);
		homeButton.setRVAlue(10);
		setTitle(new ScreenBannar("BookMarks", 40, backButton, homeButton));
		
		vrManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		add(vrManager);
	}
	
	public void updateScreen(){
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
				currentScreen.delete(vrManager);
				vrManager = new VerticalFieldManager(Manager.USE_ALL_HEIGHT);
				VerticalFieldManager listManager = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
				Deal[] bookmarks = bookmarkController.getBookmarks();
				for(int i=0; i < bookmarks.length; i++){
					Deal bookmark = bookmarks[i];
					Field listItem = new ListingField(AirCrew.image_medium + bookmark.getLogo(), bookmark.getName(), "",8);
					listItem.setChangeListener(bookmarkDetailsListener);
					listManager.add(listItem);
				}
				vrManager.add(listManager);
				add(vrManager);
				vrManager.invalidate();
			}
		});
	}
	
	public FieldChangeListener bookmarkDetailsListener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			final int index = field.getIndex();
			final Deal[] bookmarks = bookmarkController.getBookmarks();
			if(bookmarks[index].getDealDetails() == null){
				DealDetailsRequest dealDetailsRequest = new DealDetailsRequest(){
					public void afterSuccess(){
						bookmarks[index].setDealDetails(dealDetails);
						UiApplication.getUiApplication().invokeAndWait(new Runnable() {
							public void run() {
								DealDetailsScreenManager aboutDeals = new DealDetailsScreenManager(bookmarks[index]);
								aboutDeals.pushScreen();
							}
						});
					}
				};
				dealDetailsRequest.getDetials(bookmarks[index].getId());
			} else {
				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					public void run() {
//						DealDetailsScreenManager aboutDeals = new DealDetailsScreenManager(bookmarks[index].getDealDetails());
//						aboutDeals.pushScreen();
					}
				});
			}
		}
	};

	public boolean isDirty() {
	    return false;
	}
}
