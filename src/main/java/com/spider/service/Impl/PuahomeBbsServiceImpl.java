package com.spider.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.mapper.PuahomeBbsMapper;
import com.spider.models.PuahomeBbs;
import com.spider.service.PuahomeBbsService;
import com.spider.util.MybatisUtil;

public class PuahomeBbsServiceImpl implements PuahomeBbsService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private PuahomeBbsMapper puahomeBbsMapper;
	private SqlSessionFactory sessionFactory;
	private SqlSession session;
	public PuahomeBbsServiceImpl(){
		sessionFactory = MybatisUtil
				.getSqlSessionFactory("mybatis-config.xml");
		session = sessionFactory.openSession();
		puahomeBbsMapper = session.getMapper(PuahomeBbsMapper.class);
	}
	
	public int insert(PuahomeBbs puahomeBbs){
		System.out.println("inserting");
		int result = puahomeBbsMapper.insertItem(puahomeBbs);
		session.commit();
		return result;
	}
}
