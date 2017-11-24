package com.family.financial.management.emun;

/**
 * Created by zhangyiping on 2017/9/19.
 */
public enum  FFMExceptionEnum {
    NO_SUCH_USER(100101,"不存在该用户"),
    WRONG_PASSWORD(100102,"用户名或密码错误"),
    USER_EXISTED(100103,"该账号已被注册"),
    MOBILE_REGISTERED(100104,"该手机已经注册了6个账号了"),
    PHOTO_SAVE_ERROR(100105,"头像保存失败"),
    LOGIN_ERROR(100106,"登录状态异常"),
    USER_HAVE_GROUP(100107,"用户已经有家庭组"),
    USER_IS_NOT_MANAGER(100108,"用户非管理员"),
    USER_HAVE_NO_GROUP(100109,"用户没有家庭组"),
    GROUP_ID_ERROR(100110,"组号格式非法"),
    NO_SUCH_GROUP(100111,"不存在该家庭组"),
    NO_SUCH_APPLY_RECORD(100112,"无此申请记录"),
    APPLY_RECORD_HANDLED(100113,"此申请已处理"),
    ILLEAGE_HANDLE(100114,"处理动作非法"),
    NOT_GROUP_MANAGER(100115,"非该组管理员"),
    ERROR_RETURN_TYPE(100116,"返回对象转换失败"),
    USER_JOIN_OTHER_GROUP(100117,"用户已经加入了家庭组"),
    CAN_NOT_REMVE_SELF(100118,"无法移除自己"),
    TOPTYPE_NOT_EXIST(100121,"该分类不存在"),
    ACCOUNT_ID_ERROR(100131,"账单Id异常"),
    NO_SUCH_ACCOUNT(100302,"不存在该账单"),
    ERROR_PARAMETER(100303,"参数异常"),
    DATABASE_ERROR(100888,"数据库异常"),
    NOT_YOUR_TYPE(100304,"非该用户分类"),
    NOT_YOUR_ACCOUNT(100305,"非该用户账单"),
    BASIC_TYPE_ERROR(100305,"基本类型无法修改"),
    SYSTEM_ERROR(999,"系统异常");
    private int code;
    private String msg;

    FFMExceptionEnum(int code, String msg) {
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
