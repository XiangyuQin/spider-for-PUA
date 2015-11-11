package com.spider.util;

public class ConfigStatic {
	
	
	public static String KEY=".Key";
	public static String HOST=".Host";
	public static String POST=".Post";
	public static String PWD=".Pwd";
	public static String DB=".Db";
	public static String UNNECESSARYKEYS=".UnnecessaryKeys";
	
	public static String entranceAddress=Config.INSTANCE.getConfigValue("entrance.Address");
	
	public static String RegexUrlWeb=Config.INSTANCE.getConfigValue("Regex.Url.Web");
	public static String RegexUrlList=Config.INSTANCE.getConfigValue("Regex.Url.List");
	public static String RegexUrlContent=Config.INSTANCE.getConfigValue("Regex.Url.Content");
	public static String RegexContent=Config.INSTANCE.getConfigValue("Regex.Content");
	
	public static String XpathListUrl=Config.INSTANCE.getConfigValue("Xpath.List.Url");
	public static String XpathContentUrl=Config.INSTANCE.getConfigValue("Xpath.Content.Url");
	public static String XpathContent=Config.INSTANCE.getConfigValue("Xpath.Content");

	public static String MysqlConfig=Config.INSTANCE.getConfigValue("Mysql.Config");
}
