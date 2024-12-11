package com.devin.love.music.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @since 2024-11-29
 */
@Data
@TableName("tb_file")
public class FileInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件所属模块id
     */
    @TableField("belong_module_id")
    private Long belongModuleId;

    /**
     * 文件所属模块名
     */
    @TableField("belong_module_name")
    private String belongModuleName;

    /**
     * 文件名
     */
    @TableField("origin_name")
    private String originName;

    /**
     * 文件路径
     */
    @TableField("path")
    private String path;

    /**
     * 文件MD5值
     */
    @TableField("md5")
    private String md5;

    /**
     * 文件类型
     */
    @TableField("type")
    private String type;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDate createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDate modifyTime;
}
