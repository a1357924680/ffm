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
    public UserConfig getUserConfig(Long userId,Long toUser) throws FFMException {
        UserConfigExample example = new UserConfigExample();
        example.createCriteria().andUserIdBetween(userId,userId).andToUserIdBetween(toUser,toUser);
        return configMapper.selectByExample(example).get(0);
    }

    @Override
    public void insertUserConfig(Long userId, Long toUser,int type1, int type2) throws FFMException {
        UserConfig userConfig = new UserConfig();
        userConfig.setUserId(userId);
        userConfig.setToUserId(toUser);
        userConfig.setAllowType1(type1);
        userConfig.setAllowType2(type2);
        userConfig.setUserId(userId);
        configMapper.insertSelective(userConfig);
    }

    @Override
    public void updateUserConfig(Long userId,Long toUser, Integer type1, Integer type2) throws FFMException {
        UserConfigExample example = new UserConfigExample();
        example.createCriteria().andUserIdBetween(userId,userId).andToUserIdBetween(toUser,toUser);
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
