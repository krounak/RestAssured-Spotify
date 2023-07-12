package com.spotify.api.utils;

import static com.spotify.api.utils.PropertyUtils.propertyLoader;

import java.util.Properties;

public class DataLoader {

	private final Properties properties;
	private static  DataLoader dataLoader;
	
	private DataLoader() {
		properties=propertyLoader("src/test/resources/Data.properties");
	}
	
	public static DataLoader getInstance() {
		if(dataLoader==null) {
			dataLoader = new DataLoader();
		}
		return dataLoader;
	}
	
	public String getPlayList() {
		String prop = properties.getProperty("get_playlist_id");
		if(prop!=null) 	return prop;
		else throw new RuntimeException("Error in the code");
	}
}
