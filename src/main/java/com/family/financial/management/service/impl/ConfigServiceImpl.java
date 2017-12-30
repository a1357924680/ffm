package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.AccountConfig;
import com.family.financial.management.dao.entity.AccountConfigExample;
import com.family.financial.management.dao.entity.AccountType;
import com.family.financial.management.dao.mapper.AccountConfigMapper;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountNameConfig;
import com.family.financial.management.model.UserAndBasicTypes;
import com.family.financial.management.service.interfaces.AccountTypeService;
import com.family.financial.management.service.interfaces.ConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyiping on 2017/12/3.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private AccountConfigMapper configMapper;
    @Resource
    private AccountTypeMapper accountTypeMapper;
    @Autowired
    private AccountTypeService typeService;
    @Override
    public void createConfig( String name,Long typeId, Long userId, Boolean isSpending, Long money, String time, String description) throws FFMException {
        AccountType type = accountTypeMapper.selectByPrimaryKey(typeId);
        if (type == null){
            throw new FFMException(1084844,"未找到该类型");
        }
        if (!(type.getIsBasic()||userId.equals(type.getUserId()))){
            throw new FFMException(103234,"非该用户类型");
        }
        AccountConfig config = getConfig(name,typeId,userId,isSpending,money,time,description);
        configMapper.insertSelective(config);
    }

    private AccountConfig getConfig(String name, Long typeId,Long userId, Boolean isSpending, Long money, String time, String description) {
        AccountConfig accountConfig = new AccountConfig();
        accountConfig.setDescription(description);
        accountConfig.setType(typeId);
        accountConfig.setTime(time);
        accountConfig.setIsSpending(isSpending);
        accountConfig.setMoney(money);
        accountConfig.setName(name);
        accountConfig.setUserId(userId);
        accountConfig.setGmtCreate(new Date());
        return accountConfig;
    }

    @Override
    public void updateConfig(Long id, String name, Long typeId,Long userId, Boolean isSpending, Long money, String time, String description) throws FFMException {
        AccountConfig accountConfig = configMapper.selectByPrimaryKey(id);
        if (accountConfig == null){
            throw new FFMException(1003432,"无此配置项");
        }
        if (!accountConfig.getUserId().equals(userId)){
            throw new FFMException(1003432,"无法改变他人配置");
        }

        AccountType type = accountTypeMapper.selectByPrimaryKey(typeId);
        if (type == null){
            throw new FFMException(1084844,"未找到该类型");
        }
        if (!(type.getIsBasic()||userId.equals(type.getUserId()))){
            throw new FFMException(103234,"非该用户类型");
        }

        AccountConfig config = getConfig(name,typeId,userId,isSpending,money,time,description);
        config.setId(id);
        configMapper.updateByPrimaryKeySelective(config);
    }

    @Override
    public List getConfigs(Long userId) throws FFMException {
        AccountConfigExample example = new AccountConfigExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<AccountConfig> accountConfigList = configMapper.selectByExample(example);
        List<UserAndBasicTypes> typeList = typeService.getAllAccount(userId);
        List list = new ArrayList();
        for (AccountConfig a:
             accountConfigList) {
            AccountNameConfig nameConfig = new AccountNameConfig();
            BeanUtils.copyProperties(a,nameConfig);
            nameConfig.setTimeName(com.family.financial.management.emun.AccountConfig.valueOf("TYPE"+a.getTime()).getName());
            String typeName = typeList.stream().filter(t->t.getId().equals(a.getType())).findFirst().get().getTypeName();
            nameConfig.setTypeName(typeName);
            list.add(nameConfig);
        }
        return list;
    }

    @Override
    public void deleteConfig(Long id,Long userId) throws FFMException {
        AccountConfig accountConfig = configMapper.selectByPrimaryKey(id);
        if (accountConfig == null){
            throw new FFMException(1003432,"无此配置项");
        }
        if (!accountConfig.getUserId().equals(userId)){
            throw new FFMException(1003432,"无法改变他人配置");
        }
        configMapper.deleteByPrimaryKey(id);
    }
}
