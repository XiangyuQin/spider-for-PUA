package com.spider.redis;

import com.spider.common.DataTransform;
import com.spider.util.Config;
import com.spider.util.ConfigStatic;
import com.spider.util.StringUtil;

import redis.clients.jedis.Jedis;

public class SuperRedis {
	protected Jedis init(String prefix){
		String host = Config.INSTANCE.getConfigValue(prefix+ConfigStatic.HOST);
		int port = DataTransform.string2Integer(Config.INSTANCE.getConfigValue(prefix+ConfigStatic.POST));
		String password = Config.INSTANCE.getConfigValue(prefix+ConfigStatic.PWD);
		int db = DataTransform.string2Integer(Config.INSTANCE.getConfigValue(prefix+ConfigStatic.DB));
		Jedis jedis = new Jedis(host, port);
		if (StringUtil.notEmpty(password)) {
			jedis.auth(password);
		}
		jedis.select(db);
		return jedis;
	}
}
