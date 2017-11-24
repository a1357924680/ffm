package com.family.financial.management.service.interfaces;


import com.family.financial.management.dao.entity.GroupRequest;
import com.family.financial.management.dao.entity.Groups;
import com.family.financial.management.dao.entity.User;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.model.GroupInfoForm;
import com.family.financial.management.model.Request;

import java.util.List;

/**
 * Created by zhangyiping on 2017/9/20.
 */
public interface GroupService {
    long createGroup(String groupName,User user) throws FFMException;
    void updateGroup(String groupName,User user) throws FFMException;
    void dropGroup(User user) throws FFMException;
    List<Groups> findGroup(String groupInfo) throws FFMException;
    void applyForGroup(Long groupId,User user) throws FFMException;
    void handleGroupApply(Long id,User user,Long decide) throws FFMException;
    List<Request> getGroupRequests(User user) throws FFMException;
    void quitGroup(User user) throws FFMException;
    void removeFromGroup(User user,Long id) throws FFMException;
    GroupInfoForm getGroupInfo(User user) throws FFMException;

}
