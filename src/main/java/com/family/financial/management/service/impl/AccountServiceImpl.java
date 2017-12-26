package com.family.financial.management.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.family.financial.management.dao.entity.*;
import com.family.financial.management.dao.mapper.AccountMapper;
import com.family.financial.management.dao.mapper.AccountMonthMapper;
import com.family.financial.management.dao.mapper.AccountTypeBaseMapper;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.emun.AccountTypeEnum;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountForm;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.DefiniteAccount;
import com.family.financial.management.service.interfaces.AccountService;
import com.family.financial.management.service.interfaces.UpdateAllAccountService;
import com.family.financial.management.service.interfaces.UserService;
import com.family.financial.management.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static com.family.financial.management.emun.FFMExceptionEnum.DATABASE_ERROR;
import static com.family.financial.management.emun.FFMExceptionEnum.NOT_YOUR_ACCOUNT;
import static com.family.financial.management.emun.FFMExceptionEnum.NO_SUCH_ACCOUNT;

/**
 * Created by zhangyiping on 2017/10/14.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UpdateAllAccountService updateService;
    @Autowired
    private UserService userService;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountTypeMapper accountTypeMapper;
    @Resource
    private AccountTypeBaseMapper accountTypeBaseMapper;
    @Resource
    private AccountMonthMapper accountMonthMapper;

    @Resource
    private AccountMonthMapper monthMapper;
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
    public DefiniteAccount updateAccount(long userId , AccountForm accountForm) throws FFMException{
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(accountForm.getId());
        Account account = accountMapper.selectByPrimaryKey(accountForm.getId());
        if (account == null){
            throw new FFMException(NO_SUCH_ACCOUNT);
        }
        if (!account.getUserId().equals(userId)){
            throw new FFMException(NOT_YOUR_ACCOUNT);
        }
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
        example.clear();
        example.createCriteria().andIdEqualTo(accountForm.getId());
        Account a = accountMapper.selectByPrimaryKey(accountForm.getId());
        DefiniteAccount definiteAccount = new DefiniteAccount();
        BeanUtils.copyProperties(a,definiteAccount);
        AccountType type = accountTypeMapper.selectByPrimaryKey(a.getType());
        definiteAccount.setFatherName(AccountTypeEnum.getType(type.getTopLeve()));
        definiteAccount.setTopLevelId(String.valueOf(type.getTopLeve()));
        definiteAccount.setTypeName(type.getTypeName());
        return definiteAccount;
    }

    @Override
    public void deleteAccount(long userId , long id) throws FFMException{
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(id);
        Account account = accountMapper.selectByPrimaryKey(id);
        if (account == null){
            throw new FFMException(NO_SUCH_ACCOUNT);
        }
        if (!account.getUserId().equals(userId)){
            throw new FFMException(NOT_YOUR_ACCOUNT);
        }
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
        for (int i = 0; i < acounts.size(); i++) {
            acounts.get(i).setFatherName(
                    AccountTypeEnum.getType(StringUtils.praseLong(acounts.get(i).getTopLevelId())));
        }
        return acounts;
    }

    @Override
    public List<DefiniteAccount> getByConditions(long userId , ConditionForm conditionForm) throws FFMException{
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria().andUserIdBetween(userId,userId);
        example.setOrderByClause("gmt_create desc");
//        example.setLimit(20);
//        example.setOffset(20*(StringUtils.praseInteger(conditionForm.getPageNum())-1));
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
            criteria.andGmtCreateLessThan(StringUtils.plusDate(conditionForm.getToDate()));
        }
        if (!(StringUtils.isEmpty(conditionForm.getTypes()))){
            List<String> typeStrs = StringUtils.praseList(conditionForm.getTypes());
            List<Long> types = new ArrayList<>();
            for (String type: typeStrs) {
                types.add(StringUtils.praseLong(type));
            }
            criteria.andTypeIn(types);
        }
        List<Account> accounts = accountMapper.selectByExample(example);
        List<DefiniteAccount> accountList = new ArrayList<DefiniteAccount>();
        accounts.forEach(a->{
            DefiniteAccount definiteAccount = new DefiniteAccount();
            BeanUtils.copyProperties(a,definiteAccount);
            AccountType type = accountTypeMapper.selectByPrimaryKey(a.getType());
            definiteAccount.setFatherName(AccountTypeEnum.getType(type.getTopLeve()));
            definiteAccount.setTopLevelId(String.valueOf(type.getTopLeve()));
            definiteAccount.setTypeName(type.getTypeName());
            accountList.add(definiteAccount);
        });
//
//        Optional<List<DefiniteAccount>> accountList = Optional.ofNullable(accountMapper.selectDefiniteAccount(example));
//        List<DefiniteAccount> acounts = accountList.orElse(new ArrayList<DefiniteAccount>());
//        for (int i = 0; i < acounts.size(); i++) {
//            acounts.get(i).setFatherName(
//                    AccountTypeEnum.getType(StringUtils.praseLong(acounts.get(i).getTopLevelId())));
//        }
        return accountList;
    }

    @Override
    public Long countByConditions(long userId, ConditionForm conditionForm) throws FFMException {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("gmt_create desc");
        example.setLimit(20);
        example.setOffset(20*(StringUtils.praseInteger(conditionForm.getPageNum())-1));
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
        return accountMapper.countByExample(example)/20;
    }

    @Override
    public int getCountByConditions(long userId , ConditionForm conditionForm) throws FFMException{
        List<DefiniteAccount> accounts = getByConditions(userId,conditionForm);
        return accounts.size();
    }

    @Override
    public JSONObject getIndexAccount(long userId) throws FFMException{
        JSONObject jsonObject = new JSONObject();
        LocalDate time = LocalDate.now();
        //年月周日信息
        jsonObject.put("today", getSpendingAndIncomeByTime(time.atStartOfDay().toString(),time.plusDays(1).toString(),userId));
        jsonObject.put("thisWeek", getSpendingAndIncomeByTime(time.with(DayOfWeek.MONDAY).toString(),time.with(DayOfWeek.SUNDAY).toString(),userId));
        jsonObject.put("thisMonth", getSpendingAndIncomeByTime(time.with(TemporalAdjusters.firstDayOfMonth()).toString(),time.with(TemporalAdjusters.lastDayOfMonth()).toString(),userId));
        jsonObject.put("thisYear", getSpendingAndIncomeByTime(time.with(TemporalAdjusters.firstDayOfYear()).toString(),time.with(TemporalAdjusters.lastDayOfYear()).toString(),userId));


        jsonObject.put("everyMonthBill",userService.getMonthBill(userId,time.getYear()));

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria();
        accountExample.getOredCriteria().get(0).andGmtCreateGreaterThanOrEqualTo(
                Date.from(time.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        accountExample.getOredCriteria().get(0).andGmtCreateLessThan(
                Date.from(time.plusMonths(1L).with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        JSONArray monthAccountJson = new JSONArray();
        Optional<List<Account>> monthAccounts = Optional.ofNullable(accountMapper.selectGroupAccounts(time.with(TemporalAdjusters.firstDayOfMonth()).toString(),time.with(TemporalAdjusters.lastDayOfMonth()).toString(),userId));
        for (Account a: monthAccounts.orElse(new ArrayList<>())) {
            JSONObject groupAccounts = new JSONObject(3);
            groupAccounts.put("typeId", a.getType());
            groupAccounts.put("type", AccountTypeEnum.getType(a.getType()));
            if (a.getIncome() > 0){
                groupAccounts.put("income",a.getIncome());
            }else {
                groupAccounts.put("spending",a.getSpending());
            }
            monthAccountJson.add(groupAccounts);
        }
        jsonObject.put("thisMonthAccounts",monthAccountJson);

        return jsonObject;
    }


    public JSONObject getSpendingAndIncomeByTime(String fromDate, String toDate, Long userId){
        Optional<Account> account = Optional.ofNullable(accountMapper.selectByDate(fromDate,toDate,userId));
        JSONObject json = new JSONObject();
        return new JSONObject(new HashMap<String,Object>(){
            {
                put("income",account.map(Account::getIncome).orElse(0L));
                put("spending",account.map(Account::getSpending).orElse(0L));
            }
        });
    }
}
