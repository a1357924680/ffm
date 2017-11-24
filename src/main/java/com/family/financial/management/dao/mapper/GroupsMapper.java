package com.family.financial.management.dao.mapper;

import com.family.financial.management.dao.entity.Groups;
import com.family.financial.management.dao.entity.GroupsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GroupsMapper {
    long countByExample(GroupsExample example);

    int deleteByExample(GroupsExample example);

    int deleteByPrimaryKey(Long groupId);

    int insert(Groups record);

    int insertSelective(Groups record);

    List<Groups> selectByExample(GroupsExample example);

    Groups selectByPrimaryKey(Long groupId);

    List<Groups> selectByString(@Param("id") String id,@Param("info") String info);

    int updateByExampleSelective(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByExample(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);
}