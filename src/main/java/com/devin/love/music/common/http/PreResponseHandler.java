package com.devin.love.music.common.http;

import com.devin.love.music.common.config.CrossHttpConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 2024/11/3 1:42
 * <p>
 * 前置响应处理
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PreResponseHandler {

    private final CrossHttpConfig crossHttpConfig;

    /**
     * 前置跨域检测请求处理
     * @param request
     * @param response
     * @return
     */
    public void preCrossCheck(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> headersMap = crossHttpConfig.crossAllowedHeaders(request);
        headersMap.forEach(response::setHeader);
    }
}
