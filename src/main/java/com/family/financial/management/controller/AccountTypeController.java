package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.User;
import com.family.financial.management.dao.mapper.AccountTypeMapper;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.AccountTypeForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/9/21.
 */
@RestController
@RequestMapping("/accountType")
public class AccountTypeController extends BaseController{

    /**
     * 还没写完
     * @param accountTypeForm
     * @return
     */
    @PostMapping("/createAccountType")
    public Map<String,String> createAccountType(AccountTypeForm accountTypeForm){
        try {
            User user = getUser();

        } catch (FFMException e) {
            e.printStackTrace();
        }
        return getSuccessResult();
    }



}
