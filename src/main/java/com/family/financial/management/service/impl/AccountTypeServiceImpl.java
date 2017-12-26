package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.*;
import com.family.financial.management.dao.mapper.AccountMapper;
import com.family.financial.management.dao.mapper.AccountTypeBaseMapper;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.emun.AccountTypeEnum;
import com.family.financial.management.emun.FFMExceptionEnum;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.UserAndBasicTypes;
import com.family.financial.management.service.interfaces.AccountService;
import com.family.financial.management.service.interfaces.AccountTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.family.financial.management.emun.FFMExceptionEnum.BASIC_TYPE_ERROR;
import static com.family.financial.management.emun.FFMExceptionEnum.NOT_YOUR_TYPE;
import static com.family.financial.management.emun.FFMExceptionEnum.TOPTYPE_NOT_EXIST;

/**
 * Created by zhangyiping on 2017/9/21.
 */
@Service
public class AccountTypeServiceImpl implements AccountTypeService{

    @Resource
    private AccountTypeBaseMapper accountTypeBaseMapper;

    @Resource
    private AccountTypeMapper accountTypeMapper;

    @Resource
    private AccountMapper accountMapper;

    @Autowired
    private AccountTypeService accountTypeService;

    @Override
    public AccountType createAccountType(AccountTypeForm accountTypeForm) throws FFMException {
        if (!checkTopType(accountTypeForm.getTopLeve())){
            throw new FFMException(TOPTYPE_NOT_EXIST);
        }
        AccountType accountType = new AccountType();
        BeanUtils.copyProperties(accountTypeForm,accountType);
        accountType.setIsBasic(false);
        accountTypeMapper.insertSelective(accountType);
        return accountType;
    }

    @Override
    public void updateAccountType(Long typeId ,AccountTypeForm accountTypeForm) throws FFMException {
        if (!checkTopType(accountTypeForm.getTopLeve())){
            throw new FFMException(TOPTYPE_NOT_EXIST);
        }
        AccountType accountType = accountTypeMapper.selectByPrimaryKey(typeId);
        if (accountType == null){
            throw new FFMException(TOPTYPE_NOT_EXIST);
        }
        if (accountType.getIsBasic()){
            throw new FFMException(BASIC_TYPE_ERROR);
        }
        if (!accountTypeForm.getUserId().equals(accountType.getUserId())){
            throw new FFMException(NOT_YOUR_TYPE);
        }
        BeanUtils.copyProperties(accountTypeForm,accountType);
        accountTypeMapper.updateByPrimaryKey(accountType);
    }

    @Override
    public void deleteAccountType(Long typeId,Long userId) throws FFMException{
        AccountType accountType = accountTypeMapper.selectByPrimaryKey(typeId);
        if (accountType == null){
            throw new FFMException(TOPTYPE_NOT_EXIST);
        }
        if (accountType.getIsBasic()){
            throw new FFMException(BASIC_TYPE_ERROR);
        }
        if (!userId.equals(accountType.getUserId())){
            throw new FFMException(NOT_YOUR_TYPE);
        }
        ConditionForm condition = new ConditionForm();
        condition.setTypes(accountType.getId().toString());
        /*将该类别的所有账单归类到未分类*/
        AccountExample example = new AccountExample();
        example.createCriteria().andTypeEqualTo(typeId);
        List<Account> list = accountMapper.selectByExample(example);
        list.forEach(a->{
            a.setType(100002L);
            accountMapper.updateByPrimaryKey(a);
        });

        accountTypeMapper.deleteByPrimaryKey(typeId);

    }

    @Override
    public List<UserAndBasicTypes> getAllAccount(Long userId) throws FFMException {
        List<AccountType> basicTypes = accountTypeMapper.selectBasicTypes();
        Optional<List<AccountType>> userTypes = Optional.ofNullable(accountTypeMapper.selectUsersTypes(userId));
        basicTypes.addAll(userTypes.orElse((new ArrayList<AccountType>())));
        List<UserAndBasicTypes> userAndBasicTypes = getUserAndBasicTypesForm(basicTypes);
        return userAndBasicTypes;
    }



    public List<UserAndBasicTypes> getUserAndBasicTypesForm(List<AccountType> types){
        List<UserAndBasicTypes> userAndBasicTypes = new ArrayList<>();
        for (AccountType type:types) {
            UserAndBasicTypes addType = new UserAndBasicTypes();
            BeanUtils.copyProperties(type,addType);
            addType.setTopName(AccountTypeEnum.valueOf("type"+String.valueOf(addType.getTopLeve())).getTypeName());
            userAndBasicTypes.add(addType);
        }
        return userAndBasicTypes;
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
