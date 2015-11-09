package com.spider.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.spider.common.DataTransform;
import com.spider.util.StringUtil;

import redis.clients.jedis.Jedis;

public class OutputRedis {
	private Jedis jedis;
	private String key;
	private List<String> unnecessaryKeys;
	public OutputRedis(String key) {
		this.key = key;
		this.unnecessaryKeys = getUnnecessaryKeys(key);
		init(key);
	}
	
	private void init(String key){
		this.key = key;
		String host = "127.0.0.1";
		int port = 6379;
		String password = "";
		int db = 0;
		this.jedis = new Jedis(host, port);
		if (StringUtil.notEmpty(password)) {
			jedis.auth(password);
		}
		jedis.select(db);
	}
	
	public <T> long hsetItem(String url,T c){
		try{
			long result = hsetAndHandle(url,c);
			return result;
		}catch(Exception e){
			System.out.println("error");
			return 0L;
		}	
	}
	
	private <T> long hsetAndHandle(String url,T c) throws Exception{
		Map<String, Object> puahomeBbsMap = DataTransform.getFiledsInfo(c);
		deletUnnecessaryKey(puahomeBbsMap);
		String puahomeBbsStr = jsonFormat(puahomeBbsMap);
		long result = hset(this.key,url,puahomeBbsStr);
		return result; 
	}
	
	private List<String> getUnnecessaryKeys(String key) {
		List<String> unnecessaryKeys = new ArrayList<String>();
		unnecessaryKeys.add("title");
		unnecessaryKeys.add("content");
		unnecessaryKeys.add("writer");
		unnecessaryKeys.add("name");
		return unnecessaryKeys;
	}
	
	private long hset(String key, String field, String puahomeBbsStr) {
		long result = 0L;
		if(key!=null&&field!=null&&puahomeBbsStr!=null){
			jedis.hset(key, field, puahomeBbsStr);
		}
		return result;
	}

	private void deletUnnecessaryKey(Map<String, Object> puahomeBbsMap) {
		for(String unnecessaryKey:this.unnecessaryKeys){
			if(puahomeBbsMap.containsKey(unnecessaryKey)){
				puahomeBbsMap.remove(unnecessaryKey);
			}
		}	
	}

	private String jsonFormat(Map<String, Object> map){
		JSONObject json = new JSONObject();
		for(String key:map.keySet()){
			json.put(key, map.get(key));
		}
		return json.toJSONString();
	}
	
}
