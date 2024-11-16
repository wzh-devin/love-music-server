package com.devin.love.music.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 2024/11/1 20:56
 * <p>
 * 请求数据
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@Builder
public class RequestInfo {
    private Long uid;
}
