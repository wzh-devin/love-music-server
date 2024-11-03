package com.devin.love.music.common.config;

import com.alibaba.druid.util.StringUtils;
import com.devin.love.music.common.enums.HttpErrorEnum;
import com.devin.love.music.common.enums.HttpMethodEnum;
import com.devin.love.music.common.http.PreResponseHandler;
import com.devin.love.music.common.properties.CrossHttpOptionsProperties;
import com.devin.love.music.common.utils.JwtUtil;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
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
            log.info(request.getMethod());
            preResponseHandler.preCrossCheck(request, response);
            return false;
        }

        String token = getToken(request);

        // 如果没有token，则先进行登录
        if (StringUtils.isEmpty(token)) {
            // 请求拦截，发送给前端
            HttpErrorEnum.ACCESS_DENIED.sendHttpError(response);
            return false;
        }

        // TODO 进行token校验
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
