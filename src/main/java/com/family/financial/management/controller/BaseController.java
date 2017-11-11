package com.family.financial.management.controller;

import com.alibaba.fastjson.JSON;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.dao.mapper.UserMapper;
import com.family.financial.management.emun.FFMExceptionEnum;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.UserInfoFrom;
import com.family.financial.management.utils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Map;

import static com.family.financial.management.emun.FFMExceptionEnum.LOGIN_ERROR;
import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;

/**
 * Created by zhangyiping on 2017/9/20.
 */
@ControllerAdvice
public class BaseController {

    /**
     * 请求的返回对象
     */
    protected HttpServletRequest request;
    /*日志*/
    private static Logger log = LoggerFactory.getLogger(BaseController.class);

    public HttpServletRequest getRequest() {
        return request;
    }
    @Resource
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    protected UserInfoFrom userInfoFrom;

    @Resource
    private UserMapper userMapper;



    Logger logger = LoggerFactory.getLogger(BaseController.class);
    public User getUser() throws FFMException {
        User user = (User)request.getSession().getAttribute("sessionUser");
        return user;
    }

    protected void updateUserInfo(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(user,userInfoFrom);

    }

    @ExceptionHandler
    public Map<String, String> exc(Exception ex){
        logger.error(ex.getMessage(), ex);
        if(ex instanceof ConstraintViolationException){
            final StringBuffer sbf = new StringBuffer();
            ((ConstraintViolationException)ex).getConstraintViolations().forEach(e -> sbf.append(e.getMessage()).append(","));
            return getErrorResult(100000, "参数异常：" + sbf.toString());
        }
        if (ex instanceof BindException){
            final StringBuilder sb = new StringBuilder();
            ((BindException) ex).getAllErrors().forEach(e-> sb.append(e.getDefaultMessage()).append(","));
            return getErrorResult(100000,"参数异常：" + sb.toString());
        }
        return getErrorResult(100000, ex.getMessage()!=null?ex.getMessage():"系统异常!");
    }
}
