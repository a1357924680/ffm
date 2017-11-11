package com.family.financial.management.model;

/**
 * Created by zhangyiping on 2017/9/21.
 */
public class AccountTypeForm {
    /**
     * 大类型
     */
    private Long topLeve;


    private String img;

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


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
