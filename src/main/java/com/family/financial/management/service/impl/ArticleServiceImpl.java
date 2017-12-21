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
import java.util.Date;
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
        example.setOffset((pageNum-1)*20);
        example.setOrderByClause("id desc");
        List<Article> articles = articleMapper.selectByExample(example);
        List<ArticleForm> articleForm = new ArrayList<ArticleForm>();
        articles.forEach(a->{
            ArticleForm article = new ArticleForm(a.getId(),a.getTitle(),a.getGmtCreate());
            articleForm.add(article);
        });
        return articleForm;
    }

    /**
     * 找出ID比当前大的第一个（文章顺序是倒叙的）
     * @param id
     * @return
     * @throws FFMException
     */
    @Override
    public Long getPreId(long id) throws FFMException {
        Article article = articleMapper.selectByPrimaryKey( id);
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("id asc");
        example.setLimit(1);
        example.createCriteria().andIdGreaterThan(id);
        List<Article> articles = articleMapper.selectByExample(example);
        if (articles==null||articles.size()==0){
            return Long.valueOf(-1);
        }
        return articles.get(0).getId();
    }

    /**
     * 找出ID比当前小的第一个
     * @param id
     * @return
     * @throws FFMException
     */
    @Override
    public Long getNextId(long id) throws FFMException {
        Article article = articleMapper.selectByPrimaryKey( id);
        ArticleExample example = new ArticleExample();
        example.setOrderByClause("id desc");
        example.setLimit(1);
        example.createCriteria().andIdLessThan(id);
        List<Article> articles = articleMapper.selectByExample(example);
        if (articles==null||articles.size()==0){
            return Long.valueOf(-1);
        }
        return articles.get(0).getId();
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
        article.setGmtCreate(new Date());
        if (article.getId()==null){
            articleMapper.insertSelective(article);
        }else {
            articleMapper.updateByPrimaryKey(article);
        }
    }

    @Override
    public void updateArticle(Article article) throws FFMException {
//        article.setGmtCreate(new Date());
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public void deleteArticle(long id) throws FFMException {
        articleMapper.deleteByPrimaryKey(id);
    }
}
