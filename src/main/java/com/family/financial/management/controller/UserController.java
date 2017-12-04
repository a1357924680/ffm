package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.User;
import com.family.financial.management.model.UserForm;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.UserInfoFrom;
import com.family.financial.management.service.interfaces.UpdateAllAccountService;
import com.family.financial.management.service.interfaces.UserService;
import com.family.financial.management.utils.Const;
import com.family.financial.management.utils.StringUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.family.financial.management.emun.FFMExceptionEnum.WRONG_PASSWORD;
import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by Administrator on 2017/9/18.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UpdateAllAccountService updateService;
    //    @RequestBody @Valid UserForm userForm,@RequestParam MultipartFile filePhoto
    @PostMapping("/register")
    Map<String, String> register(String userId,
                                 String userName,String password,String mobile,String sex){
        try {
            UserForm userForm = new UserForm();
            userForm.setMobile(mobile);
            userForm.setPassword(password);
            userForm.setSex(sex);
            userForm.setUserId(userId);
            userForm.setUserName(userName);
            checkUserForm(userForm);
            userService.registerUser(userForm);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }


//    @RequestParam(defaultValue = "0")@RequestParam(defaultValue = "0")
    @PostMapping("/login")
    public Map<String, String> login(Model model,HttpSession session, @RequestParam String password, @RequestParam String userId){
        try {
            User user = userService.getUser(userId);
            if (!user.getPassword().equals(password)){
                throw new FFMException(WRONG_PASSWORD);
            }
            session.setAttribute(Const.SESSION_USER, user);
            updateService.checkConfig(user.getId());
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public Map<String, String> logout(HttpSession session){
        session.invalidate();
        return getSuccessResult();
    }


    @PostMapping("/updateUser")
    public Map<String,String> updateUser(@RequestParam MultipartFile filePhoto,
                                         String userName,String password,String mobile,String sex){
        try {
            UserForm userForm = new UserForm();
            userForm.setMobile(mobile);
            userForm.setFilePhoto(filePhoto);
            userForm.setPassword(password);
            userForm.setSex(sex);
            userForm.setUserName(userName);
            checkUserFormUpdate(userForm);
            userService.updateUser(userForm);
            updateUserInfo(Long.parseLong(userForm.getUserId()));
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @GetMapping("/getMonthBill")
    public Map<String,String> updateUser( String userId,String year){
        try {
            long yearLong ;
            if (StringUtils.isEmpty(year)){
                yearLong = LocalDate.now().getYear();
            }else {
                try {
                    yearLong = Long.parseLong(year);
                }catch (Exception e){
                    throw new FFMException(100151,"错误的年份");
                }
            }
            List accountList = userService.getMonthBill(Long.parseLong(userId),yearLong);
            return getSuccessResult("accountList",accountList);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }

    }
    private void checkUserForm(UserForm userForm) throws FFMException{
        if(userForm.getFilePhoto()==null){
            throw new FFMException(100101,"头像不能为空");
        }
        if(userForm.getPassword()==null){
            throw new FFMException(100101,"密码不能为空");
        }
        if(userForm.getUserName()==null){
            throw new FFMException(100101,"昵称不能为空");
        }
        if(userForm.getUserId()==null){
            throw new FFMException(100101,"账号不能为空");
        }

    }
    private void checkUserFormUpdate(UserForm userForm) throws FFMException{
        if(userForm.getFilePhoto()==null){
            throw new FFMException(100101,"头像不能为空");
        }
        if(userForm.getPassword()==null){
            throw new FFMException(100101,"密码不能为空");
        }
        if(userForm.getUserName()==null){
            throw new FFMException(100101,"昵称不能为空");
        }


    }





}
