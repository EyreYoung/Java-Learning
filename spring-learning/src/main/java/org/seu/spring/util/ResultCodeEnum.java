package org.seu.spring.util;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
public enum ResultCodeEnum {

    SUCCESS(2000, "操作成功"),
    ALL_SUCCESS(2001, "操作成功"),
    ERROR_UNAUTHENTICATED(4001, "暂未登录或token已经过期"),
    ERROR_PERMISSION_DENIED(4003, "没有相关权限"),
    ERROR_PARAM_ILLEGAL(4004, "参数校验失败"),
    ERROR_LOGIN_FAILED(4019, "登录失败"),
    /** 对于帐号密码登录成功后，根据某些条件进行判断是否进行增强校验 */
    ERROR_LOGIN_NEED_VERIFY(4020, "需要登录增强校验"),
    LOGIN_SESSION_EXPIRED(4030, "登录过程会话过期，需进行登录页面刷新"),
    ERROR_SERVER(5000, "操作失败,服务端异常"),
    ERROR_INVOKE(6000, "调用其他服务接口错误"),
    ERROR_OTHER(7000,"其他错误"),
    ERROR_BUSINESS(7001,"业务异常");

    private final int code;
    private final String message;

    private ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultCodeEnum{" + "code=" + code + ", message='" + message + '\'' + '}';
    }

    /**
     * @author sunrenyong
     * @param code
     * @return
     */
    public static ResultCodeEnum getEnumByCode(int code) {
        ResultCodeEnum[] values = values();
        for (ResultCodeEnum resultCodeEnum : values) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }

}
