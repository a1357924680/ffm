package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.AccountTypeBase;
import com.family.financial.management.dao.entity.AccountTypeBaseExample;
import com.family.financial.management.dao.mapper.AccountTypeBaseMapper;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.emun.FFMExceptionEnum;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.service.interfaces.AccountTypeService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.family.financial.management.emun.FFMExceptionEnum.TOPTYPE_NOT_EXIST;

/**
 * Created by zhangyiping on 2017/9/21.
 */
public class AccountTypeServiceImpl implements AccountTypeService{

    @Resource
    private AccountTypeBaseMapper accountTypeBaseMapper;

    @Resource
    private AccountTypeMapper accountTypeMapper;

    @Override
    public void createAccountType(AccountTypeForm accountTypeForm) throws FFMException {
        if (!checkTopType(accountTypeForm.getTopLeve())){
            throw new FFMException(TOPTYPE_NOT_EXIST);
        }


    }

    private boolean checkTopType(Long typeId){
        AccountTypeBaseExample example = new AccountTypeBaseExample();
        AccountTypeBaseExample.Criteria criteria = example.createCriteria();
        criteria.andTopLevelIdIsNotNull();
        List<AccountTypeBase> accountTypeBases = accountTypeBaseMapper.selectByExample(example);
        return accountTypeBases.stream().map(AccountTypeBase::getTopLevelId)
                .anyMatch(id->id.equals(typeId));
    }

}
