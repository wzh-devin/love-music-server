package com.devin.love.music.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * 2024/11/25 17:45
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
@ApiModel("专辑DTO对象")
public class AlbumInfoDto {
    @ApiModelProperty("专辑id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("专辑名")
    private String name;

    @ApiModelProperty("专辑对应的歌手id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long singerId;

    @ApiModelProperty("专辑对应歌手名")
    private String singerName;

    @ApiModelProperty("专辑封面地址")
    private String albumPicUrl;

    @ApiModelProperty("专辑发行时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseTime;

    @ApiModelProperty("专辑中的歌曲总数")
    private Long musicTotal;

    @ApiModelProperty("专辑描述")
    private String description;
}
