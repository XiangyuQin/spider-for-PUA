package com.spider.common;

public class DataTransform {
	public static Integer string2Integer(String numStr){
		try{
			Integer num = Integer.valueOf(numStr);
			return num;
		}catch(Exception e){
			System.out.println("error:"+e);
			return 0;
		}
	}
}
