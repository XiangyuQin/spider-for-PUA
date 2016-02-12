package com.spider.pipeline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.spider.models.PuahomeBbs;
import com.spider.models.PuahomeBbsPuaer;
import com.spider.output.Output;
import com.spider.output.impl.OutputPuahomeBbs;
import com.spider.output.impl.OutputRecordResult;

public class PuaHomePipeline implements Pipeline{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	PuahomeBbs puahomeBbs;
	PuahomeBbsPuaer puahomeBbsPuaer;
	String url;
	public void process(ResultItems resultItems, Task task) {
		init(resultItems);
		outPut();
	}
	
	private void init(ResultItems resultItems){
		puahomeBbs = resultItems.get(PuahomeBbs.class.getSimpleName());
		puahomeBbsPuaer = resultItems.get(PuahomeBbsPuaer.class.getSimpleName());
		url = resultItems.get("url");
	}
	
	private void outPut(){
		if(url!=null){
			Output outputPuahomeArticle = new OutputPuahomeBbs(puahomeBbs, url);
			Output outPutRecordResult = new OutputRecordResult(url);
			if(puahomeBbs==null){
				warnNull(puahomeBbs.getClass().getSimpleName());
			}else{
				outputPuahomeArticle.process();
			}
			outPutRecordResult.process();
		}else{
			warnNull(url.getClass().getSimpleName());
		}

	}
	
	private void warnNull(String className){
		logger.warn(className+" is null");
	}
	
}
