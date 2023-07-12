package com.spotify.api;

import java.time.Instant;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import com.spotify.api.applicationApi.RestResource;
import com.spotify.api.utils.ConfigLoader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TokenManager {

	private static String accessToken;
	private static Instant expiryTime;
  public synchronized static String getToken() {
		try {
			if(accessToken==null || Instant.now().isAfter(expiryTime)) {
		Response response = renewToken();
		accessToken = response.path("access_token");
		int expiryDurationInSeconds = response.path("expires_in");
		expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
			}
			else {
				System.out.println("tooken is good to use");
			}
		}
		catch (Exception e) {
			throw new RuntimeException("Error in method");
		}
		return accessToken;
		}
	
	private static Response renewToken() {
		HashMap<String,String> hashmap = new HashMap<String, String>();
		hashmap.put("client_id", ConfigLoader.getInstance().getClientId());
		hashmap.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		hashmap.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		hashmap.put("grant_type", ConfigLoader.getInstance().getGrantType());
		
		Response response = RestResource.postAccount(hashmap);
		
		if(response.statusCode()!=200) {
			throw new RuntimeException("Abort! Renew Token failed");
		}
		return response;
	}
}
