package com.spider.pipeline;

import com.spider.models.PuahomeBbs;
import com.spider.service.PuahomeService;
import com.spider.service.Impl.PuahomeServiceImpl;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class PuaHomePipeline implements Pipeline{

	public void process(ResultItems resultItems, Task task) {
		PuahomeBbs puahomeBbs = resultItems.get(PuahomeBbs.class.getSimpleName());
		System.out.println("puahomeBbs:"+puahomeBbs.getListurl());
		PuahomeService puahomeService =new PuahomeServiceImpl();
		System.out.println("insert:"+puahomeService.insert(puahomeBbs));
		
	}

}
