package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.Article;
import com.family.financial.management.dao.entity.Message;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.ArticleForm;
import com.family.financial.management.model.SystemType;
import com.family.financial.management.service.interfaces.*;
import com.family.financial.management.utils.Const;
import com.family.financial.management.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/12/14.
 */
@Controller
@RequestMapping("/manager")
public class ManagerController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MessageService messageService;

    @GetMapping("/loginView")
    public String loginView(){
        return "login";
    }

    @GetMapping("/addArticle")
    public String createArticle(Model model){
        model.addAttribute("id",-1);
        model.addAttribute("title","");
        model.addAttribute("content","");
        return "articleEdit";
    }

    @GetMapping("/updateArticle/{id}")
    public String updateArticle(@PathVariable String id,Model model){
        try {
            Article article = articleService.getArticle(StringUtils.praseLong(id));
            model.addAttribute("id",article.getId());
            model.addAttribute("title",article.getTitle());
            model.addAttribute("content",article.getContent());
            return "articleEdit";
        } catch (FFMException e) {
            logger.error(e.getMsg());
            return null;
        }
    }

    /**
     * 获取反馈信息
     * @param pageNum
     * @return
     */
    @GetMapping("/getMessages")
    public String getTypes(Model model, String pageNum){
        try {
            int p = StringUtils.praseInteger(pageNum);
            int c = messageService.getMessagePage();
            List<Message> messages = messageService.getMessages(p);


            //获得当前页
            model.addAttribute("pageNum", p);
            //获得一页显示的条数
            model.addAttribute("pageSize", 20);
            //是否是第一页
            model.addAttribute("isFirstPage", p==1?true:false);
            //获得总页数
            model.addAttribute("totalPages", c);
            //是否是最后一页
            model.addAttribute("isLastPage", p==c?true:false);
            model.addAttribute("messages", messages);
        } catch (FFMException e) {
        }
        return "messages";
    }


    /**
     * 获取小贴士信息
     * @param pageNum
     * @return
     */
    @GetMapping("/getArticles")
    public String getArticles(Model model, String pageNum){
        try {
            int p = StringUtils.praseInteger(pageNum);
            int c = (int) articleService.getPageNum();
            List<ArticleForm> articles = articleService.getArticles(p);

            //获得当前页
            model.addAttribute("pageNum", p);
            //获得一页显示的条数
            model.addAttribute("pageSize", 20);
            //是否是第一页
            model.addAttribute("isFirstPage", p==1?true:false);
            //获得总页数
            model.addAttribute("totalPages", c);
            //是否是最后一页
            model.addAttribute("isLastPage", p==c?true:false);
            model.addAttribute("articles", articles);
        } catch (FFMException e) {
        }
        return "articles";
    }




    /**
     * 获取类别信息
     * @param condition
     * @return
     */
    @GetMapping("/getTypes")
    public String getMessages(Model model,String condition){
        try {
            int con = StringUtils.praseInteger(condition);
            int c = managerService.getTypePage();
            List<SystemType> types = managerService.getConditionTypes(con);
            model.addAttribute("types", types);
        } catch (FFMException e) {
        }
        return "types";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(String userId, String password, HttpSession session){
        try {
            if (StringUtils.isEmpty(userId)||StringUtils.isEmpty(password)){
                throw new FFMException(521333,"账号、密码不能为空");
            }
            User user = managerService.getUser(userId,password);
            session.setAttribute(Const.SESSION_USER, user);
            return getSuccessResult("user",user);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


    @GetMapping("/getArticle/{id}")
    public String getArticle(@PathVariable String id,Model model){
        try {
            Long articleId = StringUtils.praseLong(id);
            long pre =articleService.getPreId(articleId);
            long next =  articleService.getNextId(articleId);
            Article article = articleService.getArticle(articleId);
            model.addAttribute("article",article);
            model.addAttribute("pre",pre);
            model.addAttribute("next",next);
            model.addAttribute("isFirstPage",pre==-1);
            model.addAttribute("isLastPage",next==-1);
        } catch (FFMException e) {
            logger.error(e.getMsg());
        }
        return "articleView";
    }


    @PostMapping("/addUser")
    @ResponseBody
    public Map<String, String> addUser(String userId, String password){
        try {
            if (StringUtils.isEmpty(userId)||StringUtils.isEmpty(password)){
                throw new FFMException(521333,"账号、密码不能为空");
            }
            if (!"admin".equals(getUser().getUserId())){
                throw new FFMException(278912,"只有超级管理员才能创建");
            }
            managerService.addUser(userId,password);
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


    @PostMapping("/getAllUser")
    public Map<String, String> getAllUser(){
        try {
            List<User> users = managerService.getAllUser();
            return getSuccessResult("users",users);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/addType")
    @ResponseBody
    public Map<String, String> addType(String topTypeId,String typeName){
        try {
            if (StringUtils.isEmpty(topTypeId)){
                throw new FFMException(5221112,"父类型ID不能为空");
            }
            if (StringUtils.isEmpty(typeName)){
                throw new FFMException(5221112,"类型 名称不能为空");
            }
            managerService.addType(StringUtils.praseLong(topTypeId),typeName);
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


    @PostMapping("/answerMessage")
    @ResponseBody
    public Map<String, String> answerMessage(String message,String id){
        try {
            if (StringUtils.isEmpty(message)){
                throw new FFMException(5221112,"回复不能为空");
            }
            managerService.answerMessage(message,StringUtils.praseLong(id));
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("/checkAdmin")
    @ResponseBody
    public Map<String, String> checkAdmin(){
        try {
            if ("admin".equals(getUser().getUserId())){
                return getSuccessResult();
            }else {
                return getErrorResult(789123,"非超级管理员");
            }
        } catch (FFMException e) {
            logger.error(e.getMsg());
            return getErrorResult(789123,e.getMsg());
        }

    }


}
