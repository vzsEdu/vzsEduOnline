package com.civil.domain.utils;

/**
 * Created by byao on 3/1/16.
 */
public class CharacterUtil {
	public static String convertToAlphabetFromInt(int intNumber) {
		int baseInt = 65; //A
		return String.valueOf(Character.toChars(baseInt + intNumber));
	}

}
