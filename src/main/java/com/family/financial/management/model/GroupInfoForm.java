package com.family.financial.management.model;

import java.util.List;

/**
 * Created by zhangyiping on 2017/9/20.
 */
public class GroupInfoForm {

    private Long groupId;

    private String groupName;

    private Long allIncome;

    private Long allSpending;

    private Long balance;

    private Long managerId;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    private List<UserInfoForm> userInfoForms;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(Long allIncome) {
        this.allIncome = allIncome;
    }

    public Long getAllSpending() {
        return allSpending;
    }

    public void setAllSpending(Long allSpending) {
        this.allSpending = allSpending;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public List<UserInfoForm> getUserInfoForms() {
        return userInfoForms;
    }

    public void setUserInfoForms(List<UserInfoForm> userInfoForms) {
        this.userInfoForms = userInfoForms;
    }
}
