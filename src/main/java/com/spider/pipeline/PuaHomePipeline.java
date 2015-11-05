package com.spider.pipeline;

import com.spider.models.PuahomeBbs;
import com.spider.models.PuahomeBbsPuaer;
import com.spider.service.PuahomeBbsPuaerService;
import com.spider.service.PuahomeBbsService;
import com.spider.service.Impl.PuahomeBbsPuaerServiceImpl;
import com.spider.service.Impl.PuahomeBbsServiceImpl;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class PuaHomePipeline implements Pipeline{

	public void process(ResultItems resultItems, Task task) {
		PuahomeBbs puahomeBbs = resultItems.get(PuahomeBbs.class.getSimpleName());
		PuahomeBbsPuaer puahomeBbsPuaer = resultItems.get(PuahomeBbsPuaer.class.getSimpleName());
		if(puahomeBbs==null){
			System.out.println("puahomeBbs is null");
		}else{
			System.out.println("PuahomeBbs.class.getSimpleName():"+PuahomeBbs.class.getSimpleName());
			System.out.println("puahomeBbs:"+puahomeBbs.toString());
			PuahomeBbsService puahomeService =new PuahomeBbsServiceImpl();
			System.out.println("insert:"+puahomeService.insert(puahomeBbs));
		}
		if(puahomeBbsPuaer==null){
			System.out.println("puahomeBbsPuaer is null");
		}else{
			System.out.println("puahomeBbsPuaer.class.getSimpleName():"+PuahomeBbsPuaer.class.getSimpleName());
			System.out.println("puahomeBbsPuaer:"+puahomeBbsPuaer.toString());
			PuahomeBbsPuaerService puahomeBbsPuaerService = new PuahomeBbsPuaerServiceImpl();
			System.out.println("insert puaer:"+ puahomeBbsPuaerService.insert(puahomeBbsPuaer));
		}
	}

}
