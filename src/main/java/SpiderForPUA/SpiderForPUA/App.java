package SpiderForPUA.SpiderForPUA;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.spider.models.Userinfo;
import com.spider.service.TiebaService;
import com.spider.service.Impl.UserInfoServiceImpl;

/**
 * Main
 *
 */
@Controller
public class App 
{
	
    private TiebaService tiebaService;
    
	Logger logger = LoggerFactory.getLogger(App.class);
	public App(){
		logger.info("spider main");
	}
	
	public void showList(){
		tiebaService = new UserInfoServiceImpl();
		List<Userinfo> list = tiebaService.selectAll();
		System.out.println("list size:"+list.size());
		List<Userinfo> userinfoList = tiebaService.selectAll();
		System.out.println("size:"+userinfoList.size());
	}
	
    public static void main( String[] args )
    {
    	App app = new App();
    	app.showList();
    }
}
