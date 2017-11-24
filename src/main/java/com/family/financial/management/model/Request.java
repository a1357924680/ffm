package com.family.financial.management.model;

import com.family.financial.management.dao.entity.GroupRequest;

import java.util.Date;

/**
 * Created by zhangyiping on 2017/11/24.
 */
public class Request {

    private Long id;

    private Long groupId;

    private Long fromUser;

    private Long toUser;

    private Date gmtCreate;

    /**
     * 0-等待处理，1,-通过，2-拒绝
     */
    private Long status;

    private String userName;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }

    public Long getToUser() {
        return toUser;
    }

    public void setToUser(Long toUser) {
        this.toUser = toUser;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
