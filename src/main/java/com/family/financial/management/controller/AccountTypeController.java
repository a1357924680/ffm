package com.family.financial.management.controller;

import com.alibaba.fastjson.JSONObject;
import com.family.financial.management.dao.entity.AccountType;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import com.family.financial.management.model.BasicTypeModel;
import com.family.financial.management.model.UserAndBasicTypes;
import com.family.financial.management.service.interfaces.AccountTypeService;
import com.family.financial.management.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/9/21.
 */
@RestController
@RequestMapping("/accountType")
public class AccountTypeController extends BaseController{

    @Autowired
    private AccountTypeService accountTypeService;
    /**
     * 还没写完
     * @param topLevel
     * @return
     */
    @PostMapping("/createAccountType")
    public Map<String,String> createAccountType(String typeName,String topLevel){
        try {
            AccountTypeForm accountTypeForm = checkAccountType(typeName,topLevel);
            User user = getUser();
            accountTypeForm.setUserId(user.getId());
            AccountType accountType = accountTypeService.createAccountType(accountTypeForm);
            return getSuccessResult("type",accountType);
        } catch (FFMException e) {
            e.printStackTrace();
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/deleteAccountType")
    public Map<String,String> deleteAccountType(String typeId){
        try {
            User user = getUser();
            accountTypeService.deleteAccountType(StringUtils.praseLong(typeId),user.getId());
        } catch (FFMException e) {
            e.printStackTrace();
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }

    @PostMapping("/updateAccountType")
    public Map<String,String> updateAccountType(String typeId,String typeName,String topLevel){
        try {
            AccountTypeForm accountTypeForm = checkAccountType(typeName,topLevel);
            User user = getUser();
            accountTypeForm.setUserId(user.getId());
            accountTypeService.updateAccountType(StringUtils.praseLong(typeId) , accountTypeForm);
        } catch (FFMException e) {
            e.printStackTrace();
            return getErrorResult(e.getCode(),e.getMsg());
        }
        return getSuccessResult();
    }


    private AccountTypeForm checkAccountType(String typeName, String topLevel) throws FFMException {
        AccountTypeForm accountTypeForm = new AccountTypeForm();
        try {
            accountTypeForm.setTopLeve(Long.parseLong(topLevel));
        }catch (Exception e){
            throw new FFMException(100905,"父类型异常");
        }
        try {
            accountTypeForm.setTypeName(typeName.toString());
        }catch (Exception e){
            throw new FFMException(100905,"类型名称异常");
        }
        return accountTypeForm;
    }

    @GetMapping("getAccountType")
    public Map<String, String> getAccountType(){
        try {
            User user = getUser();
            List<UserAndBasicTypes> list = accountTypeService.getAllAccount(user.getId());
            Map<String,List> map = new HashMap<String,List>();
            map.put("types",list);
            map.put("topTypes",list.stream().map(ty->new BasicTypeModel(ty.getTopLeve(),ty.getTopName())).distinct().collect(Collectors.toList()));
            return getSuccessResult("types",map);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

}
