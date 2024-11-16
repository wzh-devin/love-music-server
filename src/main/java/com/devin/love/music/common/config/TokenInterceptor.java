package com.devin.love.music.common.config;

import com.alibaba.druid.util.StringUtils;
import com.devin.love.music.common.domain.dto.RequestInfo;
import com.devin.love.music.common.enums.HttpErrorEnum;
import com.devin.love.music.common.enums.HttpMethodEnum;
import com.devin.love.music.common.http.PreResponseHandler;
import com.devin.love.music.common.properties.CrossHttpOptionsProperties;
import com.devin.love.music.common.utils.JwtUtil;
import com.devin.love.music.common.utils.RequestContext;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 2024/11/1 19:59
 * <p>
 * token拦截器
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    private final PreResponseHandler preResponseHandler;

    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_SCHEMA = "Bearer ";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 对浏览器的请求预检策略进行处理
        if (HttpMethodEnum.OPTIONS.toString().equals(request.getMethod())) {
            preResponseHandler.preCrossCheck(request, response);
            return false;
        }

        // 获取token
        String token = getToken(request);

        // 校验token
        Long uid = jwtUtil.getUidOrNull(token);

        // 登录失效，重新登录
        if (Objects.isNull(uid)) {
            // 请求拦截，发送前端
            HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
            return false;
        }

        // 将uid设置到RequestContext中
        RequestContext.set(RequestInfo.builder().uid(uid).build());

        return true;
    }

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        return Optional.ofNullable(header)
                .filter(h -> h.startsWith(AUTHORIZATION_SCHEMA))
                .map(h -> h.replaceFirst(AUTHORIZATION_SCHEMA, ""))
                .orElse(null);
    }
}
