package com.devin.love.music.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 2024/11/16 18:03
 * <p>
 * 业务错误枚举
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorEnum {
    BUSINESS_ERROR(0, "{0}"),
    SYSTEM_ERROR(1, "系统错误，请稍后重试~~~"),
    ;

    private final Integer errCode;
    private final String errMsg;
}
