package org.seu.spring.util;

/**
 * @author slowdive
 * @summary
 * @Copyright (c) 2022, Lianjia Group All Rights Reserved.
 * @since 2022/4/19
 */
public class ResultDTO<V> extends Result {

    /**
     * 数据
     */
    private V data;

    public ResultDTO() {}

    public ResultDTO(Integer code, String message) {
        super.code = code;
        super.message = message;
    }

    public ResultDTO success() {
        return success(null);
    }

    public ResultDTO success(V data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.data = data;
        return this;
    }

    @Override
    public ResultDTO fail() {
        return fail(ResultCodeEnum.ERROR_SERVER);
    }

    @Override
    public ResultDTO fail(ResultCodeEnum resultCode) {
        return fail(resultCode, resultCode.getMessage());
    }

    @Override
    public ResultDTO fail(String message) {
        return fail(ResultCodeEnum.ERROR_OTHER, message);
    }

    @Override
    public ResultDTO fail(ResultCodeEnum resultCode, String message) {
        this.code = resultCode.getCode();
        this.message = message;
        return this;
    }

    public V getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResultDTO{" + "data=" + data + ", code=" + code + ", message='" + message + '\'' + '}';
    }

    public boolean isSuccess() {
        return code != null && code == ResultCodeEnum.SUCCESS.getCode();
    }

    public ResultVO toVO() {
        return new ResultVO(this.code, this.message, this.data);
    }

}
