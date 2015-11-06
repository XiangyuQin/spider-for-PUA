package com.spider.redis;

import com.spider.util.StringUtil;

import redis.clients.jedis.Jedis;

public class PuahomeBbsRedis {
	private Jedis jedis;
	PuahomeBbsRedis() {
		String host = "127.0.0.1";
		int port = 6379;
		String password = "";
		int db = 0;
		this.jedis = new Jedis(host, port);
		if (StringUtil.notEmpty(password)) {
			jedis.auth(password);
		}
		jedis.select(db);
	}
	
}
