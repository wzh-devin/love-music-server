package com.devin.love.music.common.config;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.common.constant.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 2024/11/1 17:42
 * <p>
 * Mvc配置类
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 增加版本前缀
        configurer.addPathPrefix(Version.V1, a -> a.isAnnotationPresent(ApiV1.class));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 设置允许跨域的请求路径
                .addMapping("/**")
                // 设置允许跨域的域名
                .allowedOriginPatterns("*")
                // 是否cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
