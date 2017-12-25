package com.family.financial.management.model;

/**
 * Created by zhangyiping on 2017/12/25.
 */
public class BasicTypeModel {
    private Long toplevel;
    private String topname;

    public BasicTypeModel(Long toplevel, String topname) {
        this.toplevel = toplevel;
        this.topname = topname;
    }

    public Long getToplevel() {
        return toplevel;
    }

    public void setToplevel(Long toplevel) {
        this.toplevel = toplevel;
    }

    public String getTopname() {
        return topname;
    }

    public void setTopname(String topname) {
        this.topname = topname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicTypeModel)) return false;

        BasicTypeModel that = (BasicTypeModel) o;

        if (getToplevel() != null ? !getToplevel().equals(that.getToplevel()) : that.getToplevel() != null)
            return false;
        return getTopname() != null ? getTopname().equals(that.getTopname()) : that.getTopname() == null;
    }

    @Override
    public int hashCode() {
        int result = getToplevel() != null ? getToplevel().hashCode() : 0;
        result = 31 * result + (getTopname() != null ? getTopname().hashCode() : 0);
        return result;
    }

}
