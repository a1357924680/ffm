package com.family.financial.management.dao.mapper;

import com.family.financial.management.dao.entity.GroupRequest;
import com.family.financial.management.dao.entity.GroupRequestExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GroupRequestMapper {
    long countByExample(GroupRequestExample example);

    int deleteByExample(GroupRequestExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupRequest record);

    int insertSelective(GroupRequest record);

    List<GroupRequest> selectByExample(GroupRequestExample example);

    GroupRequest selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupRequest record, @Param("example") GroupRequestExample example);

    int updateByExample(@Param("record") GroupRequest record, @Param("example") GroupRequestExample example);

    int updateByPrimaryKeySelective(GroupRequest record);

    int updateByPrimaryKey(GroupRequest record);
}