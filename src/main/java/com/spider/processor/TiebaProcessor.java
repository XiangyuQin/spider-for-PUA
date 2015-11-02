package com.spider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TiebaProcessor implements PageProcessor{

	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	public Site getSite() {
		
		return null;
	}

	public void process(Page arg0) {
		
	}

}
