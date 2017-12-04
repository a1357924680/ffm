package com.family.financial.management.dao.mapper;

import com.family.financial.management.dao.entity.ConfigLog;
import com.family.financial.management.dao.entity.ConfigLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ConfigLogMapper {
    long countByExample(ConfigLogExample example);

    int deleteByExample(ConfigLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ConfigLog record);

    int insertSelective(ConfigLog record);

    List<ConfigLog> selectByExample(ConfigLogExample example);

    ConfigLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConfigLog record, @Param("example") ConfigLogExample example);

    int updateByExample(@Param("record") ConfigLog record, @Param("example") ConfigLogExample example);

    int updateByPrimaryKeySelective(ConfigLog record);

    int updateByPrimaryKey(ConfigLog record);
}