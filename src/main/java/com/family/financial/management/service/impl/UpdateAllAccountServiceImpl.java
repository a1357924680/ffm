package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.*;
import com.family.financial.management.dao.mapper.*;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountForm;
import com.family.financial.management.service.interfaces.AccountService;
import com.family.financial.management.service.interfaces.UpdateAllAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.family.financial.management.emun.AccountConfig.*;

/**
 * Created by zhangyiping on 2017/10/14.
 */
@Service
public class UpdateAllAccountServiceImpl implements UpdateAllAccountService {


    @Autowired
    private AccountService accountService;

    @Resource
    private GroupsMapper groupsMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountMonthMapper accountMonthMapper;

    @Resource
    private AccountConfigMapper configMapper;

    @Resource
    private ConfigLogMapper logMapper;


    @Override
    public void updateAllAccount(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        updateUserAccount(user.getId());
        if (user.getGroupId() != 0) {
            updateGroupAccount(user.getGroupId());
        }
    }

    /**
     * 用户资金变化后，更新用户
     *
     * @param userId
     */
    @Override
    public void updateUserAccount(Long userId) {
        AccountExample example = new AccountExample();
        AccountExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Account> accounts = accountMapper.selectByExample(example);
        long allIncome = accounts.stream().map(Account::getIncome).reduce((long) 0, Long::sum);
        long allSpending = accounts.stream().map(Account::getSpending).reduce((long) 0, Long::sum);
        long balance = allIncome - allSpending;

        User user = userMapper.selectByPrimaryKey(userId);
        user.setAllIncome(allIncome);
        user.setAllSpending(allSpending);
        user.setBalance(balance);

        userMapper.updateByPrimaryKeySelective(user);

    }

    /**
     * 家庭组信息变化后，更新家庭组
     *
     * @param groupId
     */
    @Override
    public void updateGroupAccount(Long groupId) {
        Groups group = groupsMapper.selectByPrimaryKey(groupId);
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andGroupIdEqualTo(groupId);
        List<User> users = userMapper.selectByExample(userExample);

        long allIncome = users.stream().map(User::getAllIncome).reduce((long) 0, Long::sum);
        long allSpending = users.stream().map(User::getAllSpending).reduce((long) 0, Long::sum);
        long balance = users.stream().map(User::getBalance).reduce((long) 0, Long::sum);

        group.setAllIncome(allIncome);
        group.setAllSpending(allSpending);
        group.setBalance(balance);

        groupsMapper.updateByPrimaryKeySelective(group);
    }

    /**
     * 更新本月信息
     *
     * @param account
     */
    @Override
    public void insertMonthAccount(Account account) {
        Long month = getMonth(account.getGmtCreate());
        AccountMonthExample example = new AccountMonthExample();
        AccountMonthExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(account.getUserId());
        criteria.andMonthEqualTo(month);
        List<AccountMonth> accountMonthList = accountMonthMapper.selectByExample(example);
        if ((null == accountMonthList) || (0 == accountMonthList.size())) {
            addYearBill(account.getUserId(), Long.parseLong(new SimpleDateFormat("YYYY").format(account.getGmtCreate()).toString()));
        } else {
            AccountMonth accountMonth = accountMonthList.get(0);
            accountMonth.setSpend(accountMonth.getSpend() + account.getSpending());
            accountMonth.setIncome(accountMonth.getIncome() + account.getIncome());
            accountMonth.setBalance(accountMonth.getIncome() - accountMonth.getSpend());
            accountMonthMapper.updateByPrimaryKey(accountMonth);
        }
    }

    /**
     * 删除账单后，更新本月信息
     *
     * @param account
     */
    @Override
    public void deleteMonthAccount(Account account) {
        Long month = getMonth(account.getGmtCreate());
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

    long getMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String dateString = formatter.format(date);
        return Long.parseLong(dateString);
    }

    /**
     * 插入每月信息
     *
     * @param userId
     * @param year
     */
    @Override
    public void addYearBill(long userId, long year) {
        long date = year * 100;
        for (int i = 1; i < 13; i++) {
            AccountMonth accountMonth = new AccountMonth();
            accountMonth.setUserId(userId);
            accountMonth.setSpend(0L);
            accountMonth.setBalance(0L);
            accountMonth.setIncome(0L);
            accountMonth.setMonth(date + i);
            accountMonthMapper.insertSelective(accountMonth);
        }
    }

    @Override
    public void checkConfig(long userId) {
        AccountConfigExample example = new AccountConfigExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<AccountConfig> configs = configMapper.selectByExample(example);
        for (AccountConfig a :
                configs) {
            boolean done = checkDone(a);
            if (!done) {
                AccountForm accountForm = new AccountForm();
                accountForm.setDescription(a.getDescription());
                accountForm.setGmtCreate(new Date());
                accountForm.setType(a.getType());
                if (a.getIsSpending()) {
                    accountForm.setSpending(a.getMoney());
                    accountForm.setIncome(0L);
                    accountForm.setAccountNum(0 - a.getMoney());
                } else {
                    accountForm.setSpending(0L);
                    accountForm.setIncome(a.getMoney());
                    accountForm.setAccountNum(a.getMoney());
                }
                try {
                    accountService.addAccount(userId, accountForm);
                } catch (FFMException e) {
                    e.printStackTrace();
                }
                ConfigLog log = new ConfigLog();
                log.setTime(new Date());
                log.setConfigId(a.getId());
                logMapper.insert(log);
            }
        }

    }

    private boolean checkDone(AccountConfig a) {
        ConfigLogExample example = new ConfigLogExample();
        example.createCriteria();
        LocalDate date = LocalDate.now();
        if (a.getTime().equals(TYPE1.getType())) {
            example.getOredCriteria().get(0).andTimeGreaterThan(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            example.getOredCriteria().get(0).andTimeLessThanOrEqualTo(Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            List<ConfigLog> configLog = logMapper.selectByExample(example);
            if (configLog == null || configLog.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (a.getTime().equals(TYPE2.getType())) {
            if ((date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)) {
                return true;
            }
            example.getOredCriteria().get(0).andTimeGreaterThan(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            example.getOredCriteria().get(0).andTimeLessThanOrEqualTo(Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            List<ConfigLog> configLog = logMapper.selectByExample(example);
            if (configLog == null || configLog.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (a.getTime().equals(TYPE3.getType())) {
            if (!(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7)) {
                return true;
            }
            example.getOredCriteria().get(0).andTimeGreaterThan(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            example.getOredCriteria().get(0).andTimeLessThanOrEqualTo(Date.from(date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            List<ConfigLog> configLog = logMapper.selectByExample(example);
            if (configLog == null || configLog.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (a.getTime().equals(TYPE4.getType())) {
            example.getOredCriteria().get(0).andTimeGreaterThan(
                    Date.from(date.minusDays(date.getDayOfWeek().getValue() - 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            example.getOredCriteria().get(0).andTimeLessThanOrEqualTo(
                    Date.from(date.minusDays(date.getDayOfWeek().getValue() - 1).plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            List<ConfigLog> configLog = logMapper.selectByExample(example);
            if (configLog == null || configLog.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        if (a.getTime().equals(TYPE5.getType())) {
            example.getOredCriteria().get(0).andTimeGreaterThan(
                    Date.from(date.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            example.getOredCriteria().get(0).andTimeLessThanOrEqualTo(
                    Date.from(date.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            List<ConfigLog> configLog = logMapper.selectByExample(example);
            if (configLog == null || configLog.size() == 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }
}

