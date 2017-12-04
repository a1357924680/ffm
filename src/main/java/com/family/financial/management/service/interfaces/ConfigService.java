package com.family.financial.management.service.interfaces;

import com.family.financial.management.exception.FFMException;

import java.util.List;

/**
 * Created by zhangyiping on 2017/12/3.
 */
public interface ConfigService {
    void createConfig( String name,Long typeId, Long userId, Boolean isSpending, Long money, String time, String description) throws FFMException;

    void updateConfig(Long id, String name,Long typeId, Long userId, Boolean isSpending, Long money, String time, String description) throws FFMException;

    List getConfigs(Long userId) throws FFMException;

    void deleteConfig(Long id,Long userId) throws FFMException;



}
