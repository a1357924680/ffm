package com.family.financial.management.controller;


import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountForm;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.DefiniteAccount;
import com.family.financial.management.service.interfaces.AccountService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.family.financial.management.emun.FFMExceptionEnum.ACCOUNT_ID_ERROR;
import static com.family.financial.management.emun.FFMExceptionEnum.ERROR_PARAMETER;
import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/10/14.
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {
    Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountService accountService;

    @PostMapping("/addAccount")
    public Map<String,String> addAccount(AccountForm accountForm){
        try {
            User user = getUser();
            accountService.addAccount(user.getId(),accountForm);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }
    @PostMapping("/updateAccount")
    public Map<String, String> updateAccount(AccountForm accountForm){
        try {
            User user = getUser();
            if (StringUtils.isEmpty(accountForm.getId())){
                throw new FFMException(ACCOUNT_ID_ERROR);
            }
            accountService.updateAccount(user.getId(),accountForm);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @PostMapping("/deleteAccount")
    public Map<String, String> deleteAccount(AccountForm accountForm){
        try {
            User user = getUser();
            if (StringUtils.isEmpty(accountForm.getId())){
                throw new FFMException(ACCOUNT_ID_ERROR);
            }
            accountService.deleteAccount(user.getId(),accountForm);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @GetMapping(" ")
    public Map<String, String> getAccountList(String limit,String offset,String fromDate,String toDate){
        try {
            User user = getUser();
            Date FromDate = null;
            Date ToDate = null;
            Integer Limit = null;
            Integer Offset = null;
            try {
                FromDate = DateUtils.parseDate(fromDate);
                ToDate = DateUtils.parseDate(toDate);
                Limit = Integer.parseInt(limit);
                Offset = Integer.parseInt(offset);
            } catch (Exception e) {
                throw new FFMException(ERROR_PARAMETER);
            }
            List<DefiniteAccount> definiteAccounts = accountService.getAccountList(user.getId(),FromDate,ToDate,Limit,Offset);
            return getSuccessResult("accounts",definiteAccounts);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("getByConditions")
    public Map<String, String> getByConditions(ConditionForm conditionForm){
        try {
            User user = getUser();

            List<DefiniteAccount> definiteAccounts = accountService.getByConditions(user.getId(),conditionForm);
            return getSuccessResult("accounts",definiteAccounts);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }
}

