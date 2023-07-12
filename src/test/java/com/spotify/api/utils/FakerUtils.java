package com.spotify.api.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

	public static String generateName() {
		Faker fake = new Faker();
		String playListText = fake.regexify("[A-za-z0-9 ,-_]{10}");
		return playListText;
	}
	
	public static String generateDes() {
		Faker fake = new Faker();
		String playListText = fake.regexify("[A-za-z0-9 ,-_]{10}");
		return playListText;
	}
}
