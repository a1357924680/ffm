package com.family.financial.management.controller;


import com.alibaba.fastjson.JSONObject;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountForm;
import com.family.financial.management.model.ConditionForm;
import com.family.financial.management.model.DefiniteAccount;
import com.family.financial.management.service.interfaces.AccountService;
import com.family.financial.management.utils.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
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
    public Map<String,String> addAccount(@RequestParam String type
            ,@RequestParam String gmtCreate,@RequestParam(defaultValue = "") String description
            , @RequestParam(defaultValue = "0") String income,@RequestParam(defaultValue = "0") String spending){
        try {
            if (spending==null){
                spending ="";
            }
            if (income==null){
                income="";
            }
            AccountForm accountForm = checkAccountForm(type, gmtCreate, description, income, spending);
            User user = getUser();
            accountService.addAccount(user.getId(),accountForm);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    private AccountForm checkAccountForm(String type, String gmtCreate, String description, String income, String spending) throws FFMException {
        AccountForm accountForm = new AccountForm();
        try {
            accountForm.setType(Long.parseLong(type));
        }catch (Exception e){
            throw new FFMException(100901,"type参数异常");
        }
        try {
         accountForm.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd").parse(gmtCreate));
        }catch (Exception e){
            throw new FFMException(100902,"时间参数异常");
        }
        if (!StringUtils.isEmpty(income)){
            accountForm.setIncome(Long.parseLong(income));
        }else {
            accountForm.setIncome(0L);
        }
        if (!StringUtils.isEmpty(spending)){
            accountForm.setSpending(Long.parseLong(spending));
        }else {
            accountForm.setSpending(0L);
        }
        accountForm.setAccountNum(accountForm.getIncome()-accountForm.getSpending());
        if (!StringUtils.isEmpty(description)){
            accountForm.setDescription(description);
        }
        return accountForm;
    }

    @PostMapping("/updateAccount")
    public Map<String, String> updateAccount(String id,String type, String gmtCreate, String description, String income, String spending){
        try {
            AccountForm accountForm = checkAccountForm(type, gmtCreate, description, income, spending);
            accountForm.setId(StringUtils.praseLong(id));
            User user = getUser();
            accountService.updateAccount(user.getId(),accountForm);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @PostMapping("/deleteAccount")
    public Map<String, String> deleteAccount(String id){
        try {

            User user = getUser();
            if (StringUtils.isEmpty(id)){
                throw new FFMException(ACCOUNT_ID_ERROR);
            }

            accountService.deleteAccount(user.getId(), com.family.financial.management.utils.StringUtils.praseLong(id));
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @GetMapping("/getAccountList")
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
            long count = accountService.countByConditions(user.getId(),conditionForm);
            List<DefiniteAccount> definiteAccounts = accountService.getByConditions(user.getId(),conditionForm);
            Map<String, Object> result = new HashMap<>();
            result.put("totalpage",String.valueOf(count));
            result.put("accounts",definiteAccounts);
            return getSuccessResult("content",result);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("getCount")
    public Map<String, String> getByConditionsCount(ConditionForm conditionForm){
        try {
            User user = getUser();

            int count = accountService.getCountByConditions(user.getId(),conditionForm);
            return getSuccessResult("count",count);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


    @GetMapping("getIndexAccount")
    public Map<String, String> getIndexAccount(){
        try {
            User user = getUser();
            JSONObject json = accountService.getIndexAccount(user.getId());
            return getSuccessResult("accounts",json);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


}

