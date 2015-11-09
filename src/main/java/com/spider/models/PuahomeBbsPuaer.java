package com.spider.models;

import java.io.Serializable;

import com.spider.analyzeresult.PuahomeResult;
import com.spider.common.DataTransform;

@SuppressWarnings("serial")
public class PuahomeBbsPuaer implements Serializable {
    /**
	 * 
	 */
	private Integer id;

    private String name;
    
    private String nameMd5;
    
    private String personalurl;

    private Integer themenum;

    private Integer fansnum;

    private Integer attentionnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getPersonalurl() {
		return personalurl;
	}

	public void setPersonalurl(String personalurl) {
		this.personalurl = personalurl;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNameMd5() {
		return nameMd5;
	}

	public void setNameMd5(String nameMd5) {
		this.nameMd5 = nameMd5;
	}

	public Integer getThemenum() {
        return themenum;
    }

    public void setThemenum(Integer themenum) {
        this.themenum = themenum;
    }

    public Integer getFansnum() {
        return fansnum;
    }

    public void setFansnum(Integer fansnum) {
        this.fansnum = fansnum;
    }

    public Integer getAttentionnum() {
        return attentionnum;
    }

    public void setAttentionnum(Integer attentionnum) {
        this.attentionnum = attentionnum;
    }

    public void generatePuahomeBbsPuaer(PuahomeResult puahomeResult) throws Exception{
		this.name = puahomeResult.getWriter();
		this.personalurl = puahomeResult.getPersonalUrl();
		this.themenum = DataTransform.string2Integer(puahomeResult.getThemenum());
		this.fansnum = DataTransform.string2Integer(puahomeResult.getFansnum());
		this.attentionnum = DataTransform.string2Integer(puahomeResult.getAttentionnum());
    }
    
    
    
@Override
	public String toString() {
		return "PuahomeBbsPuaer [id=" + id + ", name=" + name + ", personalurl=" + personalurl + ", themenum="
				+ themenum + ", fansnum=" + fansnum + ", attentionnum=" + attentionnum + "]";
	}

public void generate(PuahomeResult puahomeResult) {
	try{
		generatePuahomeBbsPuaer(puahomeResult);
	}catch(Exception e){
		System.out.println("error:"+e);
	}
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
        PuahomeBbsPuaer other = (PuahomeBbsPuaer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getThemenum() == null ? other.getThemenum() == null : this.getThemenum().equals(other.getThemenum()))
            && (this.getFansnum() == null ? other.getFansnum() == null : this.getFansnum().equals(other.getFansnum()))
            && (this.getAttentionnum() == null ? other.getAttentionnum() == null : this.getAttentionnum().equals(other.getAttentionnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getThemenum() == null) ? 0 : getThemenum().hashCode());
        result = prime * result + ((getFansnum() == null) ? 0 : getFansnum().hashCode());
        result = prime * result + ((getAttentionnum() == null) ? 0 : getAttentionnum().hashCode());
        return result;
    }
}