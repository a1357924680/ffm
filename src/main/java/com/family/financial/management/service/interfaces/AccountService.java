package com.family.financial.management.service.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountForm;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.DefiniteAccount;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangyiping on 2017/10/14.
 */
public interface AccountService {
    void addAccount(long userId , AccountForm accountForm) throws FFMException;
    void updateAccount(long userId , AccountForm accountForm) throws FFMException;
    void deleteAccount(long userId , long id) throws FFMException;
    List<DefiniteAccount> getAccountList(long userId , Date fromDate, Date toDate, Integer limit, Integer offset ) throws FFMException;
    List<DefiniteAccount> getByConditions(long userId , ConditionForm conditionForm) throws FFMException;
    int getCountByConditions(long userId , ConditionForm conditionForm) throws FFMException;
    JSONObject getIndexAccount(long userId) throws FFMException;
}
