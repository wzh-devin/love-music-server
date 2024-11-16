package com.devin.love.music.common.exception;

import com.devin.love.music.common.enums.BusinessErrorEnum;
import lombok.Data;

/**
 * 2024/11/16 18:00
 * <p>
 * 业务异常
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class BusinessException extends RuntimeException {

    // 错误码
    private Integer errCode;

    // 错误信息
    private String errMsg;

    public BusinessException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public BusinessException(Integer errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public BusinessException(BusinessErrorEnum errorEnum) {
        super(errorEnum.getErrMsg());
        this.errCode = errorEnum.getErrCode();
        this.errMsg = errorEnum.getErrMsg();
    }
}
