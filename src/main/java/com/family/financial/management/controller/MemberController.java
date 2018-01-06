package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.UserConfig;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.DefiniteAccount;
import com.family.financial.management.service.interfaces.AccountService;
import com.family.financial.management.service.interfaces.UserConfigService;
import com.family.financial.management.service.interfaces.UserService;
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
 * Created by zhangyiping on 2017/12/30.
 */
@RestController
@RequestMapping("/member")
public class MemberController extends BaseController{

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserConfigService userConfigService;
    @GetMapping("/getConfig")
    private Map<String,String> getConfig(String toUser){
        try {
            UserConfig userConfig = userConfigService.getUserConfig(getUser().getId(),StringUtils.praseLong( toUser));
            return getSuccessResult("userConfig",userConfig);
        } catch (FFMException e) {
            logger.error(e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/updateConfig")
    private Map<String,String> updateConfig(String toUser,String type1,String type2){
        try {
            userConfigService.updateUserConfig(getUser().getId(),StringUtils.praseLong(toUser)
                    , StringUtils.praseInteger(type1)
                    ,StringUtils.praseInteger(type2));
            return getSuccessResult();
        } catch (FFMException e) {
            logger.error(e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("/getUserMonthAccount")
    private Map<String,String> getUserMonthAccount(String userId, String year){
        try {
            UserConfig userConfig = userConfigService.getUserConfig(getUser().getId(),StringUtils.praseLong(userId));
            if (userConfig.getAllowType1().equals(0)){
                throw new FFMException(34789234,"该用户设置不显示！");
            }
            return getSuccessResult("accounts",userService.getMonthBill(StringUtils.praseInteger(userId),StringUtils.praseInteger(year)));
        } catch (FFMException e) {
            logger.error(e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("/getUserMonthDetail")
    private Map<String,String> getUserMonthDetail(String userId, ConditionForm conditionForm){
        try {
            UserConfig userConfig = userConfigService.getUserConfig(getUser().getId(),StringUtils.praseLong(userId));
            if (userConfig.getAllowType1().equals(0)||userConfig.getAllowType2().equals(0)){
                throw new FFMException(34789234,"该用户设置不显示！");
            }
            List<DefiniteAccount> accounts = accountService.getByConditions(Long.parseLong(userId),conditionForm);
            return getSuccessResult("accounts",accounts);
        } catch (FFMException e) {
            logger.error(e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }
}
