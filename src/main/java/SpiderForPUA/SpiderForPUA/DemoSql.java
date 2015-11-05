//package SpiderForPUA.SpiderForPUA;
//
//import java.util.List;
//
//import com.spider.models.Userinfo;
//import com.spider.service.TiebaService;
//import com.spider.service.Impl.UserInfoServiceImpl;
//import com.spider.util.QuaryParam;
//
//public class DemoSql {
//	private TiebaService tiebaService;
//	public DemoSql(){
//		tiebaService = new UserInfoServiceImpl();
//	}
//	public void selectAll(){
//		List<Userinfo> list = tiebaService.selectAll();
//		System.out.println("list size:"+list.size());
//		List<Userinfo> userinfoList = tiebaService.selectAll();
//		System.out.println("size:"+userinfoList.size());
//		for(Userinfo user:userinfoList){
//			System.out.println("user:"+user.getName());
//		}
//	}
//	
//	public void insertItem(){
//		
//		Userinfo userinfo = new Userinfo();
//		userinfo.setId(3);
//		userinfo.setName("qinchunrui");
//		userinfo.setPwd("qinchunrui");
//		System.out.println("insertItem:"+tiebaService.insertItem(userinfo));
//	}
//	
//	public void del(){
//		QuaryParam quaryParam = new QuaryParam();
//		quaryParam.setMinId(1);
//		quaryParam.setMaxId(3);
//		System.out.println("del:"+tiebaService.deletItem(quaryParam));
//	}
//	
//	public void update(){
//		QuaryParam quaryParam = new QuaryParam();
//		Userinfo userinfo = new Userinfo();
//		quaryParam.setName("qinxiangyu");
//		userinfo.setName("weicaoying");
//		userinfo.setPwd("weicaoying");
//		tiebaService.updateItem(userinfo, quaryParam);
//	}
//	
//	public void selectLike(){
//		QuaryParam quaryParam = new QuaryParam();
//		quaryParam.setNameLike("qin");
//		tiebaService.selectLike(quaryParam);
//	}
//}
//
//
//package com.spider.service.Impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.spider.mapper.UserinfoMapper;
//import com.spider.models.Userinfo;
//import com.spider.models.UserinfoCriteria;
//import com.spider.service.TiebaService;
//import com.spider.util.MybatisUtil;
//import com.spider.util.QuaryParam;
//
//
//public class UserInfoServiceImpl implements TiebaService{
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	private UserinfoMapper userinfoMapper;
//	private SqlSessionFactory sessionFactory;
//	private SqlSession session;
//	public UserInfoServiceImpl(){
//		sessionFactory = MybatisUtil
//				.getSqlSessionFactory("mybatis-config.xml");
//		session = sessionFactory.openSession();
//		userinfoMapper = session.getMapper(UserinfoMapper.class);
//	}
//	
//	public List<Userinfo> selectAll() {
//		UserinfoCriteria example = new UserinfoCriteria();
//		example.createCriteria().andIdIsNotNull();
//		List<Userinfo> userinfoList = userinfoMapper.selectByExample(example);
//		return returnUserinfoList(userinfoList);
//	}
//	
//	public List<Userinfo> selectLike(QuaryParam quaryParam) {
//		logger.debug("select:"+quaryParam.toString());
//		UserinfoCriteria example = new UserinfoCriteria();
//		example.createCriteria().andIdIsNotNull().andNameLike(quaryParam.getNameLike());
//		List<Userinfo> userinfoList = userinfoMapper.selectByExample(example);
//		return returnUserinfoList(userinfoList);
//	}
//	
//	public int insertItem(Userinfo userinfo) {
//		int result = userinfoMapper.insert(userinfo);
//		session.commit();
//		return result;
//	}
//	
//	public int updateItem(Userinfo userinfo,QuaryParam quaryParam){
//			UserinfoCriteria criteria = new UserinfoCriteria();
//			String name = quaryParam.getName();
//			if(name!=null){
//				criteria.createCriteria().andNameEqualTo(name);
//				int result = userinfoMapper.updateByExampleSelective(userinfo, criteria);
//				session.commit();
//				return result;
//			}
//			return 0;
//	}
//	
//	public int deletItem(QuaryParam quaryParam){
//		UserinfoCriteria criteria = new UserinfoCriteria();
//		Integer minId = quaryParam.getMinId();
//		Integer maxId = quaryParam.getMaxId();
//		criteria.createCriteria().andIdBetween(minId, maxId);
//		int result = userinfoMapper.deleteByExample(criteria);
//		return result;
//				
//	}
//
//	public List<Userinfo> returnUserinfoList(List<Userinfo> userinfoList){
//		if(userinfoList != null){
//			return userinfoList;
//		}
//		return new ArrayList<Userinfo>();
//	}
//
//}
