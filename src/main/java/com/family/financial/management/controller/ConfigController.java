package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.service.interfaces.ConfigService;
import com.family.financial.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/12/3.
 */
@RestController()
@RequestMapping("/config")
public class ConfigController extends BaseController{

    @Autowired
    private ConfigService configService;
    @PostMapping("/createConfig")
    public Map<String, String> addConfig(String type,String isSpending,String timeType, String name, String description, String money){
        try {
            User user = getUser();
            checkName(name);
            long _money = StringUtils.praseLong(money);
            boolean _isSpending = checkType(isSpending);
            StringUtils.praseLong(timeType);

            configService.createConfig(name,StringUtils.praseLong(type),user.getId(),_isSpending,_money,timeType,description);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @PostMapping("/updateConfig")
    public Map<String, String> updateConfig(String id,String type,String isSpending,String timeType, String name, String description, String money){
        try {
            User user = getUser();
            checkName(name);
            long _id = StringUtils.praseLong(id);
            long _money = StringUtils.praseLong(money);
            boolean _isSpending = checkType(isSpending);
            StringUtils.praseLong(timeType);

            configService.updateConfig(_id,name,StringUtils.praseLong(type),user.getId(),_isSpending,_money,timeType,description);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @PostMapping("/deleteConfig")
    public Map<String, String> deleteConfig(String id){
        try {
            User user = getUser();
            long _id = StringUtils.praseLong(id);
            configService.deleteConfig(_id,user.getId());
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @GetMapping("/getConfig")
    public Map<String, String> getConfig(){
        try {
            User user = getUser();
            List list = configService.getConfigs(user.getId());
            return getSuccessResult("config",list);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }

    }




    private void checkName(String name)throws FFMException{
        if (StringUtils.isEmpty(name)) {
            throw new FFMException(101231, "name不能为空");
        }
    }
    private boolean checkType(String type) throws FFMException {
        if ("1".equals(type)){
            return true;
        }else if ("0".equals(type)){
            return false;
        }else {
            throw new FFMException(199201,"type请传0或1");
        }

    }
}
