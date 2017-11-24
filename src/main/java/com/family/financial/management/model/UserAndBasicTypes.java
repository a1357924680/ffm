package com.family.financial.management.model;

import com.family.financial.management.dao.entity.AccountType;

/**
 * Created by zhangyiping on 2017/11/19.
 */
public class UserAndBasicTypes extends AccountType{

    private String topName;

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }
}
