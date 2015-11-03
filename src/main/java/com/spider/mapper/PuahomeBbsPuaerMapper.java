package com.spider.mapper;

import com.spider.models.PuahomeBbsPuaer;
import com.spider.models.PuahomeBbsPuaerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PuahomeBbsPuaerMapper {
    int countByExample(PuahomeBbsPuaerCriteria example);

    int deleteByExample(PuahomeBbsPuaerCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(PuahomeBbsPuaer record);

    int insertSelective(PuahomeBbsPuaer record);

    List<PuahomeBbsPuaer> selectByExample(PuahomeBbsPuaerCriteria example);

    PuahomeBbsPuaer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PuahomeBbsPuaer record, @Param("example") PuahomeBbsPuaerCriteria example);

    int updateByExample(@Param("record") PuahomeBbsPuaer record, @Param("example") PuahomeBbsPuaerCriteria example);

    int updateByPrimaryKeySelective(PuahomeBbsPuaer record);

    int updateByPrimaryKey(PuahomeBbsPuaer record);
}