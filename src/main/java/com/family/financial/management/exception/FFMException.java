package com.family.financial.management.exception;

import com.family.financial.management.emun.FFMExceptionEnum;

/**
 * Created by zhangyiping on 2017/9/19.
 */
public class FFMException extends Throwable {
    private int code;
    private String msg;

    public FFMException(int code, String msg) {
        this.code = code;
        this.msg = msg;
   }
    public FFMException(FFMExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
