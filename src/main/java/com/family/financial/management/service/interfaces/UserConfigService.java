package com.family.financial.management.service.interfaces;

import com.family.financial.management.dao.entity.UserConfig;
import com.family.financial.management.exception.FFMException;

/**
 * Created by zhangyiping on 2017/12/30.
 */
public interface UserConfigService {
    UserConfig getUserConfig(Long userId) throws FFMException;
    void insertUserConfig(Long userId,int type1,int type2) throws FFMException;
    void updateUserConfig(Long userId,Integer type1,Integer type2) throws FFMException;

}
