package com.family.financial.management.dao.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class AccountType implements Serializable {
    private Long id;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 大类型
     */
    private Long topLeve;

    /**
     * 用户创建的记录用户的id
     */
    private Long userId;

    /**
     * 是否为系统类型
     */
    private Boolean isBasic;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTopLeve() {
        return topLeve;
    }

    public void setTopLeve(Long topLeve) {
        this.topLeve = topLeve;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsBasic() {
        return isBasic;
    }

    public void setIsBasic(Boolean isBasic) {
        this.isBasic = isBasic;
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
        AccountType other = (AccountType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypeName() == null ? other.getTypeName() == null : this.getTypeName().equals(other.getTypeName()))
            && (this.getTopLeve() == null ? other.getTopLeve() == null : this.getTopLeve().equals(other.getTopLeve()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIsBasic() == null ? other.getIsBasic() == null : this.getIsBasic().equals(other.getIsBasic()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypeName() == null) ? 0 : getTypeName().hashCode());
        result = prime * result + ((getTopLeve() == null) ? 0 : getTopLeve().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIsBasic() == null) ? 0 : getIsBasic().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeName=").append(typeName);
        sb.append(", topLeve=").append(topLeve);
        sb.append(", userId=").append(userId);
        sb.append(", isBasic=").append(isBasic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}