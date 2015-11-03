package com.spider.mapper;

import com.spider.models.PuahomeBbs;
import com.spider.models.PuahomeBbsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PuahomeBbsMapper {
    int countByExample(PuahomeBbsCriteria example);

    int deleteByExample(PuahomeBbsCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(PuahomeBbs record);

    int insertSelective(PuahomeBbs record);

    List<PuahomeBbs> selectByExampleWithBLOBs(PuahomeBbsCriteria example);

    List<PuahomeBbs> selectByExample(PuahomeBbsCriteria example);

    PuahomeBbs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PuahomeBbs record, @Param("example") PuahomeBbsCriteria example);

    int updateByExampleWithBLOBs(@Param("record") PuahomeBbs record, @Param("example") PuahomeBbsCriteria example);

    int updateByExample(@Param("record") PuahomeBbs record, @Param("example") PuahomeBbsCriteria example);

    int updateByPrimaryKeySelective(PuahomeBbs record);

    int updateByPrimaryKeyWithBLOBs(PuahomeBbs record);

    int updateByPrimaryKey(PuahomeBbs record);
}