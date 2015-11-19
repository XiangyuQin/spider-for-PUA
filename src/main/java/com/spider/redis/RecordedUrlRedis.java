package com.spider.redis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.common.DataTransform;
import com.spider.util.Config;
import com.spider.util.ConfigStatic;

import redis.clients.jedis.Jedis;

public class RecordedUrlRedis extends SuperRedis{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Jedis jedis;
	private String key;
	public RecordedUrlRedis(String prefix){
		this.key = Config.INSTANCE.getConfigValue(prefix+ConfigStatic.KEY);
		jedis = init(prefix);
	}
	
	public long hsetUrl(String url,int mark){
		try{
			long record = jedis.hset(key, url, transformStringMark(mark));
			return record;
		}catch(Exception e){
			logger.error("hsetUrl error:",e);
			return 0L;
		}
		
	}
	
	public Map<String, Integer> hgetUrl(){
		Map<String, Integer> result = new HashMap<String, Integer>(); 
		try{
			result = loadAndHandleRecord();
			return result;
		}catch(Exception e){
			logger.error("error:",e);
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
