package com.spider.pipeline;

import com.spider.models.PuahomeBbs;
import com.spider.models.PuahomeBbsPuaer;
import com.spider.output.Output;
import com.spider.output.impl.OutputPuahomeBbs;
import com.spider.output.impl.OutputPuahomeBbsPuaer;
import com.spider.output.impl.OutputRecordResult;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class PuaHomePipeline implements Pipeline{
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
			Output outputPuahomePuaer = new OutputPuahomeBbsPuaer(puahomeBbsPuaer, url);
			Output outPutRecordResult = new OutputRecordResult(url);
			if(puahomeBbs==null){
				warnNull(puahomeBbs.getClass().getSimpleName());
			}else{
				outputPuahomeArticle.process();
			}
			if(puahomeBbsPuaer==null){
				warnNull(puahomeBbsPuaer.getClass().getSimpleName());
			}else{
				outputPuahomePuaer.process();
			}
			outPutRecordResult.process();
		}else{
			warnNull(url.getClass().getSimpleName());
		}

	}
	
	private void warnNull(String className){
		System.out.println(className+" is null");
	}
	
}
