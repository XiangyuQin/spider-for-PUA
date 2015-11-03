package com.spider.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spider.models.PuahomeBbs;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class PuaHomeProcessor implements PageProcessor{

	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setCharset("UTF-8");
	
    HashMap<String,String> hashMap = new HashMap<String, String>();
//  private String URL_PAGE = "http://www.soku.com/channel/personlist.*\\.html";
  private String WEB_URL = "http://www.puahome.com/bbs/";
  private String URL_PAGE = "(f-54-(\\d*)\\.html)";
  private String URL_CONTENT = "(pua-(\\d*)-(\\d*)-(\\d*)\\.html)";
  private String XEGEX_NAME = "<span class=\"name\">(.*)</span>";
  private String XEGEX_ALL = "<div class=\"G\">[\\s\\S]*<img src=\"(.*)\"[\\s\\S]*onerror[\\s\\S]*<!--G end-->" +
          "[\\s\\S]*<ul class=\"params\">([\\s\\S]*)</ul>" +
          "[\\s\\S]*<div class=\"intro\">([\\s\\S]*)</div>[\\s\\S]*</div>";
  private String XEGEX_IMF = "[\\s\\S]*<li class=\"short\"><label>性别:</label><span>(.*)</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>地区:</label><span>(.*)</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>血型:</label><span>(.*)</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>职业:</label><span title=\"(.*)\">[\\s\\S]*</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>身高:</label><span>(.*)</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>星座:</label><span>(.*)</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>生日:</label><span>(.*)</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>别名:</label><span>(.*)</span></li>" +
          "[\\s\\S]*<li class=\"short\"><label>出生地:</label><span>(.*)</span></li>";
  private String XEGEX_BRIEF = "([\\s\\S]*)<span id=.*show_all_more_dot[\\s\\S]*>([\\s\\S]*)</span>[\\s\\S]*<a href=[\\s\\S]*>";
  private String XEGEX_TEAM = "<li[\\s\\S]*>地区:</label><span>(.*)</span></li>[\\s\\S]*<li[\\s\\S]*><label>成员:</label>[\\s\\S]*<span title=\"([^>]*)\">";
  private String XEGEX_BRIEF_SHORT = "([\\s\\S]*)</div>[\\s\\S]*id=\"honor\"";
  private String XEGEX_DATE = "\\d*-\\d*-\\d*";
  private String XEGEX_DATE_S = "\\d*-\\d*";
  private String XEGEX_BYNAME = "title=\"(.*)\"";

  public void process(Page page) {
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
          System.out.println("***********************");
          page.addTargetRequests(listUrlList);
          page.addTargetRequests(contentUrlList);
      } else {
          Map<String, String> itemMap = AnalyzePage(page);
          PuahomeBbs puahomeBbs = new PuahomeBbs();
          puahomeBbs.generatePuahomeBbs(itemMap);
          page.putField(puahomeBbs.getClass().toString(), puahomeBbs);
//          if(){
//        	  
//          }
//              page.putField("tts", teamtoStarsubjects);
//          }
//          page.putField("star", starsubject);
      }
      }



  private List<String> mergeUrl(List<String> urlList,String web_url){
	  List<String> mergedUrlList = new ArrayList<String>();
      for(String url:urlList){
    	  mergedUrlList.add(web_url+url);
      }
      return mergedUrlList;
  }
  
  private Map<String, String> AnalyzePage(Page page) {
	  Map<String,String> itemMap = new HashMap<String, String>();
	  
      return itemMap;
      
  }

  public Site getSite() {
      return site;
  }

}
