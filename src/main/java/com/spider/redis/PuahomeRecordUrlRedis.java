package com.spider.redis;

import java.util.HashMap;
import java.util.Map;

import com.spider.common.DataTransform;
import com.spider.util.StringUtil;

import redis.clients.jedis.Jedis;

public class PuahomeRecordUrlRedis {
	private Jedis jedis;
	private String key;
	public PuahomeRecordUrlRedis() {
		this.key = "puahome_recorded_url";
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
	
	public long hsetUrl(String url,int mark){
		try{
			long record = jedis.hset(key, url, transformStringMark(mark));
			return record;
		}catch(Exception e){
			System.out.println("hsetUrl error:"+e);
			return 0L;
		}
		
	}
	
	public Map<String, Integer> hgetUrl(){
		Map<String, Integer> result = new HashMap<String, Integer>(); 
		try{
			result = loadAndHandleRecord();
			return result;
		}catch(Exception e){
			return result;
		}
		
	}
	
	private Map<String, String> hgetUrlWithStingValue() throws Exception{
		Map<String, String> recordedUrl = jedis.hgetAll(key);
		if(recordedUrl!=null){
			return recordedUrl;
		}else{
			return new HashMap<String, String>();
		}	
	}
	
	private Map<String, Integer> changeUrlValueType(Map<String, String> recordedUrl) throws Exception{
		Map<String, Integer> result = new HashMap<String, Integer>(); 
		for(String urlkey:recordedUrl.keySet()){
			result.put(urlkey, DataTransform.string2Integer(recordedUrl.get(urlkey)));
		}
		return result;
	}
	
	private Map<String, Integer> loadAndHandleRecord() throws Exception{
		Map<String, String> recordWithStringValue = hgetUrlWithStingValue();
		Map<String, Integer> recordWithIntegerValue = changeUrlValueType(recordWithStringValue);
		return recordWithIntegerValue;
	}
	
	private String transformStringMark(int mark) throws Exception{
		return DataTransform.integer2String(mark)!=null?DataTransform.integer2String(mark):"0";
	}
}
