package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.Article;
import com.family.financial.management.dao.entity.Message;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.model.SystemType;
import com.family.financial.management.model.UserAndBasicTypes;
import com.family.financial.management.service.interfaces.AccountTypeService;
import com.family.financial.management.service.interfaces.ArticleService;
import com.family.financial.management.service.interfaces.ManagerService;
import com.family.financial.management.service.interfaces.UserService;
import com.family.financial.management.utils.Const;
import com.family.financial.management.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.family.financial.management.emun.FFMExceptionEnum.WRONG_PASSWORD;
import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/12/14.
 */
@RestController()
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

    @PostMapping("/login")
    public Map<String, String> login(String userId, String password, HttpSession session){
        try {
            if (!"admin".equals(userId)){
                throw new FFMException(100292,"请使用管理员账号登录");
            }
            User user = userService.getUser(userId);
            if (!user.getPassword().equals(password)){
                throw new FFMException(WRONG_PASSWORD);
            }
            session.setAttribute(Const.SESSION_USER, user);
            return getSuccessResult("user",user);
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



    /**
     * 每页20条记录
     * @return
     */
    @GetMapping("/getTypePage")
    public Map<String, String> getTypePage(){
        try {
            Map<String,Object> info = new HashMap<String,Object>();
            int page = managerService.getTypePage();
            return getSuccessResult("page",page);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    /**
     * 页码从1开始
     * @param pageNum
     * @return
     */
    @GetMapping("/getTypes")
    public Map<String, String> getTypes(String pageNum){
        try {
            List<SystemType> types = managerService.getSystemTypes(StringUtils.praseInteger(pageNum));
            return getSuccessResult("types",types);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/addType")
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






}
