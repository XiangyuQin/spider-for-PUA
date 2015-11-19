package com.spider.output.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.models.PuahomeBbsPuaer;
import com.spider.output.Output;
import com.spider.redis.OutputRedis;
import com.spider.service.PuahomeBbsPuaerService;
import com.spider.service.Impl.PuahomeBbsPuaerServiceImpl;
import com.spider.util.Config;

public class OutputPuahomeBbsPuaer implements Output{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private PuahomeBbsPuaer puahomeBbsPuaer;
	private String url;
	public OutputPuahomeBbsPuaer(PuahomeBbsPuaer puahomeBbsPuaer,String url){
		this.puahomeBbsPuaer = puahomeBbsPuaer;
		this.url = url;
	}
	public boolean process() {
		try{
			outPutPuaerByMysql();
			outPutPuaerRedis();
		}catch(Exception e){
			logger.error("error"+e);
			return false;
		}

		return true;
	}
	
	private int outPutPuaerByMysql() throws Exception{
		int result = 0;
		PuahomeBbsPuaerService puahomeBbsPuaerService = new PuahomeBbsPuaerServiceImpl();
		result = puahomeBbsPuaerService.insert(puahomeBbsPuaer);
		puahomeBbsPuaerService.close();
		return result; 
	}
	
	private long outPutPuaerRedis() throws Exception{
		long result = 0;
		OutputRedis outputRedis = new OutputRedis(Config.INSTANCE.getConfigValue("PuahomeBbs.Puaer.Prefix"));
		outputRedis.hsetItem(url, puahomeBbsPuaer);
		return result; 
	}
}
