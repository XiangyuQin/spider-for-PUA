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
	Logger logger = LoggerFactory.getLogger(App.class);
	public App(){
		logger.info("spider main");
	}
	
	public void runApp(){
		ServerContext.init();
		ServerContext.cacheUrl = loadcacheUrl();
		System.out.println("ServerContext.cacheUrl prepare:"+ServerContext.cacheUrl.size());
		Spider.create(new PuaHomeProcessor()).addPipeline(new PuaHomePipeline()).thread(6).addUrl(ConfigStatic.entranceAddress).run();
//		Spider.create(new PuaHomeProcessor()).addPipeline(new PuaHomePipeline()).thread(6).test("http://www.puahome.com/bbs/pua-87806-1-1.html");
	}
	
	private Map<String, Integer> loadcacheUrl(){
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
