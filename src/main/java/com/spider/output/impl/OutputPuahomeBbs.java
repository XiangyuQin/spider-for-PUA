package com.spider.output.impl;

import com.spider.models.PuahomeBbs;
import com.spider.output.Output;
import com.spider.redis.OutputRedis;
import com.spider.service.PuahomeBbsService;
import com.spider.service.Impl.PuahomeBbsServiceImpl;
import com.spider.util.Config;

public class OutputPuahomeBbs implements Output{

	private PuahomeBbs puahomeBbs;
	private String url;
	
	public OutputPuahomeBbs(PuahomeBbs puahomeBbs,String url){
		this.puahomeBbs = puahomeBbs;
		this.url = url;
	}
	
	public boolean process() {
		try{
			outPutArticleByMysql();
			outPutArticleRedis();
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
	
	private int outPutArticleByMysql() throws Exception{
		int result = 0;
		PuahomeBbsService puahomeService =new PuahomeBbsServiceImpl();
		result = puahomeService.insert(puahomeBbs);
		puahomeService.close();
		return result;
	}
	
	private long outPutArticleRedis() throws Exception{
		long result = 0;
		OutputRedis outputRedis = new OutputRedis(Config.INSTANCE.getConfigValue("PuahomeBbs.Article.Prefix"));
		result = outputRedis.hsetItem(url, puahomeBbs);
		return result; 
	}
}
