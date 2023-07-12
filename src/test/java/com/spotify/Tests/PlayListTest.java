package com.spotify.Tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.testng.annotations.Test;

import com.spotify.api.SpecBuilder;
import com.spotify.api.StatusCode;
import com.spotify.api.applicationApi.PlaylistApi;
import com.spotify.api.utils.DataLoader;
import com.spotify.pojo.Error;
import com.spotify.pojo.Playlist;

import static com.spotify.api.utils.FakerUtils.generateDes;
import static com.spotify.api.utils.FakerUtils.generateName;
import static io.restassured.RestAssured.*;

import io.restassured.internal.http.Status;
import io.restassured.response.Response;


public class PlayListTest {
	
	private Playlist playListBuilder(String name,String description,boolean _public) {
		 return Playlist.builder().name(name).description(description)._public(_public).build();
		
	}
	
	
	@Test
	public void shouldAbleToGetPlayList() {
		String userId="gdmob5w0oey1t8ma61mhc2qat";
		given(SpecBuilder.getRequestSpec())
		.pathParam("user_id", userId)
		.when()
		.get("/users/{user_id}/playlists")
		.then()
		.spec(SpecBuilder.getResponseSpec())
		.assertThat()
		.statusCode(200)
		.body("limit", equalTo(20),
				"next",equalTo(null));	
	}
	
	@Test
	public void getPlayList() {
		String playlist_id=DataLoader.getInstance().getPlayList();
		
		Response response = PlaylistApi.get(playlist_id);
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
	}
	
	public void createPlayList() {
		
		Playlist requestPlayList = playListBuilder(generateName(),generateDes(), false);
		
		Response response = PlaylistApi.post(requestPlayList);
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.getCode()));
		Playlist responsePlayList = response.as(Playlist.class);
		
		assertThat(responsePlayList.getName(),equalTo(requestPlayList.getName()));
		assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
		assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
		
	}
	@Test
	public void shouldNotAbletoCreatePlayListWithName() {
		
		Playlist requestPlayList = playListBuilder("", generateDes(), false);
		
		Response response = PlaylistApi.post(requestPlayList);
		assertThat(response.statusCode(), equalTo(400));
		Error error = response.as(Error.class);		
		assertThat(error.getError().getStatus(), equalTo(StatusCode.CODE_400.getCode()));
		assertThat(error.getError().getMessage(),equalTo(StatusCode.CODE_400.getMsg()));

	}
	
	@Test
	public void shouldNotAbletoCreatePlayListExpiredAuth() {
		
		Playlist requestPlayList = playListBuilder(generateName(),generateDes(), false);
		String accessToken = "ggysishhyy277373";
		
		Response response = PlaylistApi.post(accessToken,requestPlayList);
		assertThat(response.statusCode(), equalTo(401));
		Error error = response.as(Error.class);
		assertThat(error.getError().getStatus(), equalTo(StatusCode.CODE_401.getCode()));
		assertThat(error.getError().getMessage(), equalTo(StatusCode.CODE_401.getMsg()));
	}
}
