package com.devin.love.music.domain.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 2024/11/27 22:21
 * <p>
 *     文件信息请求类
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Builder
@ApiModel("文件信息类")
@Data
public class FileInfoReq {

    @ApiModelProperty("文件所属模块id")
    private Long belongModuleId;

    @ApiModelProperty("文件所属模块名")
    private String belongModuleName;

    @ApiModelProperty("文件名_md5.")
    private String originName;

    @ApiModelProperty("文件路径")
    private String path;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件传输对象")
    private MultipartFile file;

    @ApiModelProperty("文件的MD5值")
    private String md5;
}
