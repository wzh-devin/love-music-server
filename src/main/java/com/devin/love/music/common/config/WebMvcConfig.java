package com.devin.love.music.common.config;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.common.constant.VersionConstant;
import org.springframework.context.annotation.Configuration;
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

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 增加版本前缀
        configurer.addPathPrefix(VersionConstant.V1, a -> a.isAnnotationPresent(ApiV1.class));
    }
}
