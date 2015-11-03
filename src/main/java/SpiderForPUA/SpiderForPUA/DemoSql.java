package SpiderForPUA.SpiderForPUA;

import java.util.List;

import com.spider.models.Userinfo;
import com.spider.service.TiebaService;
import com.spider.service.Impl.UserInfoServiceImpl;
import com.spider.util.QuaryParam;

public class DemoSql {
	private TiebaService tiebaService;
	public DemoSql(){
		tiebaService = new UserInfoServiceImpl();
	}
	public void selectAll(){
		List<Userinfo> list = tiebaService.selectAll();
		System.out.println("list size:"+list.size());
		List<Userinfo> userinfoList = tiebaService.selectAll();
		System.out.println("size:"+userinfoList.size());
		for(Userinfo user:userinfoList){
			System.out.println("user:"+user.getName());
		}
	}
	
	public void insertItem(){
		
		Userinfo userinfo = new Userinfo();
		userinfo.setId(3);
		userinfo.setName("qinchunrui");
		userinfo.setPwd("qinchunrui");
		System.out.println("insertItem:"+tiebaService.insertItem(userinfo));
	}
	
	public void del(){
		QuaryParam quaryParam = new QuaryParam();
		quaryParam.setMinId(1);
		quaryParam.setMaxId(3);
		System.out.println("del:"+tiebaService.deletItem(quaryParam));
	}
	
	public void update(){
		QuaryParam quaryParam = new QuaryParam();
		Userinfo userinfo = new Userinfo();
		quaryParam.setName("qinxiangyu");
		userinfo.setName("weicaoying");
		userinfo.setPwd("weicaoying");
		tiebaService.updateItem(userinfo, quaryParam);
	}
	
	public void selectLike(){
		QuaryParam quaryParam = new QuaryParam();
		quaryParam.setNameLike("qin");
		tiebaService.selectLike(quaryParam);
	}
}
