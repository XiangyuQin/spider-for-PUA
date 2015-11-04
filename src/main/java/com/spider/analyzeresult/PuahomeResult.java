package com.spider.analyzeresult;

public class PuahomeResult {
    private String articleId;

    private String title;

    private String writer;

    private String editdate;

    private String url;

    private String listurl;

    private String commentnum;

    private String readnum;

    private String content;
    
    private String id;

    private String name;

    private String themenum;

    private String fansnum;

    private String attentionnum;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getEditdate() {
		return editdate;
	}

	public void setEditdate(String editdate) {
		this.editdate = editdate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getListurl() {
		return listurl;
	}

	public void setListurl(String listurl) {
		this.listurl = listurl;
	}

	public String getCommentnum() {
		return commentnum;
	}

	public void setCommentnum(String commentnum) {
		this.commentnum = commentnum;
	}

	public String getReadnum() {
		return readnum;
	}

	public void setReadnum(String readnum) {
		this.readnum = readnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThemenum() {
		return themenum;
	}

	public void setThemenum(String themenum) {
		this.themenum = themenum;
	}

	public String getFansnum() {
		return fansnum;
	}

	public void setFansnum(String fansnum) {
		this.fansnum = fansnum;
	}

	public String getAttentionnum() {
		return attentionnum;
	}

	public void setAttentionnum(String attentionnum) {
		this.attentionnum = attentionnum;
	}

	@Override
	public String toString() {
		return "PuahomeResult [articleId=" + articleId + ", title=" + title + ", writer=" + writer + ", editdate="
				+ editdate + ", url=" + url + ", listurl=" + listurl + ", commentnum=" + commentnum + ", readnum="
				+ readnum + ", content=" + content + ", id=" + id + ", name=" + name + ", themenum=" + themenum
				+ ", fansnum=" + fansnum + ", attentionnum=" + attentionnum + "]";
	}
    
    

}
