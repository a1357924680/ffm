package com.family.financial.management.model;

/**
 * Created by zhangyiping on 2017/9/21.
 */
public class AccountTypeForm {
    /**
     * 大类型
     */
    private Long topLeve;


    private String typeName;

    /**
     * 用户创建的记录用户的id
     */
    private Long userId;

    public Long getTopLeve() {
        return topLeve;
    }

    public void setTopLeve(Long topLeve) {
        this.topLeve = topLeve;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
