/**
 * 
 */
package com.spider.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author XiangyuQin
 *
 */
public class DataFormat {
	private static Logger logger = LoggerFactory.getLogger(DataFormat.class);
	public static Date getNowDateTime(){
		return new Date(System.currentTimeMillis());
	}
	
	public static Long currentTimeSeconds(){
		return System.currentTimeMillis() / 1000 * 1000;
	}
	
	public static Date String2Date(String date){
		try{
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        return sdf.parse(date);
		}catch(Exception e){
			logger.error("error in String2Date",e);
			return null;
		}
	}
}
