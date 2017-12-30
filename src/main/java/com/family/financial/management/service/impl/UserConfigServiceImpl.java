package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.UserConfig;
import com.family.financial.management.dao.entity.UserConfigExample;
import com.family.financial.management.dao.mapper.UserConfigMapper;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.service.interfaces.UserConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangyiping on 2017/12/30.
 */
@Service
public class UserConfigServiceImpl implements UserConfigService {

    @Resource
    private UserConfigMapper configMapper;

    @Override
    public UserConfig getUserConfig(Long userId) throws FFMException {
        UserConfigExample example = new UserConfigExample();
        example.createCriteria().andUserIdBetween(userId,userId);
        return configMapper.selectByExample(example).get(0);
    }

    @Override
    public void insertUserConfig(Long userId, int type1, int type2) throws FFMException {
        UserConfig userConfig = new UserConfig();
        userConfig.setAllowType1(type1);
        userConfig.setAllowType2(type2);
        userConfig.setUserId(userId);
        configMapper.insertSelective(userConfig);
    }

    @Override
    public void updateUserConfig(Long userId, Integer type1, Integer type2) throws FFMException {
        UserConfigExample example = new UserConfigExample();
        example.createCriteria().andUserIdBetween(userId,userId);
        UserConfig userConfig = configMapper.selectByExample(example).get(0);
        if (type1!=null){
            userConfig.setAllowType1(type1);
        }
        if (type2!=null){
            userConfig.setAllowType2(type2);
        }
        configMapper.updateByPrimaryKeySelective(userConfig);
    }
}
