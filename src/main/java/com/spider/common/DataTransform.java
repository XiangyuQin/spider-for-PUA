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
	
	public static String integer2String(Integer num){
		try{
			String result = String.valueOf(num);
			return result;
		}catch(Exception e){
			System.out.println("error:"+e);
			return null;
		}
	}
}
