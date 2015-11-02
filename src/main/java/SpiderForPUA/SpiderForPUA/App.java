package SpiderForPUA.SpiderForPUA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public static void main( String[] args )
    {
    	
//    	Spider.create(new TiebaProcessor()).addUrl("http://tieba.baidu.com/f?kw=%E6%B3%A1%E5%A6%9E&ie=utf-8&pn=0").addPipeline(pipeline).thread(5).run();
    }
}
