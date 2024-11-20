package com.devin.love.music.common.annotation;

import com.devin.love.music.common.constant.Version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 2024/11/16 23:51
 * <p>
 * 自定义日志注解
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 业务描述
     *
     * @return
     */
    String desc() default "";

    /**
     * 业务模块
     *
     * @return
     */
    String module();

    /**
     * 业务版本
     *
     * @see Version
     * @return
     */
    String version();
}
