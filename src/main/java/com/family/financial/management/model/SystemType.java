package com.family.financial.management.model;

import com.family.financial.management.dao.entity.AccountType;

/**
 * Created by zhangyiping on 2017/12/18.
 */
public class SystemType extends AccountType{
    private String basicTypeName;

    public String getBasicTypeName() {
        return basicTypeName;
    }

    public void setBasicTypeName(String basicTypeName) {
        this.basicTypeName = basicTypeName;
    }
}
