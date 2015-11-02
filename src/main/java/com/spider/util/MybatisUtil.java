package com.spider.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	 
	 //定义静态方法获取 参数：mybatis-config.xml;
	 public static SqlSessionFactory getSqlSessionFactory(String resource) {
		 SqlSessionFactory sqlSessionFactory;
		  Reader reader = null;
		  try {
		   reader = Resources.getResourceAsReader(resource);
		  } catch (IOException e) {
		   System.out.println(e.getMessage());
		  }
		  sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        return sqlSessionFactory;
	     }
}
