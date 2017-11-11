package com.family.financial.management.model;

import java.util.Date;

/**
 * Created by zhangyiping on 2017/10/14.
 */
public class DefiniteAccount {
    private Long id;

    private Long type;

    private Long income;

    private Long spending;

    private Long accountNum;

    private Date gmtCreate;

    private String description;

    private String imgUrl;

    private String topLevelId;

    private String typeName;


    public String getTopLevelId() {
        return topLevelId;
    }

    public void setTopLevelId(String topLevelId) {
        this.topLevelId = topLevelId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getSpending() {
        return spending;
    }

    public void setSpending(Long spending) {
        this.spending = spending;
    }

    public Long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Long accountNum) {
        this.accountNum = accountNum;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
