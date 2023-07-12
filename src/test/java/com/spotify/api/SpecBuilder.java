package com.spotify.api;

import static com.spotify.api.Routes.BASE_PATH;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
		
	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri("https://api.spotify.com")
				.setBasePath(BASE_PATH)
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();

	}
	
	public static RequestSpecification getAccountRequestSpec() {
		return new RequestSpecBuilder()
				.setBaseUri("https://accounts.spotify.com")
				.setContentType(ContentType.URLENC)
				.log(LogDetail.ALL).build();
		
	}
	
	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();		
	}
	
}
