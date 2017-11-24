package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.*;
import com.family.financial.management.dao.mapper.AccountMapper;
import com.family.financial.management.dao.mapper.AccountMonthMapper;
import com.family.financial.management.dao.mapper.GroupsMapper;
import com.family.financial.management.dao.mapper.UserMapper;
import com.family.financial.management.service.interfaces.UpdateAllAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by zhangyiping on 2017/10/14.
 */
@Service
public class UpdateAllAccountServiceImpl implements UpdateAllAccountService {


    @Resource
    private GroupsMapper groupsMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountMonthMapper accountMonthMapper;
    @Override
    public void updateAllAccount(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        updateUserAccount(user.getId());
        if (user.getGroupId() != 0){
            updateGroupAccount(user.getGroupId());
        }
    }

    /**
     * 用户资金变化后，更新用户
     * @param userId
     */
    @Override
    public void updateUserAccount(Long userId) {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Account> accounts = accountMapper.selectByExample(example);
        long allIncome = accounts.stream().map(Account::getIncome).reduce((long) 0,Long::sum);
        long allSpending = accounts.stream().map(Account::getSpending).reduce((long) 0,Long::sum);
        long balance = allIncome-allSpending;

        User user = userMapper.selectByPrimaryKey(userId);
        user.setAllIncome(allIncome);
        user.setAllSpending(allSpending);
        user.setBalance(balance);

        userMapper.updateByPrimaryKeySelective(user);

    }

    /**
     * 家庭组信息变化后，更新家庭组
     * @param groupId
     */
    @Override
    public void updateGroupAccount(Long groupId) {
        Groups group = groupsMapper.selectByPrimaryKey(groupId);
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andGroupIdEqualTo(groupId);
        List<User> users = userMapper.selectByExample(userExample);

        long allIncome = users.stream().map(User::getAllIncome).reduce((long) 0,Long::sum);
        long allSpending = users.stream().map(User::getAllSpending).reduce((long) 0,Long::sum);
        long balance = users.stream().map(User::getBalance).reduce((long) 0,Long::sum);

        group.setAllIncome(allIncome);
        group.setAllSpending(allSpending);
        group.setBalance(balance);

        groupsMapper.updateByPrimaryKeySelective(group);
    }

    /**
     * 更新本月信息
     * @param account
     */
    @Override
    public void insertMonthAccount(Account account) {
        Long month =  getMonth(account.getGmtCreate());
        AccountMonthExample example = new AccountMonthExample();
        AccountMonthExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(account.getUserId());
        criteria.andMonthEqualTo(month);
        List<AccountMonth> accountMonthList = accountMonthMapper.selectByExample(example);
        if ((null == accountMonthList) || (0 == accountMonthList.size())){
            addYearBill(account.getUserId(),Long.parseLong(new SimpleDateFormat("YYYY").format(account.getGmtCreate()).toString()));
        }else {
            AccountMonth accountMonth = accountMonthList.get(0);
            accountMonth.setSpend(accountMonth.getSpend() + account.getSpending());
            accountMonth.setIncome(accountMonth.getIncome() + account.getIncome());
            accountMonth.setBalance(accountMonth.getIncome() - accountMonth.getSpend());
            accountMonthMapper.updateByPrimaryKey(accountMonth);
        }
    }

    /**
     * 删除账单后，更新本月信息
     * @param account
     */
    @Override
    public void deleteMonthAccount(Account account) {
        Long month =  getMonth(account.getGmtCreate());
        AccountMonthExample example = new AccountMonthExample();
        AccountMonthExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(account.getUserId());
        criteria.andMonthEqualTo(month);
        List<AccountMonth> accountMonthList = accountMonthMapper.selectByExample(example);
        AccountMonth accountMonth = accountMonthList.get(0);
        accountMonth.setSpend(accountMonth.getSpend() + account.getSpending());
        accountMonth.setIncome(accountMonth.getIncome() + account.getIncome());
        accountMonth.setBalance(accountMonth.getIncome() - accountMonth.getSpend());
        accountMonthMapper.updateByPrimaryKey(accountMonth);
    }

    long getMonth(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String dateString = formatter.format(date);
        return Long.parseLong(dateString);
    }

    /**
     * 插入每月信息
     * @param userId
     * @param year
     */
    @Override
    public void addYearBill(long userId,long year){
        long date = year * 100;
        for (int i = 1; i < 13; i++) {
            AccountMonth accountMonth = new AccountMonth();
            accountMonth.setUserId(userId);
            accountMonth.setSpend(0L);
            accountMonth.setBalance(0L);
            accountMonth.setIncome(0L);
            accountMonth.setMonth(date+i);
            accountMonthMapper.insertSelective(accountMonth);
        }
    }


}