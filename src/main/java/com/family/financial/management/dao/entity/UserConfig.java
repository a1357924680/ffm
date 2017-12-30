package com.family.financial.management.dao.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class UserConfig implements Serializable {
    private Long id;

    private Long userId;

    /**
     * 每月账单
     */
    private Integer allowType1;

    /**
     * 月详情账单
     */
    private Integer allowType2;

    /**
     * 备用1
     */
    private Integer allowType3;

    /**
     * 备用2
     */
    private Integer allowType4;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAllowType1() {
        return allowType1;
    }

    public void setAllowType1(Integer allowType1) {
        this.allowType1 = allowType1;
    }

    public Integer getAllowType2() {
        return allowType2;
    }

    public void setAllowType2(Integer allowType2) {
        this.allowType2 = allowType2;
    }

    public Integer getAllowType3() {
        return allowType3;
    }

    public void setAllowType3(Integer allowType3) {
        this.allowType3 = allowType3;
    }

    public Integer getAllowType4() {
        return allowType4;
    }

    public void setAllowType4(Integer allowType4) {
        this.allowType4 = allowType4;
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
        UserConfig other = (UserConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAllowType1() == null ? other.getAllowType1() == null : this.getAllowType1().equals(other.getAllowType1()))
            && (this.getAllowType2() == null ? other.getAllowType2() == null : this.getAllowType2().equals(other.getAllowType2()))
            && (this.getAllowType3() == null ? other.getAllowType3() == null : this.getAllowType3().equals(other.getAllowType3()))
            && (this.getAllowType4() == null ? other.getAllowType4() == null : this.getAllowType4().equals(other.getAllowType4()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAllowType1() == null) ? 0 : getAllowType1().hashCode());
        result = prime * result + ((getAllowType2() == null) ? 0 : getAllowType2().hashCode());
        result = prime * result + ((getAllowType3() == null) ? 0 : getAllowType3().hashCode());
        result = prime * result + ((getAllowType4() == null) ? 0 : getAllowType4().hashCode());
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
        sb.append(", allowType1=").append(allowType1);
        sb.append(", allowType2=").append(allowType2);
        sb.append(", allowType3=").append(allowType3);
        sb.append(", allowType4=").append(allowType4);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}