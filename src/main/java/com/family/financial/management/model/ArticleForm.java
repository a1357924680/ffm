package com.family.financial.management.model;

import java.util.Date;

/**
 * Created by zhangyiping on 2017/12/14.
 */
public class ArticleForm {
    private Long id;

    private String title;

    private Date gmtCreate;

    public ArticleForm(Long id, String title,Date date) {
        this.id = id;
        this.title = title;
        this.gmtCreate = date;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
