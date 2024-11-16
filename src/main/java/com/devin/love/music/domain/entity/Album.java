package com.devin.love.music.domain.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * 2024/11/6 1:01
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("album")
public class Album {
    @TableId
    private Long id;

    @TableField("name")
    private String name;

    @TableField("singer_id")
    private Long singerId;

    @TableField("album_pic_url")
    private String albumPicUrl;

    @TableField("release_time")
    private LocalDate releaseTime;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private LocalDate createTime;

    @TableField("modify_time")
    private LocalDate modifyTime;
}
