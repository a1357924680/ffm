package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.AccountType;
import com.family.financial.management.dao.entity.AccountTypeExample;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.emun.AccountTypeEnum;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.model.SystemType;
import com.family.financial.management.service.interfaces.ManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyiping on 2017/12/18.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private AccountTypeMapper accountTypeMapper;
    @Override
    public List<User> getAllUser() throws FFMException {
        return null;
    }

    @Override
    public int getTypePage() throws FFMException {
        AccountTypeExample example = new AccountTypeExample();
        example.createCriteria().andIsBasicBetween(true,true);
        long count = accountTypeMapper.countByExample(example);
        return (int) count/20+1;
    }

    @Override
    public List<SystemType> getSystemTypes(int pageNum) throws FFMException {
        AccountTypeExample example = new AccountTypeExample();
        example.createCriteria().andIsBasicBetween(true,true);
        example.setOffset(pageNum-1);
        example.setLimit(20);
        List<AccountType> accounts = accountTypeMapper.selectByExample(example);
        List<SystemType> forms = new ArrayList<SystemType>();
        accounts.forEach(a->{
            SystemType type = new SystemType();
            BeanUtils.copyProperties(a,type);
            type.setBasicTypeName(AccountTypeEnum.getType(a.getTopLeve()));
            forms.add(type);
        });
        return forms;
    }

    @Override
    public void addType(long topType, String typeName) throws FFMException {
        AccountType type = new AccountType();
        type.setTopLeve(topType);
        type.setTypeName(typeName);
        type.setIsBasic(true);
        type.setUserId((long) 0);
        accountTypeMapper.insertSelective(type);
    }
}
