package org.seu.spring.util;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
public class ResultVO<V> extends Result{

    /**
     * 数据
     */
    private V data;

    public ResultVO() {}

    public ResultVO(Integer code, String message) {
        super.code = code;
        super.message = message;
    }

    public ResultVO(Integer code, String message, V data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVO success() {
        return success(null);
    }

    public ResultVO success(V data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.data = data;
        return this;
    }

    @Override
    public ResultVO fail() {
        return fail(ResultCodeEnum.ERROR_SERVER);
    }

    @Override
    public ResultVO fail(ResultCodeEnum resultCode) {
        return fail(resultCode, resultCode.getMessage());
    }

    @Override
    public ResultVO fail(String message) {
        return fail(ResultCodeEnum.ERROR_OTHER, message);
    }

    @Override
    public ResultVO fail(ResultCodeEnum resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
        return this;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVO{" + "data=" + data + ", code=" + code + ", message='" + message + '\'' + '}';
    }
}
