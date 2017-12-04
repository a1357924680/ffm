package com.family.financial.management.service.interfaces;

import com.family.financial.management.dao.entity.User;
import com.family.financial.management.model.UserForm;
import com.family.financial.management.exception.FFMException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangyiping on 2017/9/19.
 */
public interface UserService {
    public User getUser(String userId) throws FFMException;
    public void registerUser(UserForm userForm ) throws FFMException;
    public void updateUser(UserForm userForm,Long userId) throws FFMException;
    public List getMonthBill(long userId,long year) throws FFMException;
}
