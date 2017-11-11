package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.*;
import com.family.financial.management.dao.mapper.*;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.GroupInfoForm;
import com.family.financial.management.model.UserInfoFrom;
import com.family.financial.management.service.interfaces.GroupService;
import com.family.financial.management.service.interfaces.UpdateAllAccountService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.family.financial.management.emun.FFMExceptionEnum.*;
import static com.family.financial.management.utils.Const.ALLOW_APPLY;
import static com.family.financial.management.utils.Const.REJECT_APPLY;

/**
 * Created by zhangyiping on 2017/9/20.
 */
@Transactional(rollbackFor = FFMException.class)
@Service
public class GroupServiceImpl implements GroupService {

    private Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
    @Resource
    UserMapper userMapper;
    @Resource
    GroupsMapper groupsMapper;
    @Resource
    GroupRequestMapper groupRequestMapper;

    @Autowired
    private UpdateAllAccountService updateService;

    @Override
    public long createGroup(String groupName, User user) throws FFMException{
        Groups group = new Groups();
        group.setGroupMembers(user.getId().toString());
        group.setGroupName(groupName);
        group.setGroupManager(user.getId());
        groupsMapper.insertSelective(group);

        user.setGroupId(group.getGroupId());
        user.setIsManager(true);
        userMapper.updateByPrimaryKeySelective(user);
        updateService.updateGroupAccount(group.getGroupId());
        return group.getGroupId();
    }

    @Override
    public void updateGroup(String groupName, User user) throws FFMException {
        Groups group = groupsMapper.selectByPrimaryKey(user.getGroupId());
        group.setGroupName(groupName);
        groupsMapper.updateByPrimaryKey(group);
    }

    @Override
    public void dropGroup(User user) throws FFMException {

        /*改变用户*/
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andGroupIdEqualTo(user.getGroupId());
        List<User> users = userMapper.selectByExample(userExample);
        users.stream().forEach(u -> {
            u.setIsManager(false);
            u.setGroupId((long) 0);
            userMapper.updateByPrimaryKey(u);
        });

        groupsMapper.deleteByPrimaryKey(user.getGroupId());

    }

    @Override
    public List<Groups> findGroup(String groupInfo) throws FFMException {
        List<Groups> groupsList = new ArrayList<>();
        GroupsExample example = new GroupsExample();
        GroupsExample.Criteria criteria = example.createCriteria();
        criteria.andGroupNameLike(groupInfo);

        boolean isLongType = false;
        /*验证是否可能为groupId*/
        try {
            Long.parseLong(groupInfo);
            isLongType = true;
        }catch (Exception e){
            logger.info("查询条件非long类型");
        }
        if (isLongType){
            Long signId = Long.parseLong(groupInfo);
            GroupsExample.Criteria criteria2 = example.createCriteria();
            criteria2.andGroupIdEqualTo(signId);
            example.or(criteria2);
        }
        groupsList = groupsMapper.selectByExample(example);
        return groupsList;
    }

    @Override
    public void applyForGroup(Long groupId, User user) throws FFMException {
        Groups group = groupsMapper.selectByPrimaryKey(groupId);
        if (group == null) {
            throw new FFMException(USER_HAVE_NO_GROUP);
        }
        GroupRequest groupRequest = new GroupRequest();
        groupRequest.setFromUser(user.getId());
        groupRequest.setToUser(group.getGroupManager());
        groupRequest.setGmtCreate(new Date());
        groupRequest.setGroupId(group.getGroupId());
        groupRequest.setStatus((long) 0);
        groupRequestMapper.insertSelective(groupRequest);

    }

    @Override
    public void handleGroupApply(Long id, User user, Long decide) throws FFMException {
        GroupRequest groupRequest = groupRequestMapper.selectByPrimaryKey(id);
        if (groupRequest == null){
            throw new FFMException(NO_SUCH_APPLY_RECORD);
        }
        if (!Objects.equals(groupRequest.getToUser(), user.getId())){
            throw new FFMException(NOT_GROUP_MANAGER);
        }
        if (groupRequest.getStatus()>0){
            throw new FFMException(APPLY_RECORD_HANDLED);
        }

        groupRequest.setStatus(decide);
        groupRequestMapper.updateByPrimaryKeySelective(groupRequest);
        if (decide == ALLOW_APPLY) {
            User fromUser = userMapper.selectByPrimaryKey(groupRequest.getFromUser());
            /*如果用户申请了其它组，已经通过了*/
            if (fromUser.getGroupId() == 0) {
                fromUser.setGroupId(groupRequest.getGroupId());
                fromUser.setIsManager(false);
                userMapper.updateByPrimaryKeySelective(fromUser);
                Groups group = groupsMapper.selectByPrimaryKey(groupRequest.getGroupId());
                group.setAllIncome(group.getAllIncome() + fromUser.getAllIncome());
                group.setAllSpending(group.getAllSpending() + fromUser.getAllSpending());
                group.setBalance(group.getAllIncome() - group.getAllSpending());
                group.setGroupMembers(group.getGroupMembers() + "," + fromUser.getId());
                groupsMapper.updateByPrimaryKeySelective(group);

            }
        }
    }

    @Override
    public List<GroupRequest> getGroupRequests(User user) throws FFMException {
        GroupRequestExample requestExample = new GroupRequestExample();
        GroupRequestExample.Criteria criteria = requestExample.createCriteria();
        criteria.andToUserEqualTo(user.getId());
        criteria.andStatusEqualTo((long) 0);
        List<GroupRequest> requests = new ArrayList<>();
        requests = groupRequestMapper.selectByExample(requestExample);
        return requests;
    }

    @Override
    public void quitGroup(User user) throws FFMException {
        if (user.getIsManager()){
            dropGroup(user);
        }else {
            removeFromGroup(user,user.getId());
        }
    }

    @Override
    public void removeFromGroup(User user, Long id) throws FFMException {
        Groups group = groupsMapper.selectByPrimaryKey(user.getGroupId());
        /*获取用户的list*/
        String[] members = group.getGroupMembers().split(",");
        List<String> memberSId = new ArrayList<>();
        CollectionUtils.addAll(memberSId,members);
        /*生成新的用户字符串*/
        Optional<String> findUser = memberSId.stream().filter(s->s.equals(id.toString())).findAny();
        memberSId.remove(findUser.orElseThrow(()->new FFMException(NO_SUCH_USER)));
        String newMenbersId = memberSId.stream().collect(Collectors.joining(","));
        group.setGroupMembers(newMenbersId);
        /*重新计算余额*/
        User removeUser = userMapper.selectByPrimaryKey(id);
        group.setAllSpending(group.getAllSpending()-removeUser.getAllSpending());
        group.setAllIncome(group.getAllIncome()-removeUser.getAllIncome());
        group.setBalance(group.getAllIncome()-group.getAllSpending());
        groupsMapper.updateByPrimaryKey(group);

        removeUser.setIsManager(false);
        removeUser.setGroupId((long) 0);
        userMapper.updateByPrimaryKey(removeUser);
    }

    @Override
    public GroupInfoForm getGroupInfo(User user) throws FFMException {

        Groups group = groupsMapper.selectByPrimaryKey(user.getGroupId());
        String[] members = group.getGroupMembers().split(",");
        List<String> memberSId = new ArrayList<>();
        CollectionUtils.addAll(memberSId,members);
        List<UserInfoFrom> usersForm= new ArrayList<>();
        memberSId.forEach(userid->{
            UserInfoFrom userInfoFrom = new UserInfoFrom();
            User tempUser = userMapper.selectByPrimaryKey(Long.parseLong(userid));
            BeanUtils.copyProperties(tempUser,userInfoFrom);
            usersForm.add(userInfoFrom);
        });
        GroupInfoForm groupInfoForm = new GroupInfoForm();
        BeanUtils.copyProperties(group,groupInfoForm);
        groupInfoForm.setUserInfoFroms(usersForm);
        return groupInfoForm;
    }


}
