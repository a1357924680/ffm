package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.*;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.dao.mapper.AnswerMapper;
import com.family.financial.management.dao.mapper.MessageMapper;
import com.family.financial.management.dao.mapper.SysUserMapper;
import com.family.financial.management.emun.AccountTypeEnum;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.model.SystemType;
import com.family.financial.management.service.interfaces.ManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyiping on 2017/12/18.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource
    private AccountTypeMapper accountTypeMapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private AnswerMapper answerMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public User getUser(String userId,String password) throws FFMException {
        SysUser user = sysUserMapper.selectByPrimaryKey(userId);
        if (user == null){
            throw new FFMException(432213,"用户不存在");
        }
        if (!password.equals(user.getPassword())){
            throw new FFMException(432213,"账号或密码错误");
        }
        User user1 = new User();
        user1.setUserId(userId);
        user1.setPassword(password);
        user1.setUserName("管理员");
        return user1;
    }

    @Override
    public void addUser(String userId, String password) throws FFMException {
        if (sysUserMapper.selectByPrimaryKey(userId)!=null){
            throw new FFMException(689329,"该管理员已存在");
        }
        SysUser user = new SysUser();
        user.setGmtCreate(new Date());
        user.setPassword(password);
        user.setUserId(userId);
        sysUserMapper.insertSelective(user);
    }

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
        example.setOffset((pageNum-1)*20);
        example.setLimit(20);
        example.setOrderByClause("id");
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
    public List<SystemType> getConditionTypes(int condition)throws FFMException{
        AccountTypeExample example = new AccountTypeExample();
        example.createCriteria().andTopLeveBetween((long)condition,(long)condition).andIsBasicBetween(true,true);
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

    @Override
    public void answerMessage(String message, Long id) throws FFMException {
        Message m = messageMapper.selectByPrimaryKey(id);
        if (m == null){
            throw new FFMException(1672374,"无此信息");
        }
        m.setStatus((long) 1);
        Answer answer = new Answer();
        answer.setUserId(m.getUserId());
        answer.setMessage(message);
        answer.setGmtCreate(new Date());
        messageMapper.updateByPrimaryKey(m);
        answerMapper.insertSelective(answer);


    }
}
