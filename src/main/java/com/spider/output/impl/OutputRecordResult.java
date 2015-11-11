package com.spider.output.impl;

import com.spider.enums.RecordMark;
import com.spider.output.Output;
import com.spider.redis.RecordedUrlRedis;
import com.spider.util.Config;
import com.spider.util.ServerContext;

public class OutputRecordResult implements Output{

	private String url;
	public OutputRecordResult(String url){
		this.url = url;
	}
	public boolean process() {
		try{
			outPutRecordResult();
		}catch(Exception e){
			System.out.println("error:"+e);
			return false;
		}
		return true;
	}
	private long outPutRecordResult() throws Exception{
		long Result = 0;
		ServerContext.cacheUrl.put(url, RecordMark.RECORD.getMark());
		RecordedUrlRedis RecordedUrlRedis= new RecordedUrlRedis(Config.INSTANCE.getConfigValue("Puahome.Recorded.Url.Prefix"));
		Result = RecordedUrlRedis.hsetUrl(url, RecordMark.RECORD.getMark());
		System.out.println("url:"+url);
		System.out.println("ServerContext.cacheUrl size in pipeline:"+ServerContext.cacheUrl.size());
		return Result;
	}
}
