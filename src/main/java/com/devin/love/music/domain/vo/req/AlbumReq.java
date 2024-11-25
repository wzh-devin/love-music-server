package com.devin.love.music.domain.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 2024/11/25 20:08
 * <p>
 *     专辑请求对象
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@ApiModel("歌手请求对象")
public class AlbumReq {

    @ApiModelProperty("专辑id列表")
    private List<Long> albumIds;

    @ApiModelProperty("专辑所属歌手id")
    private Long singerId;
}
