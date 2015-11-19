package com.spider.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.spider.common.DataTransform;
import com.spider.util.Config;
import com.spider.util.ConfigStatic;

import redis.clients.jedis.Jedis;

public class OutputRedis extends SuperRedis{
	private Jedis jedis;
	private String key;
	private List<String> unnecessaryKeys;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public OutputRedis(String prefix) {
		String keyMark = prefix+ConfigStatic.KEY;
		this.key = Config.INSTANCE.getConfigValue(keyMark);
		logger.info("OutputRedis init keyMark:"+keyMark);
		logger.info("OutputRedis init key:"+this.key);
		this.unnecessaryKeys = getUnnecessaryKeys(prefix);
		jedis = init(prefix);
	}
	
	public <T> long hsetItem(String url,T c){
		try{
			long result = hsetAndHandle(url,c);
			return result;
		}catch(Exception e){
			logger.error("error",e);
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
		String UnnecessaryKeyMark = prefix+ConfigStatic.UNNECESSARYKEYS;
		String UnnecessaryKeyStr =  Config.INSTANCE.getConfigValue(UnnecessaryKeyMark);
		String[] UnnecessaryKeyArray = UnnecessaryKeyStr.split(",");
		if(UnnecessaryKeyArray!=null&&UnnecessaryKeyArray.length>0){
			for(String UnnecessaryKey:UnnecessaryKeyArray){
				unnecessaryKeys.add(UnnecessaryKey.trim());
			}
		}
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
