package com.family.financial.management.model;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.InputStream;

/**
 * Created by zhangyiping on 2017/9/19.
 */

public class UserForm {

    /**
     * 账号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;


    private String sex;

    private MultipartFile filePhoto;

    @NotNull(message = "请上传头像")
    public MultipartFile getFilePhoto() {
        return filePhoto;
    }

    @NotNull
    @Pattern(regexp = "[0,1]",message = "性别格式非法")
    public String getSex() {
        return sex;
    }

    @NotNull(message = "账号不能为空")
    @Pattern(regexp = "\\w{6,20}",message = "账号格式非法")
    public String getUserId() {
        return userId;
    }
    @NotNull(message = "昵称不能为空")
    @Size(min = 2,max = 20,message = "昵称格式非法")
    public String getUserName() {
        return userName;
    }
    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "手机号格式不正确")
    public String getMobile() {
        return mobile;
    }
    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "\\w{6,20}",message = "密码格式非法")
    public String getPassword() {
        return password;
    }



    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setFilePhoto(MultipartFile filePhoto) {
        this.filePhoto = filePhoto;
    }
}
