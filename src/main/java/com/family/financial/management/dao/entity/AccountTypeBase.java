package com.family.financial.management.dao.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class AccountTypeBase implements Serializable {
    private Long topLevelId;

    private String imgs;

    private String typeName;

    private static final long serialVersionUID = 1L;

    public Long getTopLevelId() {
        return topLevelId;
    }

    public void setTopLevelId(Long topLevelId) {
        this.topLevelId = topLevelId;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        AccountTypeBase other = (AccountTypeBase) that;
        return (this.getTopLevelId() == null ? other.getTopLevelId() == null : this.getTopLevelId().equals(other.getTopLevelId()))
            && (this.getImgs() == null ? other.getImgs() == null : this.getImgs().equals(other.getImgs()))
            && (this.getTypeName() == null ? other.getTypeName() == null : this.getTypeName().equals(other.getTypeName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTopLevelId() == null) ? 0 : getTopLevelId().hashCode());
        result = prime * result + ((getImgs() == null) ? 0 : getImgs().hashCode());
        result = prime * result + ((getTypeName() == null) ? 0 : getTypeName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", topLevelId=").append(topLevelId);
        sb.append(", imgs=").append(imgs);
        sb.append(", typeName=").append(typeName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}