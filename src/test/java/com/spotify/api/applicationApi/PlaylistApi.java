package com.spotify.api.applicationApi;


import static com.spotify.api.Routes.PLAYLISTS;
import static com.spotify.api.Routes.USERS;

import com.spotify.api.TokenManager;
import com.spotify.api.utils.ConfigLoader;
import com.spotify.pojo.Playlist;

import io.restassured.response.Response;

public class PlaylistApi {
 
	public static Response post(Playlist requestPlayList) {
		
	 return RestResource.post(USERS+"/"+ConfigLoader.getInstance().getUser()+PLAYLISTS, TokenManager.getToken(), requestPlayList);
	}
	
	public static Response post(String token,Playlist requestPlayList) {
			 
		return RestResource.post(USERS+"/"+ConfigLoader.getInstance().getUser()+PLAYLISTS, token, requestPlayList);
	}
	
	public static Response get(String playListId) {
		
		return RestResource.get(PLAYLISTS+"/"+playListId, TokenManager.getToken());
	 
	}
	
}
