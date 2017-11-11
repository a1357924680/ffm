package com.family.financial.management.emun;

/**
 * Created by Administrator on 2017/9/19.
 */
public enum FFMEnum {
    MANAGER_USER(1,"managerUser"),//aa
    NORMAL_USER(0,"normalUser"),
    PHOTO_PATH(0,"E:\\ffm\\users\\");
    private int code;
    private String msg;

    FFMEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
