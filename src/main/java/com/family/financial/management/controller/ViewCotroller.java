package com.family.financial.management.controller;

import com.family.financial.management.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/10/15.
 */
@Controller
@RequestMapping("/view")
@SessionAttributes(value = {"userInfo"})
public class ViewCotroller extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
//        return "login";
        return modelAndView;
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/mainPage")
    public String mainPage(Model model){
        model.addAttribute("userInfo");
        return "mainWindow";
    }
    @GetMapping("/updateUser")
    public String updateUser(){
        return "updateUser";
    }
    @GetMapping("/group")
    public String groupView(){
        return "group";
    }
    @GetMapping("/createGroup")
    public String createGroupView(){
        return "createGroup";
    }
    @GetMapping("/updateGroup")
    public String updateGroupView(){
        return "updateGroup";
    }
    @GetMapping("/getGroupRequests")
    public String getGroupRequests(){
        return "getGroupRequests";
    }

    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
