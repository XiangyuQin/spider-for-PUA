/**
 * 
 */
package com.spider.common;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * @author XiangyuQin
 *
 */
public class DataFormat {

	public static Date getNowDateTime(){
		return new Date(System.currentTimeMillis());
	}
	
	public static Long currentTimeSeconds(){
		return System.currentTimeMillis() / 1000 * 1000;
	}
	
	public static Date String2Date(String date){
		System.out.println(date);
		try{
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        return sdf.parse(date);
		}catch(Exception e){
			System.out.println("error");
			return null;
		}
	}
}
