package com.spider.app;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.pipeline.PuaHomePipeline;
import com.spider.processor.PuaHomeProcessor;
import com.spider.redis.RecordedUrlRedis;
import com.spider.util.Config;
import com.spider.util.ConfigStatic;
import com.spider.util.ServerContext;

import us.codecraft.webmagic.Spider;

/**
 * Main
 *
 */
public class App 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public App(){
		logger.info("spider start");
	}
	
	public void runApp(){
		ServerContext.init();
		ServerContext.cacheUrl = loadcacheUrl();
		logger.info("ServerContext.cacheUrl prepare:"+ServerContext.cacheUrl.size());
		Spider.create(new PuaHomeProcessor()).addPipeline(new PuaHomePipeline()).thread(6).addUrl(ConfigStatic.Seed).run();
//		Spider.create(new PuaHomeProcessor()).addPipeline(new PuaHomePipeline()).thread(6).test("http://www.puahome.com/bbs/pua-87806-1-1.html");
	}
	
	private Map<String, Integer> loadcacheUrl(){
		logger.info("spider seed:"+ConfigStatic.Seed);
		RecordedUrlRedis puahomeRecordUrlRedis = new RecordedUrlRedis(Config.INSTANCE.getConfigValue("Puahome.Recorded.Url.Prefix"));
		return puahomeRecordUrlRedis.hgetUrl();
	}
    public static void main( String[] args )
    {
    	Config.INSTANCE.init("SpiderConfig");
    	App app = new App();
    	app.runApp();
    }
}
