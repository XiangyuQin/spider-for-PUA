package com.spider.models;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spider.analyzeresult.PuahomeResult;
import com.spider.common.DataFormat;
import com.spider.common.DataTransform;

@SuppressWarnings("serial")
public class PuahomeBbs implements Serializable {
    /**
	 * 
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Integer id;

    private String title;

    private String writer;

    private Date editdate;
    
    private Date createtime;

    private String url;

    private String listurl;

    private Integer commentnum;

    private Integer readnum;
    
    private Integer supportnum;
    
    private Integer collectnum;
    
    private String content;
    
    private String titleMd5;

    private String writerMd5;
    
    private String contentMd5;

    
    public PuahomeBbs(String url,String listUrl){
    	this.url = url;
    	this.listurl = listUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer == null ? null : writer.trim();
    }

    public Date getEditdate() {
        return editdate;
    }

    public void setEditdate(Date editdate) {
        this.editdate = editdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getListurl() {
        return listurl;
    }

    public void setListurl(String listurl) {
        this.listurl = listurl == null ? null : listurl.trim();
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public Integer getReadnum() {
        return readnum;
    }

    public void setReadnum(Integer readnum) {
        this.readnum = readnum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
    public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getSupportnum() {
		return supportnum;
	}

	public void setSupportnum(Integer supportnum) {
		this.supportnum = supportnum;
	}

	public Integer getCollectnum() {
		return collectnum;
	}

	public void setCollectnum(Integer collectnum) {
		this.collectnum = collectnum;
	}

	public String getTitleMd5() {
		return titleMd5;
	}

	public void setTitleMd5(String titleMd5) {
		this.titleMd5 = titleMd5;
	}

	public String getWriterMd5() {
		return writerMd5;
	}

	public void setWriterMd5(String writerMd5) {
		this.writerMd5 = writerMd5;
	}

	public String getContentMd5() {
		return contentMd5;
	}

	public void setContentMd5(String contentMd5) {
		this.contentMd5 = contentMd5;
	}

	public void generatePuahomeBbs(PuahomeResult puahomeResult) throws Exception{
    		this.title = puahomeResult.getTitle();
    		this.content = puahomeResult.getContent();
    		this.writer = puahomeResult.getWriter();
    		this.editdate = DataFormat.String2Date(puahomeResult.getEditdate());
    		this.createtime = DataFormat.getNowDateTime();
    		this.commentnum = DataTransform.string2Integer(puahomeResult.getCommentnum()); 
    		this.readnum = DataTransform.string2Integer(puahomeResult.getReadnum());
    		this.supportnum = DataTransform.string2Integer(puahomeResult.getSupportNum());
    		this.collectnum = DataTransform.string2Integer(puahomeResult.getCollectNum());
    		this.titleMd5 = DataTransform.md5(this.title);
    		this.contentMd5 = DataTransform.md5(this.content);
    		this.writerMd5 = DataTransform.md5(this.writer);
    }
    
    public void generate(PuahomeResult puahomeResult) {
    	try{
    		generatePuahomeBbs(puahomeResult);
    	}catch(Exception e){
    		logger.info("error:",e);
    	}
    }
    
    
    @Override
	public String toString() {
		return "PuahomeBbs [id=" + id + ", title=" + title + ", writer=" + writer + ", editdate=" + editdate + ", url="
				+ url + ", listurl=" + listurl + ", commentnum=" + commentnum + ", readnum=" + readnum + ", content="
				+ content + "]";
	}

	@Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PuahomeBbs other = (PuahomeBbs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getWriter() == null ? other.getWriter() == null : this.getWriter().equals(other.getWriter()))
            && (this.getEditdate() == null ? other.getEditdate() == null : this.getEditdate().equals(other.getEditdate()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getListurl() == null ? other.getListurl() == null : this.getListurl().equals(other.getListurl()))
            && (this.getCommentnum() == null ? other.getCommentnum() == null : this.getCommentnum().equals(other.getCommentnum()))
            && (this.getReadnum() == null ? other.getReadnum() == null : this.getReadnum().equals(other.getReadnum()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getWriter() == null) ? 0 : getWriter().hashCode());
        result = prime * result + ((getEditdate() == null) ? 0 : getEditdate().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getListurl() == null) ? 0 : getListurl().hashCode());
        result = prime * result + ((getCommentnum() == null) ? 0 : getCommentnum().hashCode());
        result = prime * result + ((getReadnum() == null) ? 0 : getReadnum().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}