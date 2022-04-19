package org.seu.spring.util;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
public class Result {

    /**
     * 状态码
     */
    protected Integer code;
    /**
     * 描述信息
     */
    protected String message;

    public Result() {}

    public Result fail() {
        return fail(ResultCodeEnum.ERROR_SERVER);
    }

    public Result fail(ResultCodeEnum resultCode) {
        return fail(resultCode, resultCode.getMessage());
    }

    public Result fail(String message) {
        return fail(ResultCodeEnum.ERROR_OTHER, message);
    }

    public Result fail(ResultCodeEnum resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
        return this;
    }

    public Result fail(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
