package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.*;
import com.family.financial.management.dao.mapper.AccountMapper;
import com.family.financial.management.dao.mapper.AccountTypeBaseMapper;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountForm;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.DefiniteAccount;
import com.family.financial.management.service.interfaces.AccountService;
import com.family.financial.management.service.interfaces.UpdateAllAccountService;
import com.family.financial.management.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.*;

import static com.family.financial.management.emun.FFMExceptionEnum.DATABASE_ERROR;
import static com.family.financial.management.emun.FFMExceptionEnum.NO_SUCH_ACCOUNT;

/**
 * Created by zhangyiping on 2017/10/14.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UpdateAllAccountService updateService;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountTypeBaseMapper accountTypeBaseMapper;

    @Override
    public void addAccount(long userId , AccountForm accountForm) throws FFMException {
        Account account = new Account();
        BeanUtils.copyProperties(accountForm,account);
        account.setUserId(userId);
        try {
            accountMapper.insertSelective(account);
            updateService.insertMonthAccount(account);
            updateService.updateAllAccount(userId);
        }catch (Exception e){
            throw new FFMException(DATABASE_ERROR);
        }
    }

    @Override
    public void updateAccount(long userId , AccountForm accountForm) throws FFMException{
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(accountForm.getId());
        Optional<List<Account>> accountList = Optional.ofNullable(accountMapper.selectByExample(example));
        Account account = accountList.orElseThrow(()->new FFMException(NO_SUCH_ACCOUNT)).get(0);
        /* 这里的为了更新月账单情况，写的有些搞笑，先删掉这笔账单，再重新插入*/
        updateService.deleteMonthAccount(account);
        BeanUtils.copyProperties(accountForm,account);
        try {
            accountMapper.updateByPrimaryKey(account);
            updateService.insertMonthAccount(account);
            updateService.updateAllAccount(userId);
        }catch (Exception e){
            throw new FFMException(DATABASE_ERROR);
        }
    }

    @Override
    public void deleteAccount(long userId , AccountForm accountForm) throws FFMException{
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(accountForm.getId());
        Optional<List<Account>> accountList = Optional.ofNullable(accountMapper.selectByExample(example));
        Account account = accountList.orElseThrow(()->new FFMException(NO_SUCH_ACCOUNT)).get(0);
        try {
            accountMapper.deleteByPrimaryKey(account.getId());
            updateService.deleteMonthAccount(account);
            updateService.updateAllAccount(userId);
        }catch (Exception e){
            throw new FFMException(DATABASE_ERROR);
        }
    }

    /**
     * 多条件查询
     * @param userId
     * @param fromDate
     * @param toDate
     * @param limit
     * @param offset
     * @return
     * @throws FFMException
     */
    @Override
    public List<DefiniteAccount> getAccountList(long userId , Date fromDate, Date toDate, Integer limit, Integer offset ) throws FFMException{
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        example.setLimit(limit);
        example.setOffset(offset);
        example.setOrderByClause("gmt_create desc");

        criteria.andUserIdEqualTo(userId);
        if (!((fromDate == null)||(toDate == null))){
            criteria.andGmtCreateBetween(fromDate,toDate);
        }
        Optional<List<DefiniteAccount>> accountList = Optional.ofNullable(accountMapper.selectDefiniteAccount(example));
        List<DefiniteAccount> acounts = accountList.orElse(new ArrayList<DefiniteAccount>());
        List<AccountTypeBase> type = accountTypeBaseMapper.selectByExample(new AccountTypeBaseExample());
        acounts.stream().forEach((u)->{
            Optional<String> imgs = type.stream().filter((v)->v.getTopLevelId()==Long.parseLong(u.getTopLevelId())).findFirst().map(AccountTypeBase::getImgs);
            //路径还没写
            u.setImgUrl(imgs.orElse(""));
        });
        return acounts;
    }

    @Override
    public List<DefiniteAccount> getByConditions(long userId , ConditionForm conditionForm) throws FFMException{
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("gmt_create desc");
        if (!(StringUtils.isEmpty(conditionForm.getLimit())||StringUtils.isEmpty(conditionForm.getOffset()))){
            example.setOffset(StringUtils.praseInteger(conditionForm.getOffset()));
            example.setLimit(StringUtils.praseInteger(conditionForm.getLimit()));
        }
        if (!(StringUtils.isEmpty(conditionForm.getMaxAccount()))){
            criteria.andAccountNumLessThanOrEqualTo(StringUtils.praseLong(conditionForm.getMaxAccount()));
        }
        if (!(StringUtils.isEmpty(conditionForm.getMinAccount()))){
            criteria.andAccountNumGreaterThanOrEqualTo(StringUtils.praseLong(conditionForm.getMinAccount()));
        }
        if (!(StringUtils.isEmpty(conditionForm.getFromDate()))){
            criteria.andGmtCreateGreaterThanOrEqualTo(StringUtils.praseDate(conditionForm.getFromDate()));
        }
        if (!(StringUtils.isEmpty(conditionForm.getToDate()))){
            criteria.andGmtCreateLessThanOrEqualTo(StringUtils.praseDate(conditionForm.getToDate()));
        }
        if (!(StringUtils.isEmpty(conditionForm.getTypes()))){
            List<String> typeStrs = StringUtils.praseList(conditionForm.getTypes());
            List<Long> types = new ArrayList<>();
            for (String type: typeStrs) {
                types.add(StringUtils.praseLong(type));
            }
            criteria.andTypeIn(types);
        }

        Optional<List<DefiniteAccount>> accountList = Optional.ofNullable(accountMapper.selectDefiniteAccount(example));
        List<DefiniteAccount> acounts = accountList.orElse(new ArrayList<DefiniteAccount>());
        List<AccountTypeBase> type = accountTypeBaseMapper.selectByExample(new AccountTypeBaseExample());
        acounts.stream().forEach((u)->{
            Optional<String> imgs = type.stream().filter((v)->v.getTopLevelId()==Long.parseLong(u.getTopLevelId())).findFirst().map(AccountTypeBase::getImgs);
            //路径还没写
            u.setImgUrl(imgs.orElse("E:\\ffm\\users\\unknow.png"));
        });
        return acounts;
    }
}
