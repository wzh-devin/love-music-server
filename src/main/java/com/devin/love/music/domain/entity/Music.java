package com.devin.love.music.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * 2024/11/7 1:51
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("music")
public class Music {
    @TableId
    private Long id;

    @TableField("singer_id")
    private Long singerId;

    @TableField("album_id")
    private Long albumId;

    @TableField("name")
    private String name;

    @TableField("music_url")
    private String musicUrl;

    @TableField("music_pic_url")
    private String musicPicUrl;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private LocalDate createTime;

    @TableField("modify_time")
    private LocalDate modifyTime;
}
