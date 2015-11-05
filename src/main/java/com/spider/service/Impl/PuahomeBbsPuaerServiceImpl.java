package com.spider.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.mapper.PuahomeBbsPuaerMapper;
import com.spider.models.PuahomeBbsPuaer;
import com.spider.service.PuahomeBbsPuaerService;
import com.spider.util.MybatisUtil;

public class PuahomeBbsPuaerServiceImpl implements PuahomeBbsPuaerService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private PuahomeBbsPuaerMapper puahomeBbsPuaerMapper;
	private SqlSessionFactory sessionFactory;
	private SqlSession session;
	public PuahomeBbsPuaerServiceImpl(){
		sessionFactory = MybatisUtil
				.getSqlSessionFactory("mybatis-config.xml");
		session = sessionFactory.openSession();
		puahomeBbsPuaerMapper = session.getMapper(PuahomeBbsPuaerMapper.class);
	}
	public int insert(PuahomeBbsPuaer puahomeBbsPuaer) {
		System.out.println("inserting");
		int result = puahomeBbsPuaerMapper.insertItem(puahomeBbsPuaer);
		session.commit();
		return result;
	}

}
