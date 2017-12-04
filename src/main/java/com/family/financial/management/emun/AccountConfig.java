package com.family.financial.management.emun;

import com.family.financial.management.exception.FFMException;

/**
 * Created by zhangyiping on 2017/12/3.
 */
public enum  AccountConfig {

    TYPE1("1","每天"),
    TYPE2("2","工作日"),
    TYPE3("3","周末"),
    TYPE4("4","每周"),
    TYPE5("5","每月")
    ;

    private String type;
    private String name;

    AccountConfig(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
