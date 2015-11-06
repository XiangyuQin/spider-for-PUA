package SpiderForPUA.SpiderForPUA;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.spider.pipeline.PuaHomePipeline;
import com.spider.processor.PuaHomeProcessor;
import com.spider.redis.PuahomeRecordUrlRedis;

import us.codecraft.webmagic.Spider;

/**
 * Main
 *
 */
@Controller
public class App 
{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public App(){
		logger.info("spider main");
	}
	
	public void runApp(){
		ServerContext.init();
		
		ServerContext.cacheUrl = loadcacheUrl();
		System.out.println("ServerContext.cacheUrl prepare:"+ServerContext.cacheUrl.size());
		Spider.create(new PuaHomeProcessor()).addPipeline(new PuaHomePipeline()).thread(6).addUrl("http://www.puahome.com/bbs/f-54-1.html").run();
//		Spider.create(new PuaHomeProcessor()).addPipeline(new PuaHomePipeline()).thread(6).test("http://www.puahome.com/bbs/pua-87806-1-1.html");
	}
	
	private Map<String, Integer> loadcacheUrl(){
		PuahomeRecordUrlRedis puahomeRecordUrlRedis = new PuahomeRecordUrlRedis();
		return puahomeRecordUrlRedis.hgetUrl();
	}
    public static void main( String[] args )
    {
    	App app = new App();
    	app.runApp();
    }
}
