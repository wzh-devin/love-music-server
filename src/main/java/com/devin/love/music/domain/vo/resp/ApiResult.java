package com.devin.love.music.domain.vo.resp;

import com.devin.love.music.common.enums.HttpErrorEnum;
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
public class ApiResult<T> {
    private Boolean isSuccess;
    private Integer errCode;
    private String errMsg;
    private Options options;
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
    public static class Options {
        private String message;
        private String type;

        public static Options of(String message, String type) {
            Options options = new Options();
            options.setMessage(message);
            options.setType(type);
            return options;
        }
    }
}