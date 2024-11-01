package com.devin.love.music.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 2024/11/1 17:14
 * <p>
 * 管理员用户表
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
@TableName("admin")
public class Admin {
    private Long id;
    private String username;
    private String password;
}
