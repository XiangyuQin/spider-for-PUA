package com.spider.output.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.enums.RecordMark;
import com.spider.output.Output;
import com.spider.redis.RecordedUrlRedis;
import com.spider.util.Config;
import com.spider.util.ServerContext;

public class OutputRecordResult implements Output{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String url;
	public OutputRecordResult(String url){
		this.url = url;
	}
	public boolean process() {
		try{
			outPutRecordResult();
		}catch(Exception e){
			logger.error("error", e);
			return false;
		}
		return true;
	}
	private long outPutRecordResult() throws Exception{
		long Result = 0;
		ServerContext.cacheUrl.put(url, RecordMark.RECORD.getMark());
		RecordedUrlRedis RecordedUrlRedis= new RecordedUrlRedis(Config.INSTANCE.getConfigValue("Puahome.Recorded.Url.Prefix"));
		Result = RecordedUrlRedis.hsetUrl(url, RecordMark.RECORD.getMark());
		logger.info("url:"+url);
		logger.info("ServerContext.cacheUrl size in pipeline:"+ServerContext.cacheUrl.size());
		return Result;
	}
}
