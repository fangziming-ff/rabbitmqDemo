package com.fangming.rabbitmqdemo.rabbitmq;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : william.ding
 * @description :
 * @date : 2021-04-05 23:27
 */
@Data
@ApiModel
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6746468112986178042L;

    private static final String SUCCESS = "success";
    private static final String FAILED = "failed";

    /**
     * 如果为success,则可以调用getResult,如果为failed,则调用errorCode来获取出错信息
     */
    @ApiModelProperty(value = "调用的结果：success-成功，failed-失败", position = 1, example = "success")
    private String status;

    /**
     * 调用返回值-泛型
     */
    @ApiModelProperty(value = "返回的数据，当status=success时有值，否则为null", position = 2)
    private T result;

    /**
     * 错误码
     */
    @ApiModelProperty(value = "错误码，当status=failed时有值，否则为null", position = 3)
    private Integer errorCode;

    /**
     * 错误描述
     */
    @ApiModelProperty(value = "错误信息，当status=failed时有值，否则为null", position = 4)
    private String errorMsg;

    /**
     * 默认构造方法
     */
    public Result() {
    }

    /**
     * 默认构造方法<p/>
     * 设置返回结果，则默认接口调用成功
     *
     * @param result 调用返回值
     */
    public Result(T result) {
        this.status = SUCCESS;
        this.result = result;
        this.errorCode = ResultCodeEnum.SUCCESS.getCode();
    }

    /**
     * 默认构造方法<p/>
     * 设置返回结果，则默认接口调用成功
     *
     * @param message 调用返回值
     */
    public Result(String message) {
        this.status = FAILED;
        this.errorCode = ResultCodeEnum.SERVER_ERROR.getCode();
        this.errorMsg = message;
    }
    /**
     * 构造方法，根据flag返回不同结果
     *
     * @param flag   接口调用状态
     * @param result 调用返回值
     */
    public Result(boolean flag, T result) {
        if (flag) {
            this.status = SUCCESS;
            this.result = result;
            this.errorCode = ResultCodeEnum.SUCCESS.getCode();
        } else {
            this.status = FAILED;
            this.errorCode = (Integer) result;
        }
    }

    /**
     * 构造方法，接口调用失败，设置错误描述
     *
     * @param errorCode 错误描述
     */
    public Result(Integer errorCode) {
        this.status = FAILED;
        this.errorCode = errorCode;
    }

    /**
     * 构造方法，接口调用失败，设置错误描述
     *
     * @param codeEnum 错误描述
     */
    public Result(ResultCodeEnum codeEnum) {
        this.status = FAILED;
        this.errorCode = codeEnum.getCode();
        this.errorMsg = codeEnum.getMessage();
    }

    /**
     * 构造方法，设置错误信息
     *
     * @param errorCode 错误码
     * @param errorMsg  错误描述
     */
    public Result(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        status = SUCCESS;
        this.result = result;
        this.errorCode = ResultCodeEnum.SUCCESS.getCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.status = FAILED;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Result result = (Result) o;

        return status.equals(result.status)
                && result.equals(result.result)
                && errorCode.equals(result.errorCode);

    }

    @Override
    public int hashCode() {
        int result1 = (SUCCESS.equals(status) ? 1 : 0);
        result1 = 31 * result1 + result.hashCode();
        result1 = 31 * result1 + errorCode.hashCode();
        return result1;
    }

}
