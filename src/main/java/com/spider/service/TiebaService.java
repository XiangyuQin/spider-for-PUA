package com.spider.service;

import java.util.List;

import com.spider.models.Userinfo;
import com.spider.util.QuaryParam;

public interface TiebaService {
	public List<Userinfo> selectAll();
	public int insertItem(Userinfo userinfo);
	public List<Userinfo> selectLike(QuaryParam quaryParam);
	public int updateItem(Userinfo userinfo,QuaryParam quaryParam);
	public int deletItem(QuaryParam quaryParam);
}
