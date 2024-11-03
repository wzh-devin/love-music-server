package com.devin.love.music.common.config;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.devin.love.music.common.enums.HttpMethodEnum;
import com.devin.love.music.common.properties.CrossHttpOptionsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2024/11/3 0:56
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Configuration
@ConditionalOnBean(CrossHttpOptionsProperties.class)
@EnableConfigurationProperties(CrossHttpOptionsProperties.class)
public class CrossHttpConfig {

    @Autowired
    private CrossHttpOptionsProperties crossHttpOptionsProperties;

    @PostConstruct
    private void crossHttpOptionsProperties() {
        // 允许跨域
        crossHttpOptionsProperties.setAllowCredentials(Boolean.TRUE);
        // 设置允许的方法
        crossHttpOptionsProperties.setAllowMethods(List.of(Arrays.toString(HttpMethodEnum.values())));
        // 配置允许的header
        crossHttpOptionsProperties.setAllowHeaders(List.of("Authorization"));
        // 配置下次预检请求的有效期 ==> s
        crossHttpOptionsProperties.setAccessControlMaxAge(1800);

    }

    public Map<String, String> crossAllowedHeaders(HttpServletRequest request) {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Access-Control-Allow-Origin", request.getHeader("Origin"));
        headersMap.put("Access-Control-Allow-Credentials", crossHttpOptionsProperties.getAllowCredentials().toString());
        headersMap.put("Access-Control-Allow-Methods", listToString(crossHttpOptionsProperties.getAllowMethods()));
        headersMap.put("Access-Control-Max-Age", crossHttpOptionsProperties.getAccessControlMaxAge().toString());
        headersMap.put("Access-Control-Allow-Headers", listToString(crossHttpOptionsProperties.getAllowHeaders()));
        return headersMap;
    }

    private String listToString(List<String> list) {
        return String.join(",", list).replaceAll("[\\[\\]]", "");
    }
}
