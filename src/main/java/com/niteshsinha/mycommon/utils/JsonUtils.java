package com.niteshsinha.mycommon.utils;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {

	public static boolean isValid(String json) {
	    try {
	        new JsonParser().parse(json);
	        return true;
	    } catch (JsonSyntaxException jse) {
	        return false;
	    
	    }
	}
}
