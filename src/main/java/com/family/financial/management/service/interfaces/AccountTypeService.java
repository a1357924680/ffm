package com.family.financial.management.service.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.family.financial.management.dao.entity.AccountType;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.model.UserAndBasicTypes;

import java.util.List;

/**
 * Created by zhangyiping on 2017/9/21.
 */
public interface AccountTypeService {
    AccountType createAccountType(AccountTypeForm accountTypeForm) throws FFMException;
    List<UserAndBasicTypes> getAllAccount(Long userId) throws FFMException;
    void updateAccountType(Long typeId ,AccountTypeForm accountTypeForm) throws FFMException;
    void deleteAccountType(Long typeId,Long userId) throws FFMException;
}
