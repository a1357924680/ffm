package com.family.financial.management.dao.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class User implements Serializable {
    private Long id;

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
    private Long mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 组号
     */
    private Long groupId;

    /**
     * 管理员
     */
    private Boolean isManager;

    /**
     * 余额
     */
    private Long balance;

    /**
     * 总支出
     */
    private Long allSpending;

    /**
     * 总收入
     */
    private Long allIncome;

    /**
     * 男0，女1
     */
    private Long sex;

    /**
     * 头像
     */
    private String photo;

    private Long extNo1;

    private String extNo2;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
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

    public Long getExtNo1() {
        return extNo1;
    }

    public void setExtNo1(Long extNo1) {
        this.extNo1 = extNo1;
    }

    public String getExtNo2() {
        return extNo2;
    }

    public void setExtNo2(String extNo2) {
        this.extNo2 = extNo2;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getIsManager() == null ? other.getIsManager() == null : this.getIsManager().equals(other.getIsManager()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getAllSpending() == null ? other.getAllSpending() == null : this.getAllSpending().equals(other.getAllSpending()))
            && (this.getAllIncome() == null ? other.getAllIncome() == null : this.getAllIncome().equals(other.getAllIncome()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
            && (this.getExtNo1() == null ? other.getExtNo1() == null : this.getExtNo1().equals(other.getExtNo1()))
            && (this.getExtNo2() == null ? other.getExtNo2() == null : this.getExtNo2().equals(other.getExtNo2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getIsManager() == null) ? 0 : getIsManager().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getAllSpending() == null) ? 0 : getAllSpending().hashCode());
        result = prime * result + ((getAllIncome() == null) ? 0 : getAllIncome().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getExtNo1() == null) ? 0 : getExtNo1().hashCode());
        result = prime * result + ((getExtNo2() == null) ? 0 : getExtNo2().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", mobile=").append(mobile);
        sb.append(", password=").append(password);
        sb.append(", groupId=").append(groupId);
        sb.append(", isManager=").append(isManager);
        sb.append(", balance=").append(balance);
        sb.append(", allSpending=").append(allSpending);
        sb.append(", allIncome=").append(allIncome);
        sb.append(", sex=").append(sex);
        sb.append(", photo=").append(photo);
        sb.append(", extNo1=").append(extNo1);
        sb.append(", extNo2=").append(extNo2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}