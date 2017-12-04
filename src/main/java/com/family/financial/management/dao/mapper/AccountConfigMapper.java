package com.family.financial.management.dao.mapper;

import com.family.financial.management.dao.entity.AccountConfig;
import com.family.financial.management.dao.entity.AccountConfigExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountConfigMapper {
    long countByExample(AccountConfigExample example);

    int deleteByExample(AccountConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountConfig record);

    int insertSelective(AccountConfig record);

    List<AccountConfig> selectByExample(AccountConfigExample example);

    AccountConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountConfig record, @Param("example") AccountConfigExample example);

    int updateByExample(@Param("record") AccountConfig record, @Param("example") AccountConfigExample example);

    int updateByPrimaryKeySelective(AccountConfig record);

    int updateByPrimaryKey(AccountConfig record);
}