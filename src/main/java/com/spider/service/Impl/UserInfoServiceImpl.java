package com.spider.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.spider.mapper.UserinfoMapper;
import com.spider.models.Userinfo;
import com.spider.models.UserinfoCriteria;
import com.spider.service.TiebaService;
import com.spider.util.MybatisUtil;


public class UserInfoServiceImpl implements TiebaService{
	private UserinfoMapper userinfoMapper;
	private SqlSessionFactory sessionFactory;
	private SqlSession session;
	public UserInfoServiceImpl(){
		sessionFactory = MybatisUtil
				.getSqlSessionFactory("mybatis-config.xml");
		session = sessionFactory.openSession();
		userinfoMapper = session.getMapper(UserinfoMapper.class);
	}
	public List<Userinfo> selectAll() {
		UserinfoCriteria example = new UserinfoCriteria();
		example.createCriteria().andIdIsNotNull();
		List<Userinfo> userinfoList = userinfoMapper.selectByExample(example);
		if(userinfoList != null){
			return userinfoList;
		}
		return new ArrayList<Userinfo>();
	}

	


}
