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
      listUrlList = page.getHtml().xpath(ConfigStatic.XpathListUrl).links().regex(ConfigStatic.RegexRedundancyUrlList).all();
      listUrlList = settleListUrl(listUrlList);
      logger.info("lb:"+page.getUrl().toString()+":"+listUrlList.size());
      contentUrlList = page.getHtml().xpath(ConfigStatic.XpathContentUrl).links().regex(ConfigStatic.RegexUrlContent).all();
      listUrlList = mergeUrl(listUrlList,ConfigStatic.RegexUrlWeb);
      contentUrlList = mergeUrl(contentUrlList,ConfigStatic.RegexUrlWeb);
      printUrlAcquireResult(listUrlList,contentUrlList);
      List<String> urlList = duplicateUrl(contentUrlList);
      logger.info("cacheUrl size:"+ServerContext.cacheUrl.size());
      mapUrlAndListUrl(contentUrlList, page.getUrl().toString());
      page.addTargetRequests(listUrlList);
      page.addTargetRequests(urlList);
  }
  private List<String> settleListUrl(List<String> listUrlList){
	  System.out.println("  sdsd:"+listUrlList);
	  List<String> resultUrls = new ArrayList<String>();
	  for(String url:listUrlList){
	      Pattern pattern = Pattern.compile(ConfigStatic.RegexRedundancyUrlList);
	      Matcher result = pattern.matcher(url);
	      if (result.find()) {
	    	  System.out.println("  sdsd");
	    	  resultUrls.add(result.group(2)+result.group(3));
	      }else{
	    	  System.out.println("settleListUrl:"+url);
	      }
	  }
	  return resultUrls;
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
  
  private List<String> duplicateUrl(List<String> contentUrlList){
	  List<String> urlList = new ArrayList<String>();
	  for(String url:contentUrlList){
		  if(!ServerContext.cacheUrl.containsKey(url)){
			  urlList.add(url);
			  ServerContext.cacheUrl.put(url, RecordMark.NORECORD.getMark());
		  }
	  }
	  printUrlStatus(urlList);
	  return urlList;
  }
  
  private void printUrlStatus(List<String> list){
	  if(list==null||list.isEmpty()){
		  logger.info("urlList is null");
	  }else{
		  logger.info("urlList:"+list.size());
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
	  String contentString = page.getHtml().xpath(ConfigStatic.XpathContent).toString();
	  Pattern pattern = Pattern.compile(ConfigStatic.RegexContent);
      Matcher result = pattern.matcher(contentString);
      if (result.find()) {
    	  puahomeResult.setContent(result.group());
      }else{
    	  logger.error("Something was wrong in AnalyzeContent："+page.getUrl().toString()); 
      }
	  String articleImfString = page.getHtml().xpath(ConfigStatic.XpathArticleImf).toString();
      Pattern patternArticleImf = Pattern.compile(ConfigStatic.RegexArticleImf);
      Matcher resultArticleImf = patternArticleImf.matcher(articleImfString);
      
      if (resultArticleImf.find()) {
    	  puahomeResult.setTitle(resultArticleImf.group(1));
    	  puahomeResult.setEditdate(resultArticleImf.group(2));
    	  puahomeResult.setPersonalUrl(resultArticleImf.group(3));
    	  puahomeResult.setWriter(resultArticleImf.group(4));
      }else{
    	  logger.error("Something was wrong in AnalyzeContent："+page.getUrl().toString()); 
      }
      return puahomeResult;
  }

  public Site getSite() {
      return site;
  }

}
