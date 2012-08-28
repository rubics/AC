package app.controllers;

import net.rim.device.api.ui.UiApplication;
import app.models.Bookmark;
import app.models.BookmarkRequest;
import app.models.Deal;
import app.views.screens.BookmarkScreen;

public class BookmarkController {
	
	private Bookmark[] bookmarks;
	private BookmarkRequest bookmarkRequest;
	private BookmarkScreen bookmarkScreen;
	private DashboardController dashboardController;
	
	public BookmarkController(DashboardController _dashboardController){
		dashboardController = _dashboardController;
		bookmarkRequest = new BookmarkRequest(this);
		bookmarkRequest.getBookmarks();
	}
	
	public void pushScreen(){
		if(bookmarkScreen == null){
			bookmarkScreen = new BookmarkScreen(this);
		}
		UiApplication.getUiApplication().pushScreen(bookmarkScreen);
	}
	
	public void setBookmarks(Bookmark[] _bookmark){
		bookmarks = _bookmark;
		bookmarkScreen.updateScreen();
	}
	
	public Bookmark[] getBookmarks(){
		return bookmarks;
	}
}
