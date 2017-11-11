package com.family.financial.management.dao.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Groups implements Serializable {
    private Long groupId;

    private String groupName;

    private String groupMembers;

    private Long extNo1;

    private String extNo2;

    private Long groupManager;

    private Long allIncome;

    private Long allSpending;

    private Long balance;

    private static final long serialVersionUID = 1L;

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

    public String getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(String groupMembers) {
        this.groupMembers = groupMembers;
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

    public Long getGroupManager() {
        return groupManager;
    }

    public void setGroupManager(Long groupManager) {
        this.groupManager = groupManager;
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
        Groups other = (Groups) that;
        return (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getGroupName() == null ? other.getGroupName() == null : this.getGroupName().equals(other.getGroupName()))
            && (this.getGroupMembers() == null ? other.getGroupMembers() == null : this.getGroupMembers().equals(other.getGroupMembers()))
            && (this.getExtNo1() == null ? other.getExtNo1() == null : this.getExtNo1().equals(other.getExtNo1()))
            && (this.getExtNo2() == null ? other.getExtNo2() == null : this.getExtNo2().equals(other.getExtNo2()))
            && (this.getGroupManager() == null ? other.getGroupManager() == null : this.getGroupManager().equals(other.getGroupManager()))
            && (this.getAllIncome() == null ? other.getAllIncome() == null : this.getAllIncome().equals(other.getAllIncome()))
            && (this.getAllSpending() == null ? other.getAllSpending() == null : this.getAllSpending().equals(other.getAllSpending()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getGroupMembers() == null) ? 0 : getGroupMembers().hashCode());
        result = prime * result + ((getExtNo1() == null) ? 0 : getExtNo1().hashCode());
        result = prime * result + ((getExtNo2() == null) ? 0 : getExtNo2().hashCode());
        result = prime * result + ((getGroupManager() == null) ? 0 : getGroupManager().hashCode());
        result = prime * result + ((getAllIncome() == null) ? 0 : getAllIncome().hashCode());
        result = prime * result + ((getAllSpending() == null) ? 0 : getAllSpending().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupId=").append(groupId);
        sb.append(", groupName=").append(groupName);
        sb.append(", groupMembers=").append(groupMembers);
        sb.append(", extNo1=").append(extNo1);
        sb.append(", extNo2=").append(extNo2);
        sb.append(", groupManager=").append(groupManager);
        sb.append(", allIncome=").append(allIncome);
        sb.append(", allSpending=").append(allSpending);
        sb.append(", balance=").append(balance);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}