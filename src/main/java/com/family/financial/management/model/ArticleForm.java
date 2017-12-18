package com.family.financial.management.model;

/**
 * Created by zhangyiping on 2017/12/14.
 */
public class ArticleForm {
    private Long id;

    private String title;

    public ArticleForm(Long id, String title) {
        this.id = id;
        this.title = title;
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
