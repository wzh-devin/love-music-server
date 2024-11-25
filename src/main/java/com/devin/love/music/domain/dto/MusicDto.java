package com.devin.love.music.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 2024/11/25 23:39
 * <p>
 *     音乐DTO
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Builder
@Data
@ApiModel("音乐DTO对象")
@AllArgsConstructor
@NoArgsConstructor
public class MusicDto {

    @ApiModelProperty("音乐id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @NotNull
    @ApiModelProperty("歌手id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long singerId;

    @ApiModelProperty("歌手名")
    private String singerName;

    @ApiModelProperty("专辑id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long albumId;

    @ApiModelProperty("专辑名")
    private String albumName;

    @ApiModelProperty("歌曲名")
    private String name;

    @ApiModelProperty("歌曲地址")
    private String musicUrl;

    @ApiModelProperty("歌曲图片地址")
    private String musicPicUrl;

    @ApiModelProperty("歌曲描述")
    private String description;
}
