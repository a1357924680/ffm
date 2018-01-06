package com.family.financial.management.controller;


import com.family.financial.management.dao.entity.GroupRequest;
import com.family.financial.management.dao.entity.Groups;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.GroupInfoForm;
import com.family.financial.management.model.Request;
import com.family.financial.management.service.interfaces.GroupService;
import com.family.financial.management.service.interfaces.UserConfigService;
import com.family.financial.management.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.family.financial.management.emun.FFMExceptionEnum.*;
import static com.family.financial.management.utils.Const.ALLOW_APPLY;
import static com.family.financial.management.utils.Const.REJECT_APPLY;
import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/9/20.
 */
@RestController
@RequestMapping("/group")
public class GroupController extends BaseController{
    Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserConfigService userConfigService;
    @PostMapping("/createGroup")
    public Map<String,String> createGroup(@RequestParam(defaultValue = "") String groupName){
        try {
            User user = getUser();
            if (user.getGroupId() != 0){
                throw new FFMException(USER_HAVE_GROUP);
            }
            groupService.createGroup(groupName,user);
            updateUserInfo(user.getId());
            return getSuccessResult("groupId",getUser().getGroupId());
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }

    }


    @PostMapping("/updateGroup")
    public Map<String,String> updateGroup(@RequestParam(defaultValue = "") String groupName){
        try {
            User user = getUser();
            if (user.getGroupId() == 0){
                throw new FFMException(USER_HAVE_NO_GROUP);
            }
            if (!user.getIsManager()){
                throw new FFMException(NOT_GROUP_MANAGER);
            }
            groupService.updateGroup(groupName,user);
            updateUserInfo(user.getId());
            return getSuccessResult();
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }

    }
    @PostMapping("/dropGroup")
    public Map<String,String> dropGroup(){
        try {
            User user = getUser();
            if (!user.getIsManager()){
                throw new FFMException(USER_IS_NOT_MANAGER);
            }
            if (user.getGroupId() == 0 ){
                throw new FFMException(USER_HAVE_NO_GROUP);
            }
            groupService.dropGroup(user);
            updateUserInfo(user.getId());
            return getSuccessResult();
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @GetMapping("/findGroup")
    public Map<String, String> findGroup(@RequestParam(defaultValue = "") String groupInfo){

        try {
            if (StringUtils.isEmpty(groupInfo)){
                throw new FFMException(ERROR_PARAMETER);
            }
            List<Groups> group = groupService.findGroup(groupInfo);
            return getSuccessResult("group",group);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }
    @GetMapping("/getGroupInfo")
    public Map<String, String> getGroupInfo(){

        try {
            User user = getUser();
            if (user.getGroupId() == 0){
                throw new FFMException(USER_HAVE_NO_GROUP);
            }
            GroupInfoForm groupInfoForm = groupService.getGroupInfo(user);
            return getSuccessResult("groupInfoForm",groupInfoForm);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }



    @PostMapping("/applyForGroup")
    public Map<String,String> applyForGroup(@RequestParam(defaultValue = "a") String groupId){
        try {
            Long groupid = checkStringToLong(groupId);
            User user = getUser();
            if (user.getGroupId()>0){
                throw new FFMException(USER_HAVE_GROUP);
            }
            groupService.applyForGroup(groupid,user);
            return getSuccessResult();
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


    @PostMapping("/handleGroupApply")
    public Map<String,String> handleGroupApply(@RequestParam(defaultValue = "a") String id,@RequestParam(defaultValue = "a")String decide){
        try {
            Long userId = checkStringToLong(id);
            Long deci = checkStringToLong(decide);
            if ((deci!=ALLOW_APPLY)&&(deci!=REJECT_APPLY)){
                throw new FFMException(ILLEAGE_HANDLE);
            }
            User user = getUser();
            groupService.handleGroupApply(userId,user,deci);
            return getSuccessResult();
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


    @GetMapping("/getNeedHandelRequests")
    public Map<String,String> getGroupRequests(){
        try {
            User user = getUser();
            if (!user.getIsManager()){
                throw new FFMException(NOT_GROUP_MANAGER);
            }
            List<Request> groupRequests = groupService.getGroupRequests(user);
            return getSuccessResult("groupRequests",groupRequests);
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }



    @PostMapping("/quitGroup")
    public Map<String,String> quitGroup(){
        try {
            User user = getUser();
            if (user.getGroupId() == 0) {
                throw new FFMException(USER_HAVE_NO_GROUP);
            }
            groupService.quitGroup(user);
            updateUserInfo(user.getId());
            return getSuccessResult();
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/removeFromGroup")
    public Map<String,String> removeFromGroup(@RequestParam(defaultValue = "a") String removeId){
        try {
            Long removeid = checkStringToLong(removeId);
            User user = getUser();
            if (!user.getIsManager()) {
                throw new FFMException(NOT_GROUP_MANAGER);
            }
            if (removeid.equals(user.getId())){
                throw new FFMException(CAN_NOT_REMVE_SELF);
            }
            groupService.removeFromGroup(user,removeid);
            return getSuccessResult();
        } catch (FFMException e) {
            logger.error(e.getCode()+":"+e.getMsg());
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }


    Long checkStringToLong(String groupId) throws FFMException {
        try {
            Long groupid = Long.parseLong(groupId);
            return groupid;
        }catch (Exception e){
            throw new FFMException(GROUP_ID_ERROR);
        }
    }

}
