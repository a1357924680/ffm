package com.family.financial.management.controller;

import com.family.financial.management.dao.entity.Message;
import com.family.financial.management.exception.FFMException;
import com.family.financial.management.service.interfaces.MessageService;
import com.family.financial.management.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.family.financial.management.utils.ResultMapUtils.getErrorResult;
import static com.family.financial.management.utils.ResultMapUtils.getSuccessResult;

/**
 * Created by zhangyiping on 2017/12/17.
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    /**
     * 每页20条记录
     * @return
     */
    @GetMapping("/getMessagePage")
    public Map<String, String> getMessagePage(){
        try {
            Map<String,Object> info = new HashMap<String,Object>();
            int page = messageService.getMessagePage();
            return getSuccessResult("page",page);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    /**
     * 页码从1开始
     * @return
     */
    @GetMapping("/getMessages")
    public Map<String, String> getMessages(String pageNum){
        try {
            List<Message> messages = messageService.getMessages(StringUtils.praseInteger(pageNum));
            return getSuccessResult("messages",messages);
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteMessage")
    public Map<String, String> deleteMessage(String id){
        try {
            messageService.deleteMessage(StringUtils.praseInteger(id));
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    @PostMapping("/createMessage")
    public Map<String, String> createMessage(String message){
        try {
            Message messageForm = new Message();
            if (!StringUtils.isEmpty(message)){
                messageForm.setMessage(message);
            }else {
                throw new FFMException(1234554,"内容不能为空");
            }
            messageForm.setUser(getUser().getUserName());
            messageService.addMessage(messageForm);
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }

    /**
     * 还没做，以后再说
     * @param message
     * @return
     */
    @PostMapping("/answerMessage")
    public Map<String, String> answerMessage(String message){
        try {

            if (!StringUtils.isEmpty(message)){
                messageService.answerMessage(message);
            }else {
                throw new FFMException(1234554,"内容不能为空");
            }
            return getSuccessResult();
        } catch (FFMException e) {
            return getErrorResult(e.getCode(),e.getMsg());
        }
    }
}
