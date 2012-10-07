package app.controllers;

import app.AirCrewApp;
import app.models.Connection;
import app.models.FavoritesRequest;
import app.views.screens.favorites.FavoritesScreen;

public class FavoritesController {
	private Connection[] connections;
	FavoritesScreen favoritesScreen;
	FavoritesRequest favoritesRequest;
	private static FavoritesController favoritesController;
	
	private FavoritesController(){
		favoritesController = this;
	}
	
	public static FavoritesController getInstance(){
		if(favoritesController == null)
			favoritesController = new FavoritesController();
		return favoritesController;
	}

	public void pushFavoritesScreen(){
		favoritesRequest = new FavoritesRequest(){
			public void afterSuccess(Connection[] _connections){
				setConnections(_connections);
			}
		};
		favoritesRequest.getFavorites();
		favoritesScreen = new FavoritesScreen(this);
		AirCrewApp.app.pushScreen(favoritesScreen);
	}
	
	public void setConnections(Connection[] connections){
		this.connections = connections;
		favoritesScreen.updateFavoritesScreen();
	}
	
	public Connection[] getConnections(){
		return connections;
	}
}

