package com.devin.love.music.domain.vo.resp;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 2024/11/6 1:28
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
@ApiModel("专辑响应对象")
public class AlbumInfoResp {

    @ApiModelProperty("专辑id")
    private Long id;

    @ApiModelProperty("专辑名")
    private String name;

    @ApiModelProperty("专辑对应的歌手id")
    private Long singerId;

    @ApiModelProperty("专辑封面地址")
    private String albumPicUrl;

    @ApiModelProperty("专辑发行时间")
    private LocalDate releaseTime;

    @ApiModelProperty("专辑中的歌曲总数")
    private Long musicTotal;

    @ApiModelProperty("专辑描述")
    private String description;
}
