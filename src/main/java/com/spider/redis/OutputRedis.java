package com.spider.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.spider.common.DataTransform;
import com.spider.util.Config;
import com.spider.util.ConfigStatic;

import redis.clients.jedis.Jedis;

public class OutputRedis extends SuperRedis{
	private Jedis jedis;
	private String key;
	private List<String> unnecessaryKeys;
	public OutputRedis(String prefix) {
		String keyMark = prefix+ConfigStatic.KEY;
		this.key = Config.INSTANCE.getConfigValue(keyMark);
		System.out.println("OutputRedis init keyMark:"+keyMark);
		System.out.println("OutputRedis init key:"+this.key);
		this.unnecessaryKeys = getUnnecessaryKeys(prefix);
		jedis = init(prefix);
	}
	
	public <T> long hsetItem(String url,T c){
		try{
			long result = hsetAndHandle(url,c);
			return result;
		}catch(Exception e){
			System.out.println("error"+e);
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
	
	private List<String> getUnnecessaryKeys(String prefix) {
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
