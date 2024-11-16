package com.devin.love.music.domain.vo.resp;

import com.devin.love.music.common.enums.HttpErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 2024/11/1 17:20
 * <p>
 * 封装API返回结果集
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@ApiModel("基础返回体")
public class ApiResult<T> {

    @ApiModelProperty("是否返回成功")
    private Boolean isSuccess;

    @ApiModelProperty("错误码")
    private Integer errCode;

    @ApiModelProperty("错误信息")
    private String errMsg;

    @ApiModelProperty("额外选项")
    private Options options;

    @ApiModelProperty("返回对象")
    private T data;

    /**
     * 成功无参结果集
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult<>();
        result.setIsSuccess(Boolean.TRUE);
        result.setErrCode(null);
        result.setErrMsg(null);
        result.setData(null);
        return result;
    }

    /**
     * 成功带参结果集
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> success(T data, Options options) {
        ApiResult<T> result = new ApiResult<>();
        result.setIsSuccess(Boolean.TRUE);
        result.setErrCode(null);
        result.setErrMsg(null);
        result.setData(data);
        result.setOptions(options);
        return result;
    }

    /**
     * 失败结果集
     * @param errCode
     * @param errMsg
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> fail(Integer errCode, String errMsg) {
        ApiResult<T> result = new ApiResult<>();
        result.setIsSuccess(Boolean.FALSE);
        result.setErrCode(errCode);
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }

    /**
     * 失败结果参数枚举
     * @param errorEnum
     * @return
     * @param <T>
     */
    public static <T> ApiResult<T> fail(HttpErrorEnum errorEnum) {
        ApiResult<T> result = new ApiResult<>();
        result.setIsSuccess(Boolean.FALSE);
        result.setErrCode(errorEnum.getErrCode());
        result.setErrMsg(errorEnum.getErrMsg());
        result.setData(null);
        return result;
    }

    /**
     * 额外选项
     */
    @Data
    @ApiModel("基础返回对象的额外选项")
    public static class Options {

        @ApiModelProperty("额外信息")
        private String message;

        @ApiModelProperty("额外类型")
        private String type;

        public static Options of(String message, String type) {
            Options options = new Options();
            options.setMessage(message);
            options.setType(type);
            return options;
        }
    }
}