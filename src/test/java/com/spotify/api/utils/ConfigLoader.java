package com.spotify.api.utils;

import static com.spotify.api.utils.PropertyUtils.propertyLoader;

import java.util.Properties;

public class ConfigLoader {
	
	private  final Properties properties;
	private static  ConfigLoader configLoader; 
	
	private ConfigLoader() {
		properties =  propertyLoader("src/test/resources/Config.properties");
	}
	
	public static ConfigLoader getInstance() {
		if(configLoader==null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}
	
	public  String getClientId() {
		String prop = properties.getProperty("client_id");
		if(prop!=null) 	return prop;
		else throw new RuntimeException("Error in the code");
	}
	
	public String getClientSecret() {
		String prop = properties.getProperty("client_secret");
		if(prop!=null) 	return prop;
		else throw new RuntimeException("Error in the code");
	}
	
	public String getRefreshToken() {
		String prop = properties.getProperty("refresh_token");
		if(prop!=null) 	return prop;
		else throw new RuntimeException("Error in the code");
	}
	
	public String getGrantType() {
		String prop = properties.getProperty("grant_type");
		if(prop!=null) 	return prop;
		else throw new RuntimeException("Error in the code");
	}
	
	public String getUser() {
		String prop = properties.getProperty("user_id");
		if(prop!=null) 	return prop;
		else throw new RuntimeException("Error in the code");
	}
	
	public String getAccessToken() {
		String prop = properties.getProperty("accessToken");
		if(prop!=null) 	return prop;
		else throw new RuntimeException("Error in the code");

	}
}
