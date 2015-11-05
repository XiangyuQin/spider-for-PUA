package com.spider.common;

public class QuaryParam {
	private Integer id;
	private String name;
	private Integer minId;
	private Integer maxId;
	private String  nameLike;
	private Integer limitNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinId() {
		return minId;
	}
	public void setMinId(Integer minId) {
		this.minId = minId;
	}
	public Integer getMaxId() {
		return maxId;
	}
	public void setMaxId(Integer maxId) {
		this.maxId = maxId;
	}
	
	public String getNameLike() {
		return nameLike;
	}
	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}
	
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	
	@Override
	public String toString() {
		return "QuaryParam [id=" + id + ", name=" + name + ", minId=" + minId + ", maxId=" + maxId + ", nameLike="
				+ nameLike + ", limitNum=" + limitNum + "]";
	}
	
	
}
