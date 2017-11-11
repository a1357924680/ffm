package com.family.financial.management.utils;

/**
 * Created by fanhb on 2017/5/12.
 */
public class Const {
    public static final Integer MAX_REGISTER_NUM = 6;
    public static final int ALLOW_APPLY = 1;
    public static final int REJECT_APPLY = 2;


    public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(forgetPwd)|(logout)|(static)|(plugins)|(commons)|(main)|(index)|(error)).*";	//不对匹配该值的访问路径拦截（正则）
    public static final String SESSION_USER = "sessionUser";
    public static final String SESSION_USER_ID = "sessionUserId";
    public static final String SESSION_USER_NAME = "sessionUserName";
    public static final String SESSION_LOGIN_NAME = "sessionLoginName";
    public static final String DEFAULT_PASSWORD = "LX123456";

    public static final String CUSTOMER_UPDATE_LOG = "CUSTOMER_UPDATE";
    public static final String APP_UPDATE_LOG = "APP_UPDATE";
    public static final String SIGN_SERVICE = "SIGN_SERVICE";
    public static final String SERVICEORDER_UPDATE_LOG = "SERVICEORDER_UPDATE";
    public static final String CUSTOMER_CREATE_LOG = "CUSTOMER_CREATE";
    public static final String LOGIN_LOG = "LOGIN_LOG";
    public static final String EXPORT_EXCEL_LOG = "EXPORT_EXCEL_LOG";
    public static final String SERVICE_LOG = "SERVICE_LOG";
    public static final String MENU_LOG = "MENU_LOG";
    public static final String USER_LOG = "USER_LOG";
    public static final String ROLE_LOG = "ROLE_LOG";
    public static final String SALESMAN_LOG = "SALESMAN_LOG";
    public static class ROLE {
        public static final String SYS_USER = "admin";
//        public static final String SESSION_USER = "sessionUser";
//        public static final String SESSION_USER = "sessionUser";
//        public static final String SESSION_USER = "sessionUser";
    }
    public static class MENU {
        public static final String USER_MANAGE = "USER_MANAGE";
        public static final String ROLE_MANAGE = "ROLE_MANAGE";
        public static final String SALESMAN_MANAGE = "SALESMAN_MANAGE";
        public static final String SERVICE_MANAGE = "SERVICE_MANAGE";
        public static final String CUSTOMER_MANAGE = "CUSTOMER_MANAGE";
        public static final String APP_MANAGE = "APP_MANAGE";
        public static final String USER_AUTH = "USER_AUTH";
    }
}
