package com.family.financial.management.service.interfaces;

import com.family.financial.management.dao.entity.Answer;
import com.family.financial.management.dao.entity.Message;
import com.family.financial.management.exception.FFMException;

import java.util.List;

/**
 * Created by zhangyiping on 2017/12/17.
 */
public interface MessageService {
    int getMessagePage()throws FFMException;
    List<Message> getMessages(int pageNum)throws FFMException;
    void addMessage(Message message)throws FFMException;
    void deleteMessage(int id)throws FFMException;
    void answerMessage(String message)throws FFMException;
    List<Answer> getAnswers(Long userId)throws FFMException;
}
