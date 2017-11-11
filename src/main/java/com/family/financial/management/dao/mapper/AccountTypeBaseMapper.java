package com.family.financial.management.dao.mapper;

import com.family.financial.management.dao.entity.AccountTypeBase;
import com.family.financial.management.dao.entity.AccountTypeBaseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountTypeBaseMapper {
    long countByExample(AccountTypeBaseExample example);

    int deleteByExample(AccountTypeBaseExample example);

    int deleteByPrimaryKey(Long topLevelId);

    int insert(AccountTypeBase record);

    int insertSelective(AccountTypeBase record);

    List<AccountTypeBase> selectByExample(AccountTypeBaseExample example);

    AccountTypeBase selectByPrimaryKey(Long topLevelId);

    int updateByExampleSelective(@Param("record") AccountTypeBase record, @Param("example") AccountTypeBaseExample example);

    int updateByExample(@Param("record") AccountTypeBase record, @Param("example") AccountTypeBaseExample example);

    int updateByPrimaryKeySelective(AccountTypeBase record);

    int updateByPrimaryKey(AccountTypeBase record);
}