package com.spider.pipeline;

import com.spider.enums.RecordMark;
import com.spider.models.PuahomeBbs;
import com.spider.models.PuahomeBbsPuaer;
import com.spider.redis.OutputRedis;
import com.spider.redis.RecordedUrlRedis;
import com.spider.service.PuahomeBbsPuaerService;
import com.spider.service.PuahomeBbsService;
import com.spider.service.Impl.PuahomeBbsPuaerServiceImpl;
import com.spider.service.Impl.PuahomeBbsServiceImpl;
import com.spider.util.Config;
import com.spider.util.ServerContext;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class PuaHomePipeline implements Pipeline{

	public void process(ResultItems resultItems, Task task) {
		PuahomeBbs puahomeBbs = resultItems.get(PuahomeBbs.class.getSimpleName());
		PuahomeBbsPuaer puahomeBbsPuaer = resultItems.get(PuahomeBbsPuaer.class.getSimpleName());
		String url = resultItems.get("url");
		int BbsInsertResult = 0;
		int BbsPuaerInsertResult = 0;
		
		if(puahomeBbs==null){
			System.out.println("puahomeBbs is null");
		}else{
			System.out.println("PuahomeBbs.class.getSimpleName():"+PuahomeBbs.class.getSimpleName());
			PuahomeBbsService puahomeService =new PuahomeBbsServiceImpl();
			BbsInsertResult = puahomeService.insert(puahomeBbs);
			puahomeService.close();
			OutputRedis puahomeBbsRedis = new OutputRedis(Config.INSTANCE.getConfigValue("PuahomeBbs.Article.Prefix"));
			puahomeBbsRedis.hsetItem(url, puahomeBbs);
		}
		if(puahomeBbsPuaer==null){
			System.out.println("puahomeBbsPuaer is null");
		}else{
			System.out.println("puahomeBbsPuaer.class.getSimpleName():"+PuahomeBbsPuaer.class.getSimpleName());
			PuahomeBbsPuaerService puahomeBbsPuaerService = new PuahomeBbsPuaerServiceImpl();
			BbsPuaerInsertResult = puahomeBbsPuaerService.insert(puahomeBbsPuaer);
			puahomeBbsPuaerService.close();
			OutputRedis puahomeBbsPuaerRedis = new OutputRedis(Config.INSTANCE.getConfigValue("PuahomeBbs.Puaer.Prefix"));
			puahomeBbsPuaerRedis.hsetItem(url, puahomeBbsPuaer);
		}
		if(BbsInsertResult>0&&BbsPuaerInsertResult>0&&url!=null){
			ServerContext.cacheUrl.put(url, RecordMark.RECORD.getMark());
			RecordedUrlRedis RecordedUrlRedis= new RecordedUrlRedis(Config.INSTANCE.getConfigValue("Puahome.Recorded.Url.Prefix"));
			RecordedUrlRedis.hsetUrl(url, RecordMark.RECORD.getMark());
			System.out.println("url:"+url);
			System.out.println("ServerContext.cacheUrl size in pipeline:"+ServerContext.cacheUrl.size());
		}
	}

}
