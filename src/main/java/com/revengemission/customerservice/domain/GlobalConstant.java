package com.revengemission.customerservice.domain;

/**
 * Created by SMT-23 on 2017/11/17.
 */
public class GlobalConstant {

    public final static int SUCCESS = 20000;//返回成功
    public final static int ERROR = 10000;//返回失败
    public final static int ERROR_PARAMETER_TYPE = 400;//参数类型错误
    public final static int ERROR_NOT_FOUNT = 404;//没有找到
    public final static int ERROR_ILLEGAL_PARAMETER = 10002;//非法参数
    public final static int ERROR_ALREADY_EXIST = 10003;//已经存在
    public final static int ERROR_EXCEED_THE_MAXIMUM_LIMIT = 10004;//超过最大限制
    public final static int ERROR_WRONG_PASSWORD = 10005;//两次密码输入不一致
    public final static int ERROR_NO_USERNAME = 10006;//用户名不能为空
    public final static int ERROR_NO_AUTHCODE = 10007;//验证码不能为空
    public final static int ERROR_WRONG_AUTHCODE = 10008;//验证码错误
    public final static int ERROR_NO_LOGIN = 401;//未登录
    public final static int ERROR_DENIED = 403;//权限不足
    public final static int ERROR_NOT_ENOUGH = 10009;//超过最大限制
    public final static int ERROR_PAYMENT_REQUEST = 10010;//请求支付错误
    public final static int ERROR_PAYMENT = 10011;//支付错误
    public final static int ERROR_BUSINESS_LOGIC = 10012;//业务逻辑错误
    public final static int ERROR_USER_STOP_LOGIN = 10013;//用户被禁止登录

    public final static String ERROR_MESSAGE_PARAMETER = "参数错误";
    public final static String ERROR_MESSAGE_PARAMETER_TYPE = "参数类型或者格式错误";
    public final static String ERROR_MESSAGE_PARAMETER_ABSENCE = "参数不足";
    public final static String ERROR_MESSAGE_ILLEGAL_PARAMETER = "非法参数";
    public final static String ERROR_MESSAGE_NOT_MATCH = "不匹配";
    public final static String ERROR_MESSAGE_EXPIRED = "已过期";
    public final static String ERROR_MESSAGE_DISABLED = "已禁用";
    public final static String ERROR_MESSAGE_DISCARD = "已作废";
    public final static String ERROR_MESSAGE_NOT_FOUNT = "没有找到";
    public final static String ERROR_MESSAGE_NOAUDIT = "没有进行审核";
    public final static String ERROR_MESSAGE_ALREADY_EXIST = "已经存在";
    public final static String ERROR_MESSAGE_EXCEED_THE_MAXIMUM_LIMIT = "超过最大限制";
    public final static String ERROR_MESSAGE_NOT_ENOUGH = "不够";
    public final static String ERROR_MESSAGE_ONE_PEOPLE = "买方卖方同一人";
    public final static String ERROR_MESSAGE_USER_STOP_LOGIN = "用户被禁止登录";


    public final static String ROLE_SUPER = "ROLE_SUPER";//超级管理员
    public final static String ROLE_ADMIN = "ROLE_ADMIN";//管理员
    public final static String ROLE_COMMISSIONER = "ROLE_COMMISSIONER";//客服人员
    public final static String ROLE_USER = "ROLE_USER";//普通用户
    public final static String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";//普通用户

    public final static String LEVEL_COMMON = "COMMON";//
    public final static String LEVEL_MIDDLE = "MIDDLE";//
    public final static String LEVEL_HIGH = "HIGH";//

    public final static int RECORD_DEFAULT = 0;//默认初始值
    public final static int RECORD_DISABLED = 1;//禁用
    public final static int RECORD_DISCARDED = 2;//已作废

}
