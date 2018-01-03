package com.family.financial.management.model;

/**
 * Created by zhangyiping on 2017/9/20.
 */
public class UserInfoForm {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 账号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机
     */
    private String mobile;

    private Long sex;

    private String photo;

    private Long allSpending;

    private Long allIncome;

    private Long balance;

    private Integer isManager;

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getAllSpending() {
        return allSpending;
    }

    public void setAllSpending(Long allSpending) {
        this.allSpending = allSpending;
    }

    public Long getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(Long allIncome) {
        this.allIncome = allIncome;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
