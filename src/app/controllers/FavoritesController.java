package app.controllers;

import app.AirCrewApp;
import app.models.Connection;
import app.models.FavoritesRequest;
import app.views.screens.favorites.FavoritesScreen;

public class FavoritesController {
	private Connection[] connections;
	FavoritesScreen favoritesScreen;
	FavoritesRequest favoritesRequest;
	
	public FavoritesController(){
		favoritesRequest = new FavoritesRequest(){
			public void afterSuccess(Connection[] _connections){
				setConnections(_connections);
			}
		};
		favoritesRequest.getFavorites();
	}
	
	public void pushFavoritesScreen(){
		favoritesScreen = new FavoritesScreen(this);
		AirCrewApp.app.pushScreen(favoritesScreen);
	}
	
	public void setConnections(Connection[] connections){
		this.connections = connections;
		// update Screen
	}
	
	public Connection[] getConnections(){
		return connections;
	}
}

