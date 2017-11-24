package com.family.financial.management.dao.mapper;

import com.family.financial.management.dao.entity.AccountType;
import com.family.financial.management.dao.entity.AccountTypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountTypeMapper {
    long countByExample(AccountTypeExample example);

    int deleteByExample(AccountTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountType record);

    int insertSelective(AccountType record);

    List<AccountType> selectByExample(AccountTypeExample example);

    AccountType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountType record, @Param("example") AccountTypeExample example);

    int updateByExample(@Param("record") AccountType record, @Param("example") AccountTypeExample example);

    int updateByPrimaryKeySelective(AccountType record);

    int updateByPrimaryKey(AccountType record);

    List<AccountType> selectBasicTypes();

    List<AccountType> selectUsersTypes(@Param("userId") Long userId);
}