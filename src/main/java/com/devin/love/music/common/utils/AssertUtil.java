package com.devin.love.music.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.devin.love.music.common.enums.BusinessErrorEnum;
import com.devin.love.music.common.exception.BusinessException;

import javax.validation.constraints.NotNull;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * 2024/11/16 17:58
 * <p>
 * 自定义断言工具类
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class AssertUtil {

    /**
     * 如果不是真，则抛异常
     * @param expression
     * @param msg
     */
    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throwException(msg);
        }
    }

    /**
     * 如果为真，则抛异常
     * @param expression
     * @param msg
     */
    public static void isFalse(boolean expression, String msg) {
        if (expression) {
            throwException(msg);
        }
    }

    /**
     * 参数统一性校验
     * @param o1
     * @param o2
     */
    public static void equal(Object o1, Object o2, String msg) {
        if (!ObjectUtil.equal(o1, o2)) {
            throwException(msg);
        }
    }

    /**
     * 如果是非空对象，则抛异常
     * @param o
     * @param msg
     */
    public static void isEmpty(@NotNull Object o, String msg) {
        if (!Objects.isNull(o)) {
            throwException(msg);
        }
    }

    /**
     *
     * 如果是空对象，则抛异常
     * @param o
     * @param msg
     */
    public static void isNotEmpty(@NotNull Object o, String msg) {
        if (Objects.isNull(o)) {
            throwException(msg);
        }
    }

    private static void throwException(String msg) {
        throwException(null, msg);
    }

    private static void throwException(BusinessErrorEnum errorEnum, Object... args) {
        if (Objects.isNull(errorEnum)) {
            errorEnum = BusinessErrorEnum.BUSINESS_ERROR;
        }
        throw new BusinessException(errorEnum.getErrCode(), MessageFormat.format(errorEnum.getErrMsg(), args));
    }
}
