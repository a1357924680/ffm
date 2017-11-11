package com.family.financial.management.service.interfaces;

import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;

/**
 * Created by zhangyiping on 2017/9/21.
 */
public interface AccountTypeService {
    void createAccountType(AccountTypeForm accountTypeForm) throws FFMException;
}
