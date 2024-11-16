package com.devin.love.music.domain.vo.req;

import com.devin.love.music.domain.entity.Album;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * 2024/11/16 17:34
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@Builder
@ApiModel("歌手请求对象")
@NoArgsConstructor
@AllArgsConstructor
public class SingerReq {

    @ApiModelProperty("歌手ID")
    private Long id;

    @ApiModelProperty("歌手名")
    @NotNull(message = "歌手名不能为空")
    private String name;

    @ApiModelProperty("歌手性别")
    private Integer sex;

    @ApiModelProperty("歌手生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @ApiModelProperty("歌手图片路径")
    private String singerPicUrl;

    @ApiModelProperty("歌手简介")
    private String description;

    @ApiModelProperty("歌手国籍")
    @NotNull
    private String nationality;
    
    @ApiModelProperty("专辑列表")
    private List<Album> albums;
}
