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

    @Override
    public void updateUserAccount(Long userId) {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Account> accounts = accountMapper.selectByExample(example);
        long allIncome = accounts.stream().map(Account::getIncome).reduce((long) 0,Long::sum);
        long allSpending = accounts.stream().map(Account::getSpending).reduce((long) 0,Long::sum);
        long balance = accounts.stream().map(Account::getAccountNum).reduce((long) 0,Long::sum);

        User user = userMapper.selectByPrimaryKey(userId);
        user.setAllIncome(allIncome);
        user.setAllSpending(allSpending);
        user.setBalance(balance);

        userMapper.updateByPrimaryKeySelective(user);

    }

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

    @Override
    public void insertMonthAccount(Account account) {
        Long month =  getMonth(account.getGmtCreate());
        AccountMonthExample example = new AccountMonthExample();
        AccountMonthExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(account.getUserId());
        criteria.andMonthEqualTo(month);
        List<AccountMonth> accountMonthList = accountMonthMapper.selectByExample(example);
        if ((null == accountMonthList) || (0 == accountMonthList.size())){
            AccountMonth newAccountMonth = new AccountMonth();
            newAccountMonth.setBalance(account.getIncome()-account.getSpending());
            newAccountMonth.setIncome(account.getIncome());
            newAccountMonth.setSpend(account.getSpending());
            newAccountMonth.setUserId(account.getUserId());
            newAccountMonth.setMonth(month);
            accountMonthMapper.insert(newAccountMonth);
        }else {
            AccountMonth accountMonth = accountMonthList.get(0);
            accountMonth.setSpend(accountMonth.getSpend() + account.getSpending());
            accountMonth.setIncome(accountMonth.getIncome() + account.getIncome());
            accountMonth.setBalance(accountMonth.getIncome() - accountMonth.getSpend());
            accountMonthMapper.updateByPrimaryKey(accountMonth);
        }
    }

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


}