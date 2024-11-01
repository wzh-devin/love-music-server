package com.devin.love.music.domain.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 2024/11/1 17:18
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Data
public class LoginReq {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
