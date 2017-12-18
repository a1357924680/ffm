package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.Article;
import com.family.financial.management.dao.entity.ArticleExample;
import com.family.financial.management.dao.mapper.ArticleMapper;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.ArticleForm;
import com.family.financial.management.service.interfaces.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyiping on 2017/12/14.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Override
    public long getPageNum() throws FFMException {
        ArticleExample example = new ArticleExample();
        example.createCriteria();
        return articleMapper.countByExample(example)/20+1;
    }

    @Override
    public List<ArticleForm> getArticles(int pageNum) throws FFMException {
        ArticleExample example = new ArticleExample();
        example.setLimit(20);
        example.setOffset(pageNum-1);
        List<Article> articles = articleMapper.selectByExample(example);
        List<ArticleForm> articleForm = new ArrayList<ArticleForm>();
        articles.forEach(a->{
            ArticleForm article = new ArticleForm(a.getId(),a.getTitle());
            articleForm.add(article);
        });
        return articleForm;
    }

    @Override
    public Article getArticle(long id) throws FFMException {
        Article article = articleMapper.selectByPrimaryKey( id);
        if (article == null){
            throw new FFMException(177342,"未查到该文章");
        }
        return article;
    }

    @Override
    public void addArticle(Article article) throws FFMException {
        articleMapper.insertSelective(article);
    }

    @Override
    public void updateArticle(Article article) throws FFMException {
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void deleteArticle(long id) throws FFMException {
        articleMapper.deleteByPrimaryKey(id);
    }
}
