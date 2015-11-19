package com.spider.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MybatisUtil {
	 private static Logger logger = LoggerFactory.getLogger(MybatisUtil.class);
	 //定义静态方法获取 参数：mybatis-config.xml;
	 public static SqlSessionFactory getSqlSessionFactory(String resource) {
		 SqlSessionFactory sqlSessionFactory;
		  Reader reader = null;
		  try {
		   reader = Resources.getResourceAsReader(resource);
		  } catch (IOException e) {
		   logger.error("error",e);
		  }
		  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        return sqlSessionFactory;
	     }
}
