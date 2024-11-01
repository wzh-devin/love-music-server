package com.devin.love.music.common.constant;

/**
 * 2024/11/1 21:09
 * <p>
 * RedisKey
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class RedisKey {

    /**
     * 基础Key
     */
    private static final String BASE_KEY = "love:music";

    /**
     * 后台用户登录的token
     */
    public static final String ADMIN_TOKEN_STRING = "admin:token:%d";

    /**
     * 后台用户登录的信息
     */
    public static final String ADMIN_INFO_STRING = "adminInfo:%d";

    /**
     * 获取Key
     * @param key
     * @param o
     * @return
     */
    public static String getKey(String key, Object... o) {
        return BASE_KEY + String.format(key, o);
    }
}
