package com.devin.love.music.common.properties;

import com.devin.love.music.common.enums.HttpMethodEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 2024/11/3 0:45
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "cross-http-options.access-control")
public class CrossHttpOptionsProperties {
    private String allowOrigin;
    private Boolean allowCredentials;
    private List<String> allowMethods;
    private Integer accessControlMaxAge;
    private List<String> allowHeaders;
}
