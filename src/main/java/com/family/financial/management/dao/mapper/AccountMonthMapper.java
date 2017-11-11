package com.family.financial.management.dao.mapper;

import com.family.financial.management.dao.entity.AccountMonth;
import com.family.financial.management.dao.entity.AccountMonthExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountMonthMapper {
    long countByExample(AccountMonthExample example);

    int deleteByExample(AccountMonthExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(AccountMonth record);

    int insertSelective(AccountMonth record);

    List<AccountMonth> selectByExample(AccountMonthExample example);

    AccountMonth selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") AccountMonth record, @Param("example") AccountMonthExample example);

    int updateByExample(@Param("record") AccountMonth record, @Param("example") AccountMonthExample example);

    int updateByPrimaryKeySelective(AccountMonth record);

    int updateByPrimaryKey(AccountMonth record);
}