package com.spider.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spider.analyzeresult.PuahomeResult;
import com.spider.enums.RecordMark;
import com.spider.models.PuahomeBbs;
import com.spider.models.PuahomeBbsPuaer;

import SpiderForPUA.SpiderForPUA.ServerContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class PuaHomeProcessor implements PageProcessor{

	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCharset("UTF-8");
    HashMap<String,String> hashMap = new HashMap<String, String>();
//  private String URL_PAGE = "http://www.soku.com/channel/personlist.*\\.html";
  private String WEB_URL = "http://www.puahome.com/bbs/";
  private String URL_PAGE = "(f-54-(.*)\\.html)";
  private String URL_CONTENT = "(pua-(\\d*)-1-(\\d*)\\.html)";
  private String CONTENT = "<div class=\"zuv_first_header cl\">[\\s\\S]* <div class=\"zuvhh_r\">[\\s\\S]*<a href=\""
	  		+ "(.*\\.html)\">\\["
	  		+ "(.*)\\]</a>[\\s\\S]*<span id=\"thread_subject\"><h1><a href=\""
	  		+ "(.*\\.html)\">"
	  		+ "(.*)</a></h1></span>[\\s\\S]*<span class=\"s5\">"
	  		+ "(\\d*-\\d*-\\d* \\d*:\\d*)</span>[\\s\\S]*<span class=\"s1\" title=\"浏览数\">"
	  		+ "(\\d*)</span>[\\s\\S]*<span class=\"s2\" title=\"评论数\">"
	  		+ "(\\d*)</span>[\\s\\S]*<span class=\"s3\" title=\"支持数\">"
	  		+ "(\\d*)</span>[\\s\\S]*<span class=\"s4\" title=\"收藏数\">"
	  		+ "(\\d*)</span>[\\s\\S]*<td class=\"t_f\" id=\".*\">"
	  		+ "([\\s\\S]*)</table>[\\s\\S]*<div class=\"zuv_author_work cl\">[\\s\\S]*<p><a href=\""
	  		+ "(.*html)\" target=\"_blank\">"
	  		+ "(.*)</a></p>[\\s\\S]*<ul>[\\s\\S]*<li class=\"li1\"><a href=\".*\" target=\"_blank\"><em class=\"em1\">"
	  		+ "(\\d*)</em><em class=\"em2\">主题</em></a></li>[\\s\\S]*<li class=\"li2\"><a href=\".*\" target=\"_blank\"><em class=\"em1\">"
	  		+ "(\\d*)</em><em class=\"em2\">粉丝</em></a></li>[\\s\\S]*<li class=\"li3\"><a href=\".*\" target=\"_blank\"><em class=\"em1\">"
	  		+ "(\\d*)</em><em class=\"em2\">关注</em></a></li>[\\s\\S]*</ul>";
  public void process(Page page) {
	  System.out.println("page:"+page.getUrl());
      List<String> contentUrlList = new ArrayList<String>();
      List<String> listUrlList = new ArrayList<String>();
      if (page.getUrl().regex(URL_PAGE).match()) {
          listUrlList = page.getHtml().xpath("//div[@class='bm bw0 pgs cl']").links().regex(URL_PAGE).all();
          System.out.println("lb:"+page.getUrl().toString()+":"+listUrlList.size());
          contentUrlList = page.getHtml().xpath("//div[@class='p_ac']").links().regex(URL_CONTENT).all();
          listUrlList = mergeUrl(listUrlList,WEB_URL);
          contentUrlList = mergeUrl(contentUrlList,WEB_URL);
          for(String url:listUrlList){
        	  System.out.println("url:"+url);
          }
          for(String content:contentUrlList){
        	  System.out.println("content:"+content);
          }
          System.out.println("***********************");
          duplicateUrl(contentUrlList);
          System.out.println("cacheUrl size:"+ServerContext.cacheUrl.size());
          mapUrlAndListUrl(contentUrlList, page.getUrl().toString());
          page.addTargetRequests(listUrlList);
          page.addTargetRequests(contentUrlList);
      } else {
    	  PuahomeResult puahomeResult = AnalyzePage(page);
          PuahomeBbs puahomeBbs = new PuahomeBbs(page.getUrl().toString(),ServerContext.articleListUrl.get(page.getUrl().toString()));
          PuahomeBbsPuaer puahomeBbsPuaer = new PuahomeBbsPuaer();
          puahomeBbs.generate(puahomeResult);
          puahomeBbsPuaer.generate(puahomeResult);
          System.out.println("output item:"+puahomeBbs.getClass().getSimpleName());
          
          page.putField("url",page.getUrl().toString());
          page.putField(puahomeBbs.getClass().getSimpleName(), puahomeBbs);
          page.putField(puahomeBbsPuaer.getClass().getSimpleName(), puahomeBbsPuaer);
      }
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
	  String pageString = page.getHtml().xpath("//div[@class='mpl']").toString();
      Pattern pattern = Pattern.compile(CONTENT);
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
//    	  System.out.println("puahomeResult.toString()"+puahomeResult.toString());
      }else{
          System.out.println("名字解析出问题："+page.getUrl().toString());
      }

      return puahomeResult;
      
  }

  public Site getSite() {
      return site;
  }

}
