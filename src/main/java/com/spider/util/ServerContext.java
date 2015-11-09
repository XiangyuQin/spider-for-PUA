package com.spider.util;

import java.util.HashMap;
import java.util.Map;

public  class ServerContext {
	public static Map<String, String> articleListUrl = null;
	public static Map<String, Integer> cacheUrl = null;
	public static void init(){
		articleListUrl = new HashMap<String, String>();
		cacheUrl = new HashMap<String,Integer>();
	}
}
