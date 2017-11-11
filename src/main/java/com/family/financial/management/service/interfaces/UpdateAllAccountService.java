package com.family.financial.management.service.interfaces;

import com.family.financial.management.dao.entity.Account;

/**
 * Created by zhangyiping on 2017/10/14.
 */
public interface UpdateAllAccountService {
    void updateAllAccount(Long userId);
    void updateUserAccount(Long userId);
    void updateGroupAccount(Long groupId);
    void insertMonthAccount(Account account);
    void deleteMonthAccount(Account account);
}
