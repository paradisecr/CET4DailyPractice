package com.question.util;

import java.util.UUID;

public class IDUtil {

	public static String randomID(){
		String s = UUID.randomUUID().toString();
		return s.replace("-", "");
	}
	
}
