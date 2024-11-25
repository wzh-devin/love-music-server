package com.devin.love.music.domain.vo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.Data;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 2024/11/6 1:06
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
@ApiModel("歌手响应对象")
public class SingerInfoResp {

    @ApiModelProperty("歌手id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("歌手名字")
    private String name;

    @ApiModelProperty("歌手性别，0-女 1-男")
    private Integer sex;

    @ApiModelProperty("歌手图片地址")
    private String singerPicUrl;

    @ApiModelProperty("歌手生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty("歌手简介")
    private String description;

    @ApiModelProperty("歌手出生地")
    private String birthplace;

    @ApiModelProperty("歌手国籍")
    private String nationality;
}
