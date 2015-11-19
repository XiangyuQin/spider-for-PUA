package com.spider.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 读取配置文件
 * @author qinxiangyu
 *
 */
public enum Config {

	INSTANCE;
	
	private static final Logger logger = LoggerFactory
			.getLogger(Config.class);
	
	private volatile Properties configuration = new Properties();
	
	public void init(String configFile) {
		try{
			this.configuration.clear();
			getConfiguration(configFile);
		}catch(Exception e){
			logger.error("error",e);
		}
	}
	
	private void getConfiguration(String nodeName) {
		InputStream is = this.getClass().getResourceAsStream("/" + nodeName + ".properties");
        if (is != null) {
            try {
                this.configuration.load(is);
            } catch (IOException e) {
            	logger.error("getConfiguration error",e);
            } finally {
                try {
                    is.close();
                } catch (Throwable t) {
                	logger.error("property file close error",t);
                }
            }
        }
	}
	
	public String getConfigValue(String key) {
		try{
		  return this.configuration.getProperty(key);
		}catch(Exception e){
			logger.error("getConfigValue error",e);
		}
		throw new NullPointerException();
	}
}
