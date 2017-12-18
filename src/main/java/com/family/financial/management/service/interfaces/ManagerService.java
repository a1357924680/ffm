package com.family.financial.management.service.interfaces;

import com.family.financial.management.dao.entity.Message;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.model.SystemType;

import java.util.List;

/**
 * Created by zhangyiping on 2017/12/14.
 */
public interface ManagerService {
    List<User> getAllUser()throws FFMException;
    int getTypePage()throws FFMException;
    List<SystemType> getSystemTypes(int pageNum)throws FFMException;
    void addType(long topType ,String typeName)throws FFMException;
}
