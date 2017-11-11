package com.family.financial.management.service.impl;

import com.family.financial.management.dao.entity.AccountMonth;
import com.family.financial.management.dao.entity.AccountMonthExample;
import com.family.financial.management.dao.entity.User;

import com.family.financial.management.dao.entity.UserExample;

import com.family.financial.management.dao.mapper.AccountMonthMapper;
import com.family.financial.management.dao.mapper.UserMapper;
import com.family.financial.management.model.UserForm;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.service.interfaces.UserService;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.Optional;

import static com.family.financial.management.emun.FFMEnum.PHOTO_PATH;
import static com.family.financial.management.emun.FFMExceptionEnum.*;
import static com.family.financial.management.utils.Const.MAX_REGISTER_NUM;

/**@author zyp
 * Created by zhangyiping on 2017/9/19.
 * 更新用户账户，和家庭组账户
 */
@Service
@Transactional(rollbackFor = FFMException.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountMonthMapper accountMonthMapper;

    @Override
    public User getUser(String userId) throws FFMException {
        Optional<User> user = Optional.ofNullable(selectUserByUserId(userId));
        return user.orElseThrow(()->new FFMException(NO_SUCH_USER));
    }

    User selectUserByUserId(String userId){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }
    @Override
    public void registerUser(UserForm userForm) throws FFMException {
        User user = new User();
        BeanUtils.copyProperties(userForm,user);
        user.setIsManager(false);
        user.setGroupId((long) 0);
        user.setMobile(Long.parseLong(userForm.getMobile()));
        User validUser = selectUserByUserId(userForm.getUserId());
        if (validUser != null) {
            throw new FFMException(USER_EXISTED);
        }
        checkMobileNum(user.getMobile());

        String originalFilename=userForm.getFilePhoto().getOriginalFilename();
        String types=originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
        String newFileName=PHOTO_PATH.getMsg() + user.getUserId() + "." +types;
        savePhoto(newFileName,userForm.getFilePhoto());
        BASE64Encoder base64 = new BASE64Encoder();
        String code = base64.encode(file2Byte(newFileName));
        code = "data:image/"+types+";base64,"+code;
        user.setPhoto(code);
        try {
            userMapper.insertSelective(user);
        }catch (Exception e){
            throw new FFMException(SYSTEM_ERROR);
        }

    }

    @Override
    public void updateUser(UserForm userForm) throws FFMException {

        User user = selectUserByUserId(userForm.getUserId());
        if (user == null) {
            throw new FFMException(NO_SUCH_USER);
        }
        BeanUtils.copyProperties(userForm,user);
        user.setMobile(Long.parseLong(userForm.getMobile()));
        checkMobileNum(user.getMobile());

        String originalFilename=userForm.getFilePhoto().getOriginalFilename();
        String types=originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
        String newFileName=PHOTO_PATH.getMsg() + user.getUserId() + "." +types;
        savePhoto(newFileName,userForm.getFilePhoto());
        BASE64Encoder base64 = new BASE64Encoder();
        String code = base64.encode(file2Byte(newFileName));
        code = "data:image/"+types+";base64,"+code;
        user.setPhoto(code);
        try {
            userMapper.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            throw new FFMException(SYSTEM_ERROR);
        }
    }

    @Override
    public List getMonthBill(String userId) throws FFMException {
        Long userid ;
        Long groupid ;
        try {
            userid = Long.parseLong(userId);
        }catch (Exception e){
            throw new FFMException(ERROR_PARAMETER);
        }
        User user = userMapper.selectByPrimaryKey(userid);
        if (null == user){
            throw new FFMException(NO_SUCH_USER);
        }

        AccountMonthExample example = new AccountMonthExample();
        AccountMonthExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userid);
        List<AccountMonth> accountMonthList = accountMonthMapper.selectByExample(example);
        return accountMonthList;
    }


    /**
     * 检查号码注册人数是否已满
     * @param mobile
     * @throws FFMException
     */
    private void checkMobileNum(Long mobile) throws FFMException {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()>=MAX_REGISTER_NUM){
            throw new FFMException(MOBILE_REGISTERED);
        }
    }

    /**
     * 保存头像
     * @param newFileName
     * @param filePhoto
     * @throws FFMException
     */
    private void savePhoto(String newFileName,MultipartFile filePhoto) throws FFMException {

        try {
            //以用户id加图片扩展名给图片命名
            File file=new File(newFileName);

            filePhoto.transferTo(file);
            //以80*80大小改变图片，此处使用thumbnailator-0.4.2.jar改变图片大小
            Thumbnails.of(file).size(80, 80).keepAspectRatio(false).toFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FFMException(PHOTO_SAVE_ERROR);
        }
    }

    public static byte[] file2Byte(String filePath) throws FFMException {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new FFMException(PHOTO_SAVE_ERROR);
        }
        return buffer;
    }
}
