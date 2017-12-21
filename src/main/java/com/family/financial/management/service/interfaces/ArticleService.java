package com.family.financial.management.service.interfaces;

import com.family.financial.management.dao.entity.Article;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.ArticleForm;

import java.util.List;

/**
 * Created by zhangyiping on 2017/12/14.
 */
public interface ArticleService {
    long getPageNum()throws FFMException;
    List<ArticleForm> getArticles(int pageNum)throws FFMException;
    Long getPreId(long id)throws FFMException;
    Long getNextId(long id)throws FFMException;
    Article getArticle(long id)throws FFMException;
    void addArticle(Article article)throws FFMException;
    void updateArticle(Article article)throws FFMException;
    void deleteArticle(long id)throws FFMException;


}
