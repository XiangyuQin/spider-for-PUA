package com.spider.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.analyzeresult.PuahomeResult;
import com.spider.enums.RecordMark;
import com.spider.models.PuahomeBbs;
import com.spider.models.PuahomeBbsPuaer;
import com.spider.util.ConfigStatic;
import com.spider.util.ServerContext;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class PuaHomeProcessor implements PageProcessor{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCharset("UTF-8");
    HashMap<String,String> hashMap = new HashMap<String, String>();
  public void process(Page page) {
	  logger.info("page:"+page.getUrl());
      if (page.getUrl().regex(ConfigStatic.RegexUrlList).match()) {
    	  handleListPage(page);
      }
      if(page.getUrl().regex(ConfigStatic.RegexUrlContent).match()){
    	  handleContentPage(page);
      }
  }

  private void handleListPage(Page page){
      List<String> contentUrlList = new ArrayList<String>();
      List<String> listUrlList = new ArrayList<String>();
      listUrlList = page.getHtml().xpath(ConfigStatic.XpathListUrl).links().regex(ConfigStatic.RegexUrlList).all();
      logger.info("lb:"+page.getUrl().toString()+":"+listUrlList.size());
      contentUrlList = page.getHtml().xpath(ConfigStatic.XpathContentUrl).links().regex(ConfigStatic.RegexUrlContent).all();
      listUrlList = mergeUrl(listUrlList,ConfigStatic.RegexUrlWeb);
      contentUrlList = mergeUrl(contentUrlList,ConfigStatic.RegexUrlWeb);
      printUrlAcquireResult(listUrlList,contentUrlList);
      duplicateUrl(contentUrlList);
      logger.info("cacheUrl size:"+ServerContext.cacheUrl.size());
      mapUrlAndListUrl(contentUrlList, page.getUrl().toString());
      page.addTargetRequests(listUrlList);
      page.addTargetRequests(contentUrlList);
  }
  
  private void printUrlAcquireResult(List<String> listUrlList,List<String> contentUrlList){
      for(String url:listUrlList){
    	  logger.info("url:"+url);
      }
      for(String content:contentUrlList){
    	  logger.info("content:"+content);
      }
      logger.info("***********************"); 
  }
  
  private void handleContentPage(Page page){
	  PuahomeResult puahomeResult = AnalyzePage(page);
      PuahomeBbs puahomeBbs = new PuahomeBbs(page.getUrl().toString(),ServerContext.articleListUrl.get(page.getUrl().toString()));
      PuahomeBbsPuaer puahomeBbsPuaer = new PuahomeBbsPuaer();
      puahomeBbs.generate(puahomeResult);
      puahomeBbsPuaer.generate(puahomeResult);    
      page.putField("url",page.getUrl().toString());
      page.putField(puahomeBbs.getClass().getSimpleName(), puahomeBbs);
      page.putField(puahomeBbsPuaer.getClass().getSimpleName(), puahomeBbsPuaer);
  }
  
  private void duplicateUrl(List<String> contentUrlList){
	  for(String url:contentUrlList){
		  if(!ServerContext.cacheUrl.containsKey(url)){
			  ServerContext.cacheUrl.put(url, RecordMark.NORECORD.getMark());
		  }
	  }
	  
  }
  
  private void mapUrlAndListUrl(List<String> urlList,String listUrl){
	  for(String url:urlList){
		  ServerContext.articleListUrl.put(url, listUrl);
	  }
  }
  private List<String> mergeUrl(List<String> urlList,String web_url){
	  List<String> mergedUrlList = new ArrayList<String>();
      for(String url:urlList){
    	  mergedUrlList.add(web_url+url);
      }
      return mergedUrlList;
  }
  
  private PuahomeResult AnalyzePage(Page page) {
	  PuahomeResult puahomeResult = new PuahomeResult();
	  String pageString = page.getHtml().xpath(ConfigStatic.XpathContent).toString();
      Pattern pattern = Pattern.compile(ConfigStatic.RegexContent);
      Matcher result = pattern.matcher(pageString);
      if (result.find()) {
    	  puahomeResult.setCategoryurl(result.group(1));
    	  puahomeResult.setCategory(result.group(2));
    	  puahomeResult.setTitle(result.group(4));
    	  puahomeResult.setEditdate(result.group(5));
    	  puahomeResult.setReadnum(result.group(6));
    	  puahomeResult.setCommentnum(result.group(7));
    	  puahomeResult.setSupportNum(result.group(8));
    	  puahomeResult.setCollectNum(result.group(9));
    	  puahomeResult.setContent(result.group(10));
    	  puahomeResult.setPersonalUrl(result.group(11));
    	  puahomeResult.setWriter(result.group(12));
    	  puahomeResult.setThemenum(result.group(13));
    	  puahomeResult.setFansnum(result.group(14));
    	  puahomeResult.setAttentionnum(result.group(15));
      }else{
    	  logger.error("Something was wrong in AnalyzePage："+page.getUrl().toString()); 
      }
      return puahomeResult;
  }

  public Site getSite() {
      return site;
  }

}
