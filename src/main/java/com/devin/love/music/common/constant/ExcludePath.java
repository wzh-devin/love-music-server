package com.devin.love.music.common.constant;

/**
 * 2024/11/7 2:43
 * <p>
 * 排除路径常量
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class ExcludePath {

    /**
     * token拦截器的排除路径
     */
    public static String[] tokenInterceptorExcludePath() {
        return new String[]{
                "/**/login",
                "/swagger-resources/**",
                "/webjars/**",
                "/v2/**",
                "/swagger-ui.html",
                "/doc.html",
                "/favicon.ico"
        };
    }
}
