package com.spider.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTransform {
	
	private static final String ALGORITHM = "MD5";
	private static Logger logger = LoggerFactory.getLogger(DataTransform.class);
	public static Integer string2Integer(String numStr){
		try{
			Integer num = Integer.valueOf(numStr);
			return num;
		}catch(Exception e){
			logger.error("error:",e);
			return 0;
		}
	}
	
	public static String integer2String(Integer num){
		try{
			String result = String.valueOf(num);
			return result;
		}catch(Exception e){
			logger.error("error:",e);
			return null;
		}
	}
	
	public static Map<String, Object>  getFiledsInfo(Object o){  
		Field[] fields=o.getClass().getDeclaredFields();  
        	Map<String, Object> infoMap=new HashMap<String, Object>();;  
        	for(int i=0;i<fields.length;i++){   
        		infoMap.put(fields[i].getName(), getFieldValueByName(fields[i].getName(), o));   
        	}  
        	return infoMap;
	}
	
	public static Object getFieldValueByName(String fieldName, Object o) {  
	     try {
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	    	   logger.warn("warn:NoSuchMethodException");
	           return null;    
	       }    
	   }  
	
	
	public static String md5(String source) {  
	    StringBuffer stringBuffer = new StringBuffer(32);
	    try {
	    	if(source!=null){
		        MessageDigest md    = MessageDigest.getInstance(ALGORITHM);  
		        byte[] array        = md.digest(source.getBytes("utf-8"));    
		        for (int i = 0; i < array.length; i++) {  
		            stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));  
		        }  
	    	}
	    } catch (Exception e) {  
	    	logger.error("error:",e);
	        return null;  
	    }         
	    return stringBuffer.toString();  
	}  
	
}
