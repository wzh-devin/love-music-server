package com.devin.love.music.common.utils;

import com.devin.love.music.common.domain.dto.RequestInfo;

/**
 * 2024/11/1 20:53
 * <p>
 * 请求上下文，将用户信息保存到本地
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class RequestContext {
    private static final ThreadLocal<RequestInfo> local = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        local.set(requestInfo);
    }

    public static RequestInfo get() {
        return local.get();
    }

    public static void remove() {
        local.remove();
    }
}
