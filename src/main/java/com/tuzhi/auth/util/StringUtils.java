package com.tuzhi.auth.util;

/**
 *
 * author : codeZ createdTime: 2018-06-13 11:34:56
 *
 */

public class StringUtils {

	public static boolean hasEmpty(String... strs) {

		for (String string : strs) {
			if (string == null || string.equals(""))
				return true;
		}
		return false;
	}

	public static boolean hasNoEmpty(String... strs) {

		return !hasEmpty(strs);
	}
	
}
