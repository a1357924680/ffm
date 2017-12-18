package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.Article;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.ArticleForm;
import com.family.financial.management.service.interfaces.ArticleService;
import com.family.financial.management.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/12/14.
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/getPageNum")
    public Map<String,String> getPageNum(){
        try {
            Map<String,Object> info = new HashMap<String,Object>();
            long page = articleService.getPageNum();
            return getSuccessResult("page",page);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("/getPage")
        public Map<String,String> getPageNum(String pageNum){
        try {
            Map<String,Object> info = new HashMap<String,Object>();
            List<ArticleForm> articles = articleService.getArticles(StringUtils.praseInteger(pageNum));
            return getSuccessResult("articles",articles);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("/getArticle")
    public Map<String,String> getArticle(String id){
        try {

            Map<String,Object> info = new HashMap<String,Object>();
            Article article = articleService.getArticle(StringUtils.praseLong(id));
            return getSuccessResult("article",article);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/insertArticle")
    public Map<String,String> insertTips(String title,String content){
        try {
            if (StringUtils.isEmpty(title)||StringUtils.isEmpty(content)){
                throw new FFMException(100435,"类型名称不得为空");
            }
            Article article = new Article();
            article.setContent(content);
            article.setTitle(title);
            articleService.addArticle(article);
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/updateArticle")
    public Map<String,String> updateArticle(String id,String title,String content){
        try {
            if (!Objects.equals("admin",getUser().getUserId())){
                throw new FFMException(199827,"只有管理员才具有该权限");
            }
            if (StringUtils.isEmpty(title)||StringUtils.isEmpty(content)){
                throw new FFMException(100435,"内容不得为空");
            }
            Article article = new Article();
            article.setContent(content);
            article.setTitle(title);
            article.setId(StringUtils.praseLong(id));
            articleService.updateArticle(article);
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/deleteArticle")
    public Map<String,String> deleteArticle(String id){
        try {
            if (!Objects.equals("admin",getUser().getUserId())){
                throw new FFMException(199827,"只有管理员才具有该权限");
            }

            articleService.deleteArticle(StringUtils.praseLong(id));
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


}
