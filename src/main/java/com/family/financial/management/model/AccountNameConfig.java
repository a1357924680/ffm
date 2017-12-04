package com.family.financial.management.model;

import com.family.financial.management.dao.entity.AccountConfig;

/**
 * Created by zhangyiping on 2017/12/3.
 */
public class AccountNameConfig extends AccountConfig{
    private String typeName;

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    private String timeName;
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
