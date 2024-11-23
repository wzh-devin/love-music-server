package com.devin.love.music.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 2024/11/6 0:40
 * <p>
 * 歌手实体类
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("singer")
public class Singer {
    @TableId
    private Long id;

    @TableField("name")
    private String name;

    @TableField("sex")
    private Integer sex;

    @TableField("singer_pic_url")
    private String singerPicUrl;

    @TableField("file_md5")
    private String fileMd5;

    @TableField(value = "birthday")
    private LocalDate birthday;

    @TableField("description")
    private String description;

    @TableField("birthplace")
    private String birthplace;

    @TableField("nationality")
    private String nationality;

    @TableField("create_time")
    private LocalDate createTime;

    @TableField("modify_time")
    private LocalDate modifyTime;
}
