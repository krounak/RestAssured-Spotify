package com.spotify.api.applicationApi;

import com.spotify.api.SpecBuilder;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {

	public static Response post(String path,String token,Object request) {
		return
		given(SpecBuilder.getRequestSpec())
		.body(request)
		.header("Authorization","Bearer "+token)
		.when()
		.post(path)
		.then()
		.spec(SpecBuilder.getResponseSpec())
		.extract()
		.response();
	}
	
	public static Response postAccount(HashMap<String, String> hashmap) {
               return given(SpecBuilder.getAccountRequestSpec())
				.formParams(hashmap)
				.when()
				.post("/api/token")
				.then()
				.spec(SpecBuilder.getResponseSpec())
				 .extract()
				 .response();
	}
	
	public static Response get(String path,String token) {
		return
				given(SpecBuilder.getRequestSpec())
				.header("Authorization","Bearer "+token)
				.when()
				.get(path)
				.then()
				.spec(SpecBuilder.getResponseSpec())
				.extract()
				.response();
	}
}
